package com.szedavid.sightseeing.sightseeing.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Collection;

// Using @Data for JPA entities is not recommended. It can cause severe performance and memory consumption issues.

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    //    @Size(max = 50)    // todo
    private String username;

    @NotNull
    //    @Size(max = 100)    // todo
    private String password; // todo encrypt

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
