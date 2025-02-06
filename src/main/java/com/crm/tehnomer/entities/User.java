package com.crm.tehnomer.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.crm.tehnomer.entities.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 30, unique = true, nullable = false)
    private String username;

    @Column(length = 255)
    private String name;

    @Column(length = 255)
    private String surname;

    @Column(length = 255)
    private String type;

    @Email
    @Column(length = 255, unique = true, nullable = false)
    private String email;

    @Column(length = 1500, nullable = false)
    private String password;

    @Column(length = 255)
    private String information;

    @Column(nullable = false)
    private LocalDateTime dateJoined;

    @Column(nullable = false)
    private LocalDateTime dateLastEnter;

    @Column(nullable = false)
    private boolean active;

    private int phone;

    // здесь старший продажник и младший
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "users_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE,
    orphanRemoval=true, mappedBy="client")
    private List<Order> clientOrders;

    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE,
    orphanRemoval=true, mappedBy="saler")
    private List<Order> salerOrders;

    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE,
    orphanRemoval=true, mappedBy="client")
    private List<Meter> clientMeters;

    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE,
    orphanRemoval=true, mappedBy="installer")
    private List<Meter> installerMeters;

    @PrePersist
    private void init() {
        dateJoined = LocalDateTime.now();
        dateLastEnter = LocalDateTime.now();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();

    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.roles.add(role);
    }

    public User() {

    }

    @Override
    public String getUsername() {
        return username;
    }

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
        return active;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user))
            return false;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }

}
