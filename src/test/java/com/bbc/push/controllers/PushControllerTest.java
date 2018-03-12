package com.bbc.push.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bbc.push.exceptions.BadRequestException;
import com.bbc.push.exceptions.NotFoundException;
import com.bbc.push.models.elements.CreatePushElement;
import com.bbc.push.services.PushService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * Tests {@link PushController}
 *
 * @author Ahmad A. Furqan
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PushControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private PushService pushService;

  private ObjectMapper mapper;

  @Before
  public void setUp() {
    mapper = new ObjectMapper();
  }

  @Test
  public void pushNotificationSuccesfully() throws Exception {

    CreatePushElement request = new CreatePushElement().setType("note").setTitle("title").setBody("body");
    String expectedResponse = "responseBody";

    when(pushService.push("user1", request)).thenReturn(expectedResponse);

    mvc.perform(MockMvcRequestBuilders.post("/api/push/user1").contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().string(expectedResponse));
  }

  @Test
  public void pushNotification() throws Exception {

    CreatePushElement request = new CreatePushElement().setType("note").setTitle("title").setBody("body");

    when(pushService.push("unregistered", request)).thenThrow(new NotFoundException("User not found"));

    mvc.perform(MockMvcRequestBuilders.post("/api/push/unregistered").contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void pushNotificationGetsBadResponse() throws Exception {

    CreatePushElement request = new CreatePushElement().setType("note").setTitle("title").setBody("body");

    when(pushService.push("user1", request)).thenThrow(new BadRequestException("Bad request"));

    mvc.perform(MockMvcRequestBuilders.post("/api/push/user1").contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}
