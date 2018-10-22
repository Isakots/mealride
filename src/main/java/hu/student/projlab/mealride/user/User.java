package hu.student.projlab.mealride.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.student.projlab.mealride.config.Role;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="USER_ID")
    private Long id;
    @Column(name="FIRSTNAME")
    private String firstname;
    @Column(name="LASTNAME")
    private String lastname;
    @JsonIgnore
    @Column(name="PASSWORD")
    private String password; //hash
    @Column(name="EMAIL")
    private String email;
    @Column(name="PHONE")
    private String phone;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="USER_ROLES", joinColumns = { @JoinColumn(name="USER_ID")},
                inverseJoinColumns = { @JoinColumn(name="ROLE_ID")})
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(String firstname, String lastname, String password, String email, String phone, Set<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
