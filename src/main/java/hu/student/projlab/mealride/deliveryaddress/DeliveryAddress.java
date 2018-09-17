package hu.student.projlab.mealride.deliveryaddress;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Address")
public class DeliveryAddress {

    @Id
    private String name;
    private Short zipcode;
    private String settlement;
    private String street;
    private Short housenumber;
    private Short floor;
    private Short door;

    public DeliveryAddress() {
    }

    public DeliveryAddress(String name, Short zipcode, String settlement, String street, Short housenumber, Short floor, Short door) {
        this.name = name;
        this.zipcode = zipcode;
        this.settlement = settlement;
        this.street = street;
        this.housenumber = housenumber;
        this.floor = floor;
        this.door = door;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getZipcode() {
        return zipcode;
    }

    public void setZipcode(Short zipcode) {
        this.zipcode = zipcode;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
}
