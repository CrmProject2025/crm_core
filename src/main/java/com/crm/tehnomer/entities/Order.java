package com.crm.tehnomer.entities;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @Column(length = 255)
    private LocalDateTime date_create;

    @Column(length = 255)
    private Enum() status;
    //  создать енам для статусов заказа


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_client")
    private User client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id_saler")
    private User saler;

    //  еще поле для связи с order_product manytoone



}
