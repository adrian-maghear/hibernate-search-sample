package com.adrian.maghear.samples.matchers.impl;

import com.adrian.maghear.samples.model.Order;
import com.adrian.maghear.samples.matchers.OrderMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class OrderMatcherImpl implements OrderMatcher {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Order> findByOrderNumber(String orderNumber) {
        return null;
    }

}
