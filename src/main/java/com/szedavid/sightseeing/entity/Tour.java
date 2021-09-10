package com.szedavid.sightseeing.entity;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

// Using @Data for JPA entities is not recommended. It can cause severe performance and memory consumption issues.

@Entity
public class Tour {
    @Id
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(nullable = false)
    private String name;

    @Size(max = 5000)
    private String longDesc;

    public Long getId() {
        return id;
    }

    // todo del ?
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }
}
