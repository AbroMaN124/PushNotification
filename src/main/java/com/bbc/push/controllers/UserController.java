package com.bbc.push.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bbc.push.exceptions.BadRequestException;
import com.bbc.push.exceptions.ConflictException;
import com.bbc.push.models.elements.UserCreationElement;
import com.bbc.push.models.elements.UserElement;
import com.bbc.push.services.UserService;

/**
 * Controller for user actions
 *
 * @author Ahmad A. Furqan
 *
 */
@RestController
@RequestMapping("api")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public UserElement registerUser(@RequestBody UserCreationElement createUserElement)
      throws ConflictException, BadRequestException {
    return userService.createUser(createUserElement.getUsername(), createUserElement.getAccessToken());
  }

  @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<UserElement> getUsers() {
    return userService.getUsers();
  }

}
