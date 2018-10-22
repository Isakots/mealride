package hu.student.projlab.mealride.config;

import hu.student.projlab.mealride.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class CustomUserDetails implements UserDetails {

    private User user;

    @Autowired
    private RoleRepository roleRepository;

    public CustomUserDetails(User user) {
        this.user = user;  }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new HashSet<>();
        //System.out.println(this.user.getRoles());
       // authorities.addAll(this.user.getRoles());
         authorities.add(new GrantedAuthority() {
             @Override
             public String getAuthority() {
                 return "ROLE_ADMIN";
             }
         });
        //return authorities;
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

