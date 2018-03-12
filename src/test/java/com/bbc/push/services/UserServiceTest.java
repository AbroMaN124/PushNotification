package com.bbc.push.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bbc.push.daos.UserDao;
import com.bbc.push.exceptions.BadRequestException;
import com.bbc.push.exceptions.ConflictException;
import com.bbc.push.models.User;
import com.bbc.push.models.elements.UserElement;

/**
 *
 * Tests {@link UserService}
 *
 * @author Ahmad A. Furqan
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @MockBean
  private UserDao userDao;

  @Test
  public void createUserSuccessfully() throws Exception {
    User user = new User("username", "accessToken");

    when(userDao.addUser(any())).thenReturn(user);

    UserElement result = userService.createUser("username", "accessToken");

    assertEquals(user.getUsername(), result.getUsername());
    assertEquals(user.getAccessToken(), result.getAccessToken());
    assertEquals(0, result.getNumOfNotificationsPushed());
  }

  @Test(expected = BadRequestException.class)
  public void createUserMissingUsernameAccessToken() throws Exception {
    userService.createUser(null, null);
  }

  @Test(expected = BadRequestException.class)
  public void createUserMissingUsername() throws Exception {
    userService.createUser(null, "token");
  }

  @Test(expected = BadRequestException.class)
  public void createUserMissingAccessToken() throws Exception {
    userService.createUser("username", null);
  }

  @Test(expected = ConflictException.class)
  public void createUserPassesOnConflict() throws Exception {
    when(userDao.addUser(any())).thenThrow(new ConflictException("User with username username already exists"));

    userService.createUser("username", "accessToken");
  }

  @Test
  public void getUsers() throws Exception {
    User one = new User("test", "user");
    User two = new User("00000", "0101");
    List<User> users = new ArrayList<User>() {
      {
        add(one);
        add(two);
      }
    };

    when(userDao.getUsers()).thenReturn(users);

    List<UserElement> result = userService.getUsers();

    assertEquals(2, result.size());
    assertEquals(one.getUsername(), result.get(0).getUsername());
    assertEquals(two.getUsername(), result.get(1).getUsername());
    assertEquals(one.getAccessToken(), result.get(0).getAccessToken());
    assertEquals(two.getAccessToken(), result.get(1).getAccessToken());
  }

  @Test
  public void getUsersWhenNoneExist() throws Exception {
    when(userDao.getUsers()).thenReturn(new ArrayList<User>());

    List<UserElement> result = userService.getUsers();

    assertEquals(0, result.size());
  }
}
