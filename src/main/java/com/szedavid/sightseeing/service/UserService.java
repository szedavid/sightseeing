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

    // In a real project user cration should be more sophisticated

    /**
     * Creates a new user if username is not already in use.
     *
     * @param user The user to create
     * @return The created user or null if username is in use
     */
    public User create(User user) {
        if (findByUsername(user.getUsername()).isPresent()) {
            logger.debug("User name already exists: {}", user.getUsername());
            return null;
        }
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
