package com.adrian.maghear.samples.model;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @IndexedEmbedded
    @ManyToOne
    private Customer customer;

    @Field
    private String orderNumber;

    @OneToMany
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(Customer customer, String orderNumber) {
        this.customer = customer;
        this.orderNumber = orderNumber;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

}
