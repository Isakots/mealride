package hu.student.projlab.mealride.order;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride.cart.CartItem;
import hu.student.projlab.mealride.meal.Meal;
import hu.student.projlab.mealride.restaurant.Restaurant;
import hu.student.projlab.mealride.user.User;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name="ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ORDER_ID")
    private Long id;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="CUSTOMER_ORDERS", joinColumns = { @JoinColumn(name="ORDER_ID")},
            inverseJoinColumns = { @JoinColumn(name="CUSTOMER_ID")})
    private List<User> customers;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name="RESTAURANT_ORDERS", joinColumns = { @JoinColumn(name="ORDER_ID")},
            inverseJoinColumns = { @JoinColumn(name="RESTAURANT_ID")})
    private List<Restaurant> restaurants;

    @ElementCollection
    @JoinTable(name="ORDER_MEALS", joinColumns = { @JoinColumn(name="ORDER_ID")},
            inverseJoinColumns = { @JoinColumn(name="CARTITEM_ID")})
    private List<CartItem> meals;

    @Column(name="PRICE")
    private int price;
    @Column(name="ORDER_TIME")
    private Date datetime;
    @Column(name="COURIER_NAME")
    private String couriername;
    @Column(name="CUSTOMER_COMMENT")
    private String usercomment;
    @Column(name="WORKER_COMMENT")
    private String restaurantcomment;
   // private State state;  // enum type State


    public Order() {
    }

    public Order(List<User> customer, List<Restaurant> restaurant, List<CartItem> meals, int price,
                 Date datetime, String couriername, String usercomment, String restaurantcomment) {
       // this.customer = customer;
        //this.restaurant = restaurant;
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

    public List<User> getCustomers() {
        return customers;
    }

    public void setCustomer(List<User> customers) {
        this.customers = customers;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurant(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<CartItem> getMeals() {
        return meals;
    }

    public void setMeals(List<CartItem> meals) {
        this.meals = meals;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {

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
