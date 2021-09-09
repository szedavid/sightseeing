package com.szedavid.sightseeing.sightseeing.repository;

import com.szedavid.sightseeing.sightseeing.entity.Role;
import com.szedavid.sightseeing.sightseeing.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}