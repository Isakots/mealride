package hu.student.projlab.mealride.bankcard;


import hu.student.projlab.mealride.user.User;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="CARD")
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="OWNER_NAME")
    private String ownername;
    @Column(name="EXPIRATION_DATE")
    private Date expriationdate;
    @Column(name="CVC")
    private Short cvc;  //has to hashed!

    @ManyToOne
    private User user;

    public BankCard() {
    }

    public BankCard(String ownername, Date expriationdate, Short cvc) {
        this.ownername = ownername;
        this.expriationdate = expriationdate;
        this.cvc = cvc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Short getCvc() {
        return cvc;
    }

    public void setCvc(Short cvc) {
        this.cvc = cvc;
    }
}
