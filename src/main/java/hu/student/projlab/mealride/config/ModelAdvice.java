package hu.student.projlab.mealride.config;

import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(annotations = Controller.class)
public class ModelAdvice {

    @Autowired
    private UserService userService;

    @ModelAttribute("username")
    public String getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        User user = userService.findUserByEmail(name);

        if(user == null)
            return "Guest";

        return user.getFirstname()+" "+user.getLastname();
    }

}
