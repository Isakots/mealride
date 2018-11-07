package hu.student.projlab.mealride.bankcard;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride.user.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="CARD")
public class BankCard {

    @Id
    private Long number;

    @JsonIgnore
    @Column(name="OWNER_NAME")
    private String ownername;


    @Column(name="EXPIRATION_DATE")
    private Date expriationdate;

    @JsonIgnore
    @Column(name="CVC")
    private String cvc; // hashed

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    public BankCard() {
    }

    public BankCard(String ownername, Date expriationdate, String cvc) {
        this.ownername = ownername;
        this.expriationdate = expriationdate;
        this.cvc = cvc;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public Date getExpriationdate() {
        return expriationdate;
    }

    public void setExpriationdate(Date expriationdate) {
        this.expriationdate = expriationdate;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
