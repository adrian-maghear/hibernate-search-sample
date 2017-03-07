package hello;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Indexed
@AnalyzerDef(name = "customAddressanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
                        @Parameter(name = "language", value = "German")
                })
        })
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO)
    @Analyzer(definition = "customAddressanalyzer")
    private String street;

    @Field(index= Index.YES, analyze=Analyze.YES, store=Store.NO)
    private String city;

    private Integer number;

    public Address() {
    }

    public Address(String street, String city, Integer number) {
        this.street = street;
        this.city = city;
        this.number = number;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", number=" + number +
                '}';
    }
}
