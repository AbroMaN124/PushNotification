package com.bbc.push.models;

import java.time.LocalDateTime;

/**
 * Models a user
 *
 * @author Ahmad A. Furqan
 *
 */
public class User {

  private String username;
  private String accessToken;
  private LocalDateTime creationTime;
  private int notificationsPushed;

  public User(String username, String accessToken) {
    this.username = username;
    this.accessToken = accessToken;
    creationTime = LocalDateTime.now();
    notificationsPushed = 0;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public int getNotificationsPushed() {
    return notificationsPushed;
  }

  public void setNotificationsPushed(int notificationsPushed) {
    this.notificationsPushed = notificationsPushed;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
    result = prime * result + ((creationTime == null) ? 0 : creationTime.hashCode());
    result = prime * result + notificationsPushed;
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
    User other = (User) obj;
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
    if (notificationsPushed != other.notificationsPushed)
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

}
