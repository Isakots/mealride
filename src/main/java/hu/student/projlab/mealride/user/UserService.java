package hu.student.projlab.mealride.user;

import hu.student.projlab.mealride.config.Role;
import hu.student.projlab.mealride.config.RoleRepository;
import hu.student.projlab.mealride.exception.PasswordNotMatchingException;
import hu.student.projlab.mealride.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    void addUser(User user) {
        Role role = roleRepository.findByRole("ROLE_USER");
        user.getRoles().add(role);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Note: We have to retrieve the user from security context, because email-address can be edited from the browser
    // and that would cause an internal server error. This way we can avoid that.
    void editUser(User user){
            User currentUser = getCurrentUser();

            // Set new parameters
            currentUser.setFirstname(user.getFirstname());
            currentUser.setLastname(user.getLastname());
            currentUser.setPhone(user.getPhone());

            userRepository.save(currentUser);
    }


    List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    void changePassword(PasswordChanger changer) throws PasswordNotMatchingException{

        User currentUser = this.getCurrentUser();

        if(!bCryptPasswordEncoder.matches(changer.getCurrentPassword(),currentUser.getPassword()) )
            throw new PasswordNotMatchingException("Old password does not match. Password is not changed!");
        else {
            if(!changer.getNewPassword1().equals(changer.getNewPassword2())) {
                throw new PasswordNotMatchingException("New passwords do not match. Password is not changed!");
            } else
                currentUser.setPassword(bCryptPasswordEncoder.encode(changer.getNewPassword1()));
                userRepository.save(currentUser);
        }
    }

    void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Long getRestaurantId(Long userId) {
        return userRepository.findRestaurantIdByUserId(userId);
    }

    public void setUserToRestaurantAdmin(Restaurant restaurant, User user) {
        user.setRestaurant(restaurant);
        Role role = roleRepository.findByRole("ROLE_RESTWORKER");
        user.getRoles().add(role);
        role = roleRepository.findByRole("ROLE_RESTADMIN");
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public void setUserToRestaurantWorker(Restaurant restaurant, User user) {
        user.setRestaurant(restaurant);
        Role role = roleRepository.findByRole("ROLE_RESTWORKER");
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return findUserByEmail(name);
    }

}
