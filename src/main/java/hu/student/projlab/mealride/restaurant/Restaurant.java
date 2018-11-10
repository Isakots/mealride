package hu.student.projlab.mealride.restaurant;

import hu.student.projlab.mealride.meal.Meal;
import hu.student.projlab.mealride.user.User;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="RESTAURANT_NAME")
    private String name;
    @Column(name="AVERAGE_DELIVERY_TIME")
    private String avgdeliverytime;
    @Column(name="MINIMUM_ORDER_PRICE")
    private Short minorderprice;
    @Column(name="DELIVERY_PRICE")
    private Short deliveryprice;

    @Embedded
    private ShoppingHours hours;

    @ElementCollection
    @CollectionTable(name = "MENU", joinColumns = @JoinColumn(name = "RESTAURANT_ID"))
    @Column(name = "MENU")
    private List<Meal> menu;

    @OneToMany
    @JoinTable(name = "RESTAURANT_WORKERS",
            joinColumns = {@JoinColumn(name = "RESTAURANT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "WORKER_ID")})
    @Column(name = "WORKER_ID")
    private List<User> workers;


    public Restaurant() {
    }

    public Restaurant(String name, String avgdeliverytime, Short minorderprice, Short deliveryprice,
                      ShoppingHours hours) {
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

    public String getAvgdeliverytime() {
        return avgdeliverytime;
    }

    public void setAvgdeliverytime(String avgdeliverytime) {
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

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }
}
