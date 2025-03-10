package com.crm.tehnomer.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255)
    private String model;

    @Column(length = 1000)
    private String description;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    private Integer guarantee;

    @Column(nullable = false)
    private boolean deprecated;

    // в mappedBy ссылка на поле product в таблице Stock
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
     orphanRemoval = true, mappedBy = "product")
    private List<Stock> stocks;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
     orphanRemoval = true, mappedBy = "product")
    private List<Meter> meters;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
     orphanRemoval = true, mappedBy = "product")
    private List<OrderProduct> orderProducts;


    public Product(String name) {
        this.name = name;
    }

    public Product() {
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
        Product other = (Product) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
    }

}
