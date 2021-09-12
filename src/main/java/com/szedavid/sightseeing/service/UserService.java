package com.szedavid.sightseeing.service;

import com.szedavid.sightseeing.model.User;
import com.szedavid.sightseeing.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * User related logic.
 */
@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private RoleService roleService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Inits the database by creating required users for the demo
     */
    public void initForDemo() {
        var user = new User();
        user.setUsername("john");
        user.setPassword("john12");
        user.setRoles(Set.of(roleService.findByName("ROLE_USER")));
        create(user);

        user = new User();
        user.setUsername("admin");
        user.setPassword("admin12");
        // In a real project we could use role hierarchy so admin gets user privileges automatically
        user.setRoles(Set.of(roleService.findByName("ROLE_USER"), roleService.findByName("ROLE_ADMIN")));
        create(user);

        logger.debug("Users with 'user' and 'admin' level ready to use.");
    }

    // In a real project user cration should be more sophisticated
    public User create(User user) {
        if (findByUsername(user.getUsername()).isPresent()) {
            System.out.println("User name in use: " + user.getUsername());
            return null;
        }
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
