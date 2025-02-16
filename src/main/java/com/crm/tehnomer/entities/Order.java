package com.crm.tehnomer.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.crm.tehnomer.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 1000)
    private String info;

    @Column(length = 255)
    private String address;

    @Column(nullable = false)
    private LocalDateTime dateCreate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_client")
    private User client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_saler")
    private User saler;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE,
     orphanRemoval = true, mappedBy = "order")
    private List<OrderProduct> orderProducts;

    @PrePersist
    private void init() {
        dateCreate = LocalDateTime.now();
    }

    public Order(OrderStatus status, User client, User saler) {
        this.status = status;
        this.client = client;
        this.saler = saler;
    }

    public Order() {
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
        Order other = (Order) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", info=" + info + ", address=" + address + ", dateCreate=" + dateCreate
                + ", status=" + status + ", client=" + client + ", saler=" + saler + "]";
    }

}
