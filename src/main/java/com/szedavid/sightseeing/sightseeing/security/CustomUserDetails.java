package com.szedavid.sightseeing.sightseeing.security;

import com.szedavid.sightseeing.sightseeing.entity.Role;
import com.szedavid.sightseeing.sightseeing.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        super();
        this.user = user;

        System.out.println("CustomUserDetails username: " + user.getUsername() + "roles: " + user.getRoles().size());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> collection = new ArrayList<>();
        for (Role role: user.getRoles()) {
            collection.add(new SimpleGrantedAuthority(role.getName()));

            System.out.println("Auth: " + user.getUsername() + " - " + role.getName());
        }
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // Below checks are not supported

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
