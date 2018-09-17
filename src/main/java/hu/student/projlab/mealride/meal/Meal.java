package hu.student.projlab.mealride.meal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Meal {

    @Id
    private String name;
    private Integer price;
    private Boolean garnish;
    private String comment;

    public Meal() {
    }

    public Meal(String name, Integer price, Boolean garnish, String comment) {
        this.name = name;
        this.price = price;
        this.garnish = garnish;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getGarnish() {
        return garnish;
    }

    public void setGarnish(Boolean garnish) {
        this.garnish = garnish;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
