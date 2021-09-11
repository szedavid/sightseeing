package com.szedavid.sightseeing.model;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

// Using @Data for JPA entities is not recommended. It can cause severe performance and memory consumption issues.

@Entity
public class Tour {
    @Id
    private Long id;

    @NotNull
    @Length(max = 250)
    @Column(nullable = false)
    private String name;

    @Length(max = 5000)
    private String longDesc;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return "Tour{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", longDesc='" + longDesc + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return id.equals(tour.id) && name.equals(tour.name) && Objects.equals(longDesc, tour.longDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, longDesc);
    }
}
