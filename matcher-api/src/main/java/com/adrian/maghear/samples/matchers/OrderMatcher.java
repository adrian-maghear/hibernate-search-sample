package com.adrian.maghear.samples.matchers;

import com.adrian.maghear.samples.model.Order;

import java.util.List;

public interface OrderMatcher {

    List<Order> findByOrderNumber(String orderNumber);

}
