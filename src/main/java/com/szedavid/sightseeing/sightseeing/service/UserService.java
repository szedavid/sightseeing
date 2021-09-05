package com.szedavid.sightseeing.sightseeing.service;

import com.szedavid.sightseeing.sightseeing.entity.User;
import com.szedavid.sightseeing.sightseeing.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  private final IUserRepository userRepository;

  @Autowired
  public UserService(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void fillWithDemoUsers() {
    User user = new User();
    user.setUsername("john");
    user.setPassword("john12");
    createUser(user);

    user = new User();
    user.setUsername("admin");
    user.setPassword("admin12");
    user.setAdmin(true);
    createUser(user);

    System.out.println("Users with 'user' and 'admin' level ready to use.");
  }

  public User createUser(User user) {
    if(findUser(user.getUsername()).isPresent()){
      // todo error?
      System.out.println("User name in use: " + user.getUsername());
      return null;
    }
    return userRepository.save(user);
  }

  public Optional<User> findUser(String username) {
    return userRepository.findByUsername(username);
  }
}
