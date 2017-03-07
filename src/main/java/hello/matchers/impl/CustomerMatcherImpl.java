package hello.matchers.impl;

import hello.Customer;
import hello.matchers.CustomerMatcher;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CustomerMatcherImpl implements CustomerMatcher {

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Customer> findByLastName(String lastName) {

        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Customer.class).get();

        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields("lastName")
                .matching(lastName)
                .createQuery();

        javax.persistence.Query jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, Customer.class);

        List<Customer> result = jpaQuery.getResultList();

        entityManager.close();

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Customer> findByFullName(String fullName) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Customer.class).get();

        org.apache.lucene.search.Query luceneQuery = qb
                .keyword()
                .onFields("fullName")
                .matching(fullName)
                .createQuery();

        javax.persistence.Query jpaQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery, Customer.class);

        List<Customer> result = jpaQuery.getResultList();

        entityManager.close();

        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Customer> findByFirstNameLastNameCityAndStreet(String firstName, String lastName, String city, String street) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Customer.class).get();

        org.apache.lucene.search.Query firstNameQuery = qb
                .keyword()
                .onFields("firstName")
                .matching(firstName)
                .createQuery();

        org.apache.lucene.search.Query lastNameQuery = qb
                .keyword().boostedTo(10)
                .onFields("lastName")
                .matching(lastName)
                .createQuery();

        org.apache.lucene.search.Query cityQuery = qb
                .keyword()
                .onFields("address.city")
                .matching(city)
                .createQuery();

        org.apache.lucene.search.Query streetQuery = qb
                .keyword().boostedTo(1)
                .onFields("address.street")
                .matching(street)
                .createQuery();

        Query query = qb.bool().should(firstNameQuery).should(lastNameQuery).should(cityQuery).should(streetQuery).createQuery();

        javax.persistence.Query jpaQuery =
                fullTextEntityManager.createFullTextQuery(query, Customer.class);

        List<Customer> result = jpaQuery.getResultList();

        entityManager.close();

        return result;
    }

}
