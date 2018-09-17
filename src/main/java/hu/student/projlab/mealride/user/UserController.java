package hu.student.projlab.mealride.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
     private UserService userService;


    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }
    // but this is NOT rest..
    @PostMapping("/")
    public String addTopic(@ModelAttribute(value="user") User user) {
        userService.addUser(user);
        // redirecting to index.html to view it again..
        return "redirect:/";
    }

}
