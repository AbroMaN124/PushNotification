package com.bbc.push.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.bbc.push.daos.MapUserDao;
import com.bbc.push.daos.UserDao;
import com.bbc.push.services.PushService;
import com.bbc.push.services.UserService;

/**
 * Defines the context that can be used by other classes in the service
 *
 * @author Ahmad A. Furqan
 *
 */
public class ApplicationContext {

  @Bean
  public UserService userService() {
    return new UserService();
  }

  @Bean
  public PushService pushService() {
    return new PushService();
  }

  @Bean
  public UserDao userDao() {
    return new MapUserDao();
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

}
