package com.crm.tehnomer.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "meter")
public class Meter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255)
    private String address;

    @Column(nullable = false)
    private LocalDateTime dateInstall;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_client")
    private User client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_installer")
    private User installer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
     orphanRemoval = true, mappedBy = "meter")
    private List<Measurement> measurements;


    @PrePersist
    private void init() {
        dateInstall = LocalDateTime.now();
    }

    public Meter(String address, User client, User installer, Product product) {
        this.address = address;
        this.client = client;
        this.installer = installer;
        this.product = product;
    }

    public Meter() {
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
        Meter other = (Meter) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Meter [id=" + id + ", address=" + address + ", dateInstall=" + dateInstall + ", client=" + client
                + ", installer=" + installer + ", product=" + product + "]";
    }

    
}
