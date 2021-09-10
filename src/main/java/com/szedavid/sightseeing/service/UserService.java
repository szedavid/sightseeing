package com.szedavid.sightseeing.service;

import com.szedavid.sightseeing.entity.User;
import com.szedavid.sightseeing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

  private UserRepository userRepository;
  private RoleService roleService;

  @Autowired
  public void setUserRepository(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Autowired
  public void setRoleService(RoleService roleService){
    this.roleService = roleService;
  }

  public void fillWithDemoUsers() {
    var user = new User();
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
