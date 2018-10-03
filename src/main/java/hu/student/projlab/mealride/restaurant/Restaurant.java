package hu.student.projlab.mealride.restaurant;

import hu.student.projlab.mealride.meal.Meal;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name="RESTAURANT_NAME")
    private String name;
    @Column(name="AVERAGE_DELIVERY_TIME")
    private Time avgdeliverytime;
    @Column(name="MINIMUM_ORDER_PRICE")
    private Short minorderprice;
    @Column(name="DELIVERY_PRICE")
    private Short deliveryprice;

    @Embedded
    private ShoppingHours hours;

    @ElementCollection
    private List<Meal> menu;


    public Restaurant() {
    }

    public Restaurant(String name, Time avgdeliverytime, Short minorderprice, Short deliveryprice,
                      ShoppingHours hours, List<Meal> menu) {
        this.name = name;
        this.avgdeliverytime = avgdeliverytime;
        this.minorderprice = minorderprice;
        this.deliveryprice = deliveryprice;
        this.hours = hours;
        this.menu = menu;
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

    public List<Meal> getMenu() {
        return menu;
    }

    public void setMenu(List<Meal> menu) {
        this.menu = menu;
    }
}
