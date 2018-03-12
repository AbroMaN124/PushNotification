package com.bbc.push;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.bbc.push.config.ApplicationContext;

/**
 * Convenient way to run the spring application
 *
 * @author Ahmad A. Furqan
 *
 */
@SpringBootApplication
@Import(ApplicationContext.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
