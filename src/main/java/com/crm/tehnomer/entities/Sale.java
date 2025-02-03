// package com.crm.tehnomer.entities;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.ManyToMany;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.Table;
// import lombok.Getter;
// import lombok.Setter;

// @Entity
// @Getter
// @Setter
// @Table(name = "sale")
// public class Sale {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private long id;

//     @Column(nullable = false)
//     private LocalDateTime dateSale;

//     @Column(length = 255, nullable = false)
//     private int count;

//     @Column(length = 255)
//     private int sum;

//     @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "sale")
//     private List<Product> products = new ArrayList();

// }
