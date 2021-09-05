package com.szedavid.sightseeing.sightseeing.service;

import com.szedavid.sightseeing.sightseeing.entity.Role;
import com.szedavid.sightseeing.sightseeing.entity.Role;
import com.szedavid.sightseeing.sightseeing.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

  private final IRoleRepository roleRepository;

  @Autowired
  public RoleService(IRoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  // todo másképp?
  public void init() {
    Role role = new Role();
    role.setName("ROLE_USER");
    create(role);

    role = new Role();
    role.setName("ROLE_ADMIN");
    create(role);

    System.out.println("'USER' and 'ADMIN' level roles are ready to use.");
  }

  public Role create(Role role) {
    if(findByName(role.getName()) != null){
      // todo error?
      System.out.println("Role already exists: " + role.getName());
      return null;
    }
    return roleRepository.save(role);
  }

  public Role findByName(String name) {
    return roleRepository.findByName(name).orElse(null);
  }
}
