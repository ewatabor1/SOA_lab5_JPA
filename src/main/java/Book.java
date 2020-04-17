import clojure.lang.IFn;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="book")
public class Book {
    private Integer id;
    private String lastName;
    private String firstName;
    private BigInteger numberISBN;
    private Integer year;
    private Double price;

    public Book() {
        super();
    }
    public Book(String lastName, String firstName, BigInteger numberISBN, Integer year, Double price){
        this.lastName=lastName;
        this.firstName=firstName;
        this.numberISBN=numberISBN;
        this.year=year;
        this.price=price;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable=false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "lastName", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "firstName", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    @Column(name = "numberISBN", nullable = false, unique = true, columnDefinition = "numeric(13,0)")
    public BigInteger getNumberISBN() {
        return numberISBN;
    }

    public void setNumberISBN(BigInteger numberISBN) {
        this.numberISBN = numberISBN;
    }

    @Column(name = "year", nullable = false)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Column(name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
