package com.szedavid.sightseeing.service;

import com.szedavid.sightseeing.model.Role;
import com.szedavid.sightseeing.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Role related logic.
 */
@Service
public class RoleService {
    private final Logger logger = LoggerFactory.getLogger(RoleService.class);

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

        logger.debug("'USER' and 'ADMIN' level roles are ready to use.");
    }

    /**
     * Creates the given role if not present yet.
     *
     * @param role The role to create
     * @return The created role
     */
    public Role createIfMissing(Role role) {
        if (findByName(role.getName()) != null) {
            System.out.println("Role already exists: " + role.getName());
            return null;
        }
        return roleRepository.save(role);
    }

    /**
     * Finds a role by its name.
     *
     * @param name The name of the role to be found
     * @return The found role or null
     */
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElse(null);
    }
}
