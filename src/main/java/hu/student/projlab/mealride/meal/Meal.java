package hu.student.projlab.mealride.meal;

import javax.persistence.*;


@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="MEAL_NAME")
    private String name;
    @Column(name="PRICE")
    private Integer price;
    @Column(name="IS_GARNISH")
    private Boolean isgarnish;
    @Column(name="MEAL_COMMENT")
    private String comment;

    public Meal() {
    }

    public Meal(String name, Integer price, Boolean isgarnish, String comment) {
        this.name = name;
        this.price = price;
        this.isgarnish = isgarnish;
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

    public Boolean isGarnish() {
        return isgarnish;
    }

    public void setGarnish(Boolean isgarnish) {
        this.isgarnish = isgarnish;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
