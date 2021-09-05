package com.szedavid.sightseeing.sightseeing.service;

import com.szedavid.sightseeing.sightseeing.entity.User;
import com.szedavid.sightseeing.sightseeing.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

  private IUserRepository userRepository;
  private RoleService roleService;

  @Autowired
  public void setUserRepository(IUserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Autowired
  public void setRoleService(RoleService roleService){
    this.roleService = roleService;
  }

  public void fillWithDemoUsers() {
    User user = new User();
    user.setUsername("john");
    user.setPassword("$2a$12$qWD5QdzQZ8QCd5chaYME7Ou/TBDByqGibbQrndM7UlSxJbd2NL1T6");
    user.setRoles(Set.of(roleService.findByName("ROLE_USER")));
    create(user);

    user = new User();
    user.setUsername("admin");
    user.setPassword("$2a$12$njopzy7.ab1fOB4lIC/C.ewoZ9vKo21cgqfMy9cV32rYdQdw0E3Z6");
    // todo role hierarchy so admin contains user privileges
    user.setRoles(Set.of(roleService.findByName("ROLE_USER"), roleService.findByName("ROLE_ADMIN")));
    create(user);

    System.out.println("Users with 'user' and 'admin' level ready to use.");
  }

  public User create(User user) {
    if(findByUsername(user.getUsername()).isPresent()){
      // todo error?
      System.out.println("User name in use: " + user.getUsername());
      return null;
    }
    return userRepository.save(user);
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
