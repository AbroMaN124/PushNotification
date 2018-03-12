package com.bbc.push.models.elements;

import java.time.LocalDateTime;

import com.bbc.push.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Element representation of a user model
 *
 * @author Ahmad A. Furqan
 *
 */
public class UserElement {

  private String username;
  private String accessToken;
  private LocalDateTime creationTime;
  private int numOfNotificationsPushed;

  public static UserElement fromModel(User user) {
    return new UserElement(user.getUsername(), user.getAccessToken(), user.getCreationTime(),
        user.getNotificationsPushed());
  }

  public UserElement() {
  }

  public UserElement(String username, String accessToken, LocalDateTime creationTime, int numOfNotificationsPushed) {
    this.username = username;
    this.accessToken = accessToken;
    this.creationTime = creationTime;
    this.numOfNotificationsPushed = numOfNotificationsPushed;
  }

  public String getUsername() {
    return username;
  }

  public UserElement setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public UserElement setAccessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  @JsonFormat(pattern = "yyy-MM-dd'T'HH:mm:ss")
  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public UserElement setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
    return this;
  }

  public int getNumOfNotificationsPushed() {
    return numOfNotificationsPushed;
  }

  public UserElement setNumOfNotificationsPushed(int numOfNotificationsPushed) {
    this.numOfNotificationsPushed = numOfNotificationsPushed;
    return this;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
    result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
    result = prime * result + numOfNotificationsPushed;
    result = prime * result + ((username == null) ? 0 : username.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    UserElement other = (UserElement) obj;
    if (accessToken == null) {
      if (other.accessToken != null)
        return false;
    } else if (!accessToken.equals(other.accessToken))
      return false;
    if (creationTime == null) {
      if (other.creationTime != null)
        return false;
    } else if (!creationTime.equals(other.creationTime))
      return false;
    if (numOfNotificationsPushed != other.numOfNotificationsPushed)
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

}
