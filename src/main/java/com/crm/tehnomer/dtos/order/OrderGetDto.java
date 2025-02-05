package com.crm.tehnomer.dtos.order;

import java.time.LocalDateTime;

import com.crm.tehnomer.entities.enums.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class OrderGetDto {


    private long id;

    private String info;

    private String address;

    private LocalDateTime dateCreate;

    private OrderStatus status;

    

    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "user_id_client")
    // private User client;

    // @ManyToOne(fetch = FetchType.EAGER)
    // @JoinColumn(name = "user_id_saler")
    // private User saler;
    
}
