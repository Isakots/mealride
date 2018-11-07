package hu.student.projlab.mealride.deliveryaddress;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ADDRESS")
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="ZIP")
    private Short zipcode;
    @Column(name="CITY")
    private String city;
    @Column(name="STREET")
    private String street;
    @Column(name="STATE")
    private String state;
    @Column(name="HOUSE_NUMBER")
    private Short housenumber;
    @Column(name="FLOOR")
    private Short floor;
    @Column(name="DOOR")
    private Short door;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public DeliveryAddress() {
    }

    public DeliveryAddress(Short zipcode, String city, String street, String state, Short housenumber, Short floor, Short door, User user) {
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
        this.state = state;
        this.housenumber = housenumber;
        this.floor = floor;
        this.door = door;
        this. user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getZipcode() {
        return zipcode;
    }

    public void setZipcode(Short zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Short getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(Short housenumber) {
        this.housenumber = housenumber;
    }

    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    public Short getDoor() {
        return door;
    }

    public void setDoor(Short door) {
        this.door = door;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
