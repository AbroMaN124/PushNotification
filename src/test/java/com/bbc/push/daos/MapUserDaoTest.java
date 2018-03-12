package com.bbc.push.daos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bbc.push.exceptions.ConflictException;
import com.bbc.push.models.User;

/**
 * Tests {@link MapUserDao}
 *
 * @author Ahmad A. Furqan
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapUserDaoTest {

  private MapUserDao userDao;

  @Before
  public void setUp() {
    // use a new instance of MapUserDao for each test
    userDao = new MapUserDao();
  }

  @Test
  public void addNewUser() throws Exception {
    userDao.addUser(new User("username", "abc"));
  }

  @Test(expected = ConflictException.class)
  public void addDuplicateUser() throws Exception {
    userDao.addUser(new User("test123", "abcde"));
    userDao.addUser(new User("test123", "abcde"));
  }

  @Test
  public void getUsers() throws Exception {
    Collection<User> result = userDao.getUsers();
    assertEquals(0, result.size());
  }

  @Test
  public void addThenGetUsers() throws Exception {
    Collection<User> result = userDao.getUsers();
    userDao.addUser(new User("user1", "abc"));
    userDao.addUser(new User("user2", "abcd"));
    assertEquals(2, result.size());
  }

  @Test
  public void putNewUser() {
    userDao.putUser(new User("hello", "world"));
  }

  @Test
  public void addAndUpdateUser() {
    userDao.putUser(new User("hello", "world"));
    userDao.putUser(new User("hello", "world2"));
  }

  @Test
  public void getUserThatDoesNotExist() {
    User result = userDao.getUser("user");
    assertNull(result);
  }

  @Test
  public void getUserThatExists() throws ConflictException {
    User input = new User("user", "at");
    userDao.addUser(input);
    User result = userDao.getUser("user");
    assertEquals(input, result);
  }

}
