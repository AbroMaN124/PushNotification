package com.bbc.push.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.bbc.push.daos.UserDao;
import com.bbc.push.exceptions.BadRequestException;
import com.bbc.push.exceptions.ConflictException;
import com.bbc.push.models.User;
import com.bbc.push.models.elements.UserElement;

/**
 * Service layer for user actions
 *
 * @author Ahmad A. Furqan
 *
 */
public class UserService {

  @Autowired
  private UserDao userDao;

  /**
   * Create a new user with the given parameters
   *
   * @param username
   *          the username of the user
   * @param accessToken
   *          the pushbullet access token of the user
   * @return the create user as a {@link UserElement}
   * @throws ConflictException
   *           if user already exists
   * @throws BadRequestException
   *           if there is mandatory field(s) missing
   */
  public UserElement createUser(String username, String accessToken) throws ConflictException, BadRequestException {
    if (username == null || accessToken == null) {
      throw new BadRequestException("Missing mandatory field");
    }
    return UserElement.fromModel(userDao.addUser(new User(username, accessToken)));
  }

  /**
   * @return All users as a list of {@link UserElement}s
   */
  public List<UserElement> getUsers() {
    return userDao.getUsers().stream().map(user -> UserElement.fromModel(user)).collect(Collectors.toList());
  }

}
