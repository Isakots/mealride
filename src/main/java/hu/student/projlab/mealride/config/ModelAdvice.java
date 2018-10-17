package hu.student.projlab.mealride.config;

import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(annotations = Controller.class)
public class ModelAdvice {

    @Autowired
    private UserService userService;

    @ModelAttribute("username")
    public String getUser() {

        User user = userService.getCurrentUser();
        if(user == null)
            return "Guest";
        return user.getFirstname()+" "+user.getLastname();
    }

}
