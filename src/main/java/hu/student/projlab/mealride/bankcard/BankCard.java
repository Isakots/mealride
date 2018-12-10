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
    private Long expriationdate;

    @JsonIgnore
    @Column(name="CVC")
    private String cvc; // hashed

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name="CREATED_AT")
    private Long created_at;
    @Column(name="DELETED_AT")
    private Long deleted_at;

    public BankCard() {
    }

    public BankCard(Long number, String ownername, Long expriationdate, String cvc, User user, Long created_at, Long deleted_at) {
        this.number = number;
        this.ownername = ownername;
        this.expriationdate = expriationdate;
        this.cvc = cvc;
        this.user = user;
        this.created_at = created_at;
        this.deleted_at = deleted_at;
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

    public Long getExpriationdate() {
        return expriationdate;
    }

    public void setExpriationdate(Long expriationdate) {
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

    public Long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Long created_at) {
        this.created_at = created_at;
    }

    public Long getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Long deleted_at) {
        this.deleted_at = deleted_at;
    }
}
