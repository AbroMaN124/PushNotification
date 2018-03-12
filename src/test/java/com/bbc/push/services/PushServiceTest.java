package com.bbc.push.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bbc.push.daos.UserDao;
import com.bbc.push.exceptions.BadRequestException;
import com.bbc.push.exceptions.NotFoundException;
import com.bbc.push.models.User;
import com.bbc.push.models.elements.CreatePushElement;

/**
 * Tests {@link PushService}
 *
 * @author Ahmad A. Furqan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PushServiceTest {

  @Autowired
  PushService pushService;

  @SpyBean
  UserDao userDao;

  @MockBean
  RestTemplate restTemplate;

  @Test
  public void pushNotificationSuccesfully() throws Exception {
    String username = "user1";
    User user = new User(username, "accessToken");
    when(userDao.getUser(username)).thenReturn(user);
    when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
        .thenReturn(new ResponseEntity<String>("You did it", HttpStatus.CREATED));
    String response = pushService.push("user1",
        new CreatePushElement().setType("note").setTitle("title").setBody("body"));
    assertEquals("You did it", response);
    verify(userDao, times(1)).putUser(any(User.class));
  }

  @Test(expected = NotFoundException.class)
  public void pushNotificationForUnregisteredUser() throws Exception {
    String username = "unregistered";
    when(userDao.getUser(username)).thenReturn(null);
    pushService.push(username, new CreatePushElement().setType("note").setTitle("title").setBody("body"));
  }

  @Test
  public void pushNotificationReturns4xx() throws Exception {
    String username = "user1";
    User user = new User(username, "accessToken");
    when(userDao.getUser(username)).thenReturn(user);
    when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
        .thenReturn(new ResponseEntity<String>("Bad request", HttpStatus.BAD_REQUEST));
    String response = pushService.push("user1",
        new CreatePushElement().setType("note").setTitle("title").setBody("body"));
    assertEquals("Bad request", response);
    verify(userDao, times(0)).putUser(any(User.class));
  }

  @Test(expected = BadRequestException.class)
  public void pushNotificationThrowsHttpClientErrorException() throws Exception {
    String username = "user1";
    User user = new User(username, "accessToken");
    when(userDao.getUser(username)).thenReturn(user);
    when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), any(Class.class)))
        .thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Bad Request"));
    pushService.push("user1", new CreatePushElement().setType("note").setTitle("title").setBody("body"));
    verify(userDao, times(0)).putUser(any(User.class));
  }
}
