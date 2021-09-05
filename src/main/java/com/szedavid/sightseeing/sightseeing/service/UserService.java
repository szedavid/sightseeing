package com.szedavid.sightseeing.sightseeing.service;

import com.szedavid.sightseeing.sightseeing.entity.User;
import com.szedavid.sightseeing.sightseeing.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    user.setPassword("john12"); // todo encode
    user.setRoles(List.of(roleService.findByName("ROLE_USER")));
    create(user);

    user = new User();
    user.setUsername("admin");
    user.setPassword("admin12"); // todo encode
    user.setRoles(Arrays.asList(roleService.findByName("ROLE_USER"), roleService.findByName("ROLE_ADMIN")));
    create(user);

    System.out.println("Users with 'user' and 'admin' level ready to use.");
  }

  public User create(User user) {
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
