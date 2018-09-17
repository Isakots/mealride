package hu.student.projlab.mealride.user;

import hu.student.projlab.mealride.bankcard.BankCard;
import hu.student.projlab.mealride.deliveryaddress.DeliveryAddress;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Person")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String password; //hash!
    private String email;
    private String phone;
    @OneToMany
    private List<DeliveryAddress> addresses;
    @OneToMany
    private List<BankCard> bankcards;

    public User() {

    }

    public User(String firstname, String lastname, String password, String email, String phone,
                List<DeliveryAddress> addresses, List<BankCard> bankcards) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.addresses = addresses;
        this.bankcards = bankcards;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<DeliveryAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<DeliveryAddress> addresses) {
        this.addresses = addresses;
    }

    public List<BankCard> getBankcards() {
        return bankcards;
    }

    public void setBankcards(List<BankCard> bankcards) {
        this.bankcards = bankcards;
    }
}
