package hu.student.projlab.mealride.order;


import hu.student.projlab.mealride.meal.Meal;
import hu.student.projlab.mealride.restaurant.Restaurant;
import hu.student.projlab.mealride.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private User customer;
    private Restaurant restaurant;
    private List<Meal> meals;
    private Integer price;
    private Timestamp datetime;
    private String couriername;
    private String usercomment;
    private String restaurantcomment;
   // private State state;  // enum type State


    public Order() {
    }

    public Order(User customer, Restaurant restaurant, List<Meal> meals, Integer price,
                 Timestamp datetime, String couriername, String usercomment, String restaurantcomment) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.meals = meals;
        this.price = price;
        this.datetime = datetime;
        this.couriername = couriername;
        this.usercomment = usercomment;
        this.restaurantcomment = restaurantcomment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public String getCouriername() {
        return couriername;
    }

    public void setCouriername(String couriername) {
        this.couriername = couriername;
    }

    public String getUsercomment() {
        return usercomment;
    }

    public void setUsercomment(String usercomment) {
        this.usercomment = usercomment;
    }

    public String getRestaurantcomment() {
        return restaurantcomment;
    }

    public void setRestaurantcomment(String restaurantcomment) {
        this.restaurantcomment = restaurantcomment;
    }
}
