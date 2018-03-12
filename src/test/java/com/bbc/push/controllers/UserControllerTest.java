package com.bbc.push.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.bbc.push.exceptions.ConflictException;
import com.bbc.push.models.elements.UserCreationElement;
import com.bbc.push.models.elements.UserElement;
import com.bbc.push.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 *
 * Tests {@link UserController}
 *
 * @author Ahmad A. Furqan
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;

  private ObjectMapper mapper;

  @Before
  public void setUp() {
    mapper = new ObjectMapper();
  }

  @Test
  public void registerUserSuccessfully() throws Exception {

    // LocalDateTime not correctly mapped without registering JavaTimeModule
    mapper.registerModule(new JavaTimeModule());

    UserCreationElement request = new UserCreationElement().setUsername("test").setAccessToken("accessToken01");
    UserElement expectedResponse = new UserElement().setUsername("test").setAccessToken("accessToken01")
        .setCreationTime(LocalDateTime.now()).setNumOfNotificationsPushed(0);

    when(userService.createUser("test", "accessToken01")).thenReturn(expectedResponse);

    mvc.perform(MockMvcRequestBuilders.post("/api/user").contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
        .andExpect(content().string(mapper.writeValueAsString(expectedResponse)));
  }

  @Test
  public void registerUserReturnsConflict() throws Exception {
    UserCreationElement request = new UserCreationElement().setUsername("test").setAccessToken("accessToken01");

    when(userService.createUser("test", "accessToken01"))
        .thenThrow(new ConflictException("User with username test already exists"));

    mvc.perform(MockMvcRequestBuilders.post("/api/user").contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isConflict());
  }

  @Test
  public void registerUserReturnsBadRequest() throws Exception {
    UserCreationElement request = new UserCreationElement().setAccessToken("access");

    when(userService.createUser(null, "access")).thenThrow(new BadRequestException("Missing mandontary field"));

    mvc.perform(MockMvcRequestBuilders.post("/api/user").contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request)).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void getAllUsers() throws Exception {
    // LocalDateTime not correctly mapped without registering JavaTimeModule
    mapper.registerModule(new JavaTimeModule());

    LocalDateTime workingDateTime = LocalDateTime.now();
    UserElement one = new UserElement("username", "accessToken", workingDateTime, 0);
    UserElement two = new UserElement("testUser", "accessToken01", workingDateTime, 2);
    List<UserElement> users = new ArrayList<UserElement>() {
      {
        add(one);
        add(two);
      }
    };
    when(userService.getUsers()).thenReturn(users);

    mvc.perform(MockMvcRequestBuilders.get("/api/user").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().string(mapper.writeValueAsString(users)));
  }

  @Test
  public void getAllUsersWhenNoUsersExist() throws Exception {
    // LocalDateTime not correctly mapped without registering JavaTimeModule
    mapper.registerModule(new JavaTimeModule());

    LocalDateTime workingDateTime = LocalDateTime.now();
    List<UserElement> users = new ArrayList<UserElement>();
    when(userService.getUsers()).thenReturn(users);

    mvc.perform(MockMvcRequestBuilders.get("/api/user").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(content().string("[]"));
  }
}
