package hu.student.projlab.mealride.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    // Note: We have to retrieve the user from security context, because email-address can be edited from the browser
    // and that would cause an internal server error. This way we can avoid that.
    public void editUser(User user){

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();
            User dbuser = findUserByEmail(name);

            // Set new parameters
            dbuser.setFirstname(user.getFirstname());
            dbuser.setLastname(user.getLastname());
            dbuser.setPhone(user.getPhone());

            userRepository.save(dbuser);
    }


    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
