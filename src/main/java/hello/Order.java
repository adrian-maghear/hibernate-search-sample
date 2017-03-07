package hello;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @IndexedEmbedded
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Field
    private String orderNumber;

    public Order() {
    }

    public Order(Customer customer, String orderNumber) {
        this.customer = customer;
        this.orderNumber = orderNumber;
    }

}
