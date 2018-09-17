package hu.student.projlab.mealride.restaurant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Time avgdeliverytime;
    private Short minorderprice;
    private Short deliveryprice;
    private ShoppingHours hours;

    public Restaurant() {
    }

    public Restaurant(String name, Time avgdeliverytime, Short minorderprice, Short deliveryprice, ShoppingHours hours) {
        this.name = name;
        this.avgdeliverytime = avgdeliverytime;
        this.minorderprice = minorderprice;
        this.deliveryprice = deliveryprice;
        this.hours = hours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getAvgdeliverytime() {
        return avgdeliverytime;
    }

    public void setAvgdeliverytime(Time avgdeliverytime) {
        this.avgdeliverytime = avgdeliverytime;
    }

    public Short getMinorderprice() {
        return minorderprice;
    }

    public void setMinorderprice(Short minorderprice) {
        this.minorderprice = minorderprice;
    }

    public Short getDeliveryprice() {
        return deliveryprice;
    }

    public void setDeliveryprice(Short deliveryprice) {
        this.deliveryprice = deliveryprice;
    }

    public ShoppingHours getHours() {
        return hours;
    }

    public void setHours(ShoppingHours hours) {
        this.hours = hours;
    }
}
