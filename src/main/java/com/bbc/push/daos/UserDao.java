package com.bbc.push.daos;

import java.util.Collection;

import com.bbc.push.exceptions.ConflictException;
import com.bbc.push.models.User;

/**
 * Data access object for accessing user data
 *
 * @author Ahmad A. Furqan
 *
 */
public interface UserDao {

  /**
   * Adds a new user, throws {@link ConflictException} if user already exists.
   *
   * @param user
   *          the user to add
   * @return the user, if added
   * @throws ConflictException
   *           if a user with the provided username already exists
   */
  public User addUser(User user) throws ConflictException;

  /**
   * Update a user, add if it does not already exist
   *
   * @param user
   *          the user to update/add
   */
  public void putUser(User user);

  /**
   * @return all users in a collection
   */
  public Collection<User> getUsers();

  /**
   *
   * @return the user for the given username, if one exists, null otherwise
   */
  public User getUser(String username);

}