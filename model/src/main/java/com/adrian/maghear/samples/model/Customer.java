package com.adrian.maghear.samples.model;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Parameter;

import javax.persistence.*;

@Entity
@Indexed
@AnalyzerDef(name = "customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "German")
                })
        })
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Field(index= Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Analyzer(definition = "customanalyzer")
    private String firstName;

    @Field(index= Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Analyzer(definition = "customanalyzer")
    private String middleName;

    @Field(index= Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Analyzer(definition = "customanalyzer")
    private String lastName;

    @IndexedEmbedded
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    protected Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    public Customer(String firstName, String middleName, String lastName, Address address) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
    }

    @Field(index= Index.YES, analyze=Analyze.YES, store=Store.NO)
    @Analyzer(definition = "customanalyzer")
    public String getFullName(){
        return middleName == null ? firstName + " " + lastName : firstName + " " + middleName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                '}';
    }
}
