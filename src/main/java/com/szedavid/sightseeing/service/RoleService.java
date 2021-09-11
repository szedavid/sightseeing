package com.szedavid.sightseeing.service;

import com.szedavid.sightseeing.model.Role;
import com.szedavid.sightseeing.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Inits the database by creating roles
     */
    // In a real project I would use Liquibase instead
    public void initForDemo() {
        var role = new Role();
        role.setName("ROLE_USER");
        createIfMissing(role);

        role = new Role();
        role.setName("ROLE_ADMIN");
        createIfMissing(role);

        System.out.println("'USER' and 'ADMIN' level roles are ready to use.");
    }

    public Role createIfMissing(Role role) {
        if (findByName(role.getName()) != null) {
            System.out.println("Role already exists: " + role.getName());
            return null;
        }
        return roleRepository.save(role);
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }
}
