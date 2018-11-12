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
    private int price;
    @Column(name="MEAL_COMMENT")
    private String comment;

    public Meal() {
    }

    public Meal(String name, int price, String comment) {
        this.name = name;
        this.price = price;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
