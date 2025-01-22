package com.crm.tehnomer.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String type;

    @Column(length = 255, nullable = false)
    private String email;

    private int phone;

    @PrePersist
    private void init() {
        dateRegister = LocalDateTime.now();
        dateLastEnter = LocalDateTime.now();
    }

    @Column(name = "date_register", nullable = false)
    private LocalDateTime dateRegister;

    @Column(nullable = false)
    private LocalDateTime dateLastEnter;

    @Column(nullable = false)
    private boolean active;

    public Client(String name, String type, String email) {
        this.name = name;
        this.type = type;
        this.email = email;
    }

    public Client() {

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Client [id=" + id + ", name=" + name + ", type=" + type + ", email=" + email + ", phone=" + phone + "]";
    }

}
