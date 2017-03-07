package hello.matchers;

import hello.Order;

import java.util.List;

public interface OrderMatcher {

    List<Order> findByOrderNumber(String orderNumber);

}
