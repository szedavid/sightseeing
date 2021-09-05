package com.szedavid.sightseeing.sightseeing.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// Using @Data for JPA entities is not recommended. It can cause severe performance and memory consumption issues.

@Entity
public class User {
    @Id
    //    @Size(max = 50)    // todo
    @Column(nullable = false)
    private String username;

    @NotNull
    //    @Size(max = 50)    // todo
    @Column(nullable = false)
    private String password;    // todo encrypt

    private Boolean isAdmin;    // todo is boolean best practice in DB?

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

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
