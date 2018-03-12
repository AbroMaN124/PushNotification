package com.bbc.push.models.elements;

/**
 * Element representation of a user creation request
 *
 * @author Ahmad A. Furqan
 *
 */
public class UserCreationElement {

  private String username;
  private String accessToken;

  public UserCreationElement() {
  }

  public String getUsername() {
    return username;
  }

  public UserCreationElement setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public UserCreationElement setAccessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
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
    UserCreationElement other = (UserCreationElement) obj;
    if (accessToken == null) {
      if (other.accessToken != null)
        return false;
    } else if (!accessToken.equals(other.accessToken))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

}
