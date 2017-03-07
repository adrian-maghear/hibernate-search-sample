package hello.matchers.impl;

import hello.Order;
import hello.matchers.OrderMatcher;
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
