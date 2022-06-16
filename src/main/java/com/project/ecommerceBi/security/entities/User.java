package com.project.ecommerceBi.security.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Getter @Setter
    private String id;

    @NotNull
    @Column(unique = true)
    @Getter @Setter
    private String userName;

    @NotNull
    @Column(unique = true)
    @Getter @Setter
    private String email;

    @NotNull
    @Getter @Setter
    private String password;

    @NotNull
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Getter @Setter
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String id, String userName, String email, String password, Set<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
}
