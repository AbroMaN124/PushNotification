package com.bbc.push.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bbc.push.daos.UserDao;
import com.bbc.push.exceptions.BadRequestException;
import com.bbc.push.exceptions.NotFoundException;
import com.bbc.push.models.User;
import com.bbc.push.models.elements.CreatePushElement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Service layer for push actions
 *
 * @author Ahmad A. Furqan
 *
 */
public class PushService {

  private static final String PUSH_URL = "https://api.pushbullet.com/v2/pushes";

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private UserDao userDao;

  private ObjectMapper objectMapper = new ObjectMapper();

  public String push(String username, CreatePushElement element)
      throws RestClientException, JsonProcessingException, NotFoundException, BadRequestException {
    User user = userDao.getUser(username);
    if (user == null) {
      throw new NotFoundException("User does not exist");
    }
    HttpHeaders header = new HttpHeaders();
    header.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
    header.add("Access-Token", user.getAccessToken());
    try {
      ResponseEntity<String> response = restTemplate.exchange(PUSH_URL, HttpMethod.POST,
          new HttpEntity<>(objectMapper.writeValueAsString(element), header), String.class);
      if (response.getStatusCode().is2xxSuccessful()) {
        user.setNotificationsPushed(user.getNotificationsPushed() + 1);
        userDao.putUser(user);
      }
      return response.getBody();
    } catch (HttpClientErrorException ex) {
      throw new BadRequestException(ex.getMessage());
    }
  }

}
