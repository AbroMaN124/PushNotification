package com.bbc.push.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.bbc.push.exceptions.BadRequestException;
import com.bbc.push.exceptions.NotFoundException;
import com.bbc.push.models.elements.CreatePushElement;
import com.bbc.push.services.PushService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Controller for push actions
 *
 * @author Ahmad A. Furqan
 *
 */
@RestController
@RequestMapping("api")
public class PushController {

  @Autowired
  private PushService pushService;

  @RequestMapping(value = "/push/{username}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public String createPush(@PathVariable String username, @RequestBody CreatePushElement element)
      throws RestClientException, JsonProcessingException, NotFoundException, BadRequestException {
    return pushService.push(username, element);
  }

}
