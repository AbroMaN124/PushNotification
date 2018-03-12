package com.bbc.push.daos;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bbc.push.exceptions.ConflictException;
import com.bbc.push.models.User;

/**
 * {@link UserDao} that is backed by a map to store the data
 *
 * @author Ahmad A. Furqan
 *
 */
public class MapUserDao implements UserDao {

  private Map<String, User> users = new HashMap<String, User>();

  @Override
  public User addUser(User user) throws ConflictException {
    String username = user.getUsername();
    if (users.containsKey(username)) {
      throw new ConflictException("User with username " + username + " already exists");
    } else {
      users.put(username, user);
      return user;
    }
  }

  @Override
  public void putUser(User user) {
    users.put(user.getUsername(), user);
  }

  @Override
  public Collection<User> getUsers() {
    return users.values();
  }

  @Override
  public User getUser(String username) {
    return users.get(username);
  }

}
