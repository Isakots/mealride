package hu.student.projlab.mealride.restaurant;

import hu.student.projlab.mealride.meal.Meal;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Time;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Time avgdeliverytime;
    private Short minorderprice;
    private Short deliveryprice;

    // Shopping hours
    private Time opentime;
    private Time closetime;

    @ElementCollection
    private List<Meal> menu;

    //private ShoppingHours hours;

    public Restaurant() {
    }


    public Restaurant(String name, Time avgdeliverytime, Short minorderprice, Short deliveryprice, Time opentime, Time closetime, List<Meal> menu) {
        this.name = name;
        this.avgdeliverytime = avgdeliverytime;
        this.minorderprice = minorderprice;
        this.deliveryprice = deliveryprice;
        this.opentime = opentime;
        this.closetime = closetime;
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

    public Time getOpentime() {
        return opentime;
    }

    public void setOpentime(Time opentime) {
        this.opentime = opentime;
    }

    public Time getClosetime() {
        return closetime;
    }

    public void setClosetime(Time closetime) {
        this.closetime = closetime;
    }

    public List<Meal> getMenu() {
        return menu;
    }

    public void setMenu(List<Meal> menu) {
        this.menu = menu;
    }
}
