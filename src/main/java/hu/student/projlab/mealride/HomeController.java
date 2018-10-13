package hu.student.projlab.mealride;


import hu.student.projlab.mealride.deliveryaddress.DeliveryAddress;
import hu.student.projlab.mealride.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomepage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "error", required = false) String error, Model model) {

        if(error!= null)
            model.addAttribute("loginfailed", "Email address or password is incorrect!");

        return "login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        User user = new User();
        DeliveryAddress address = new DeliveryAddress();
        model.addAttribute("user", user);
        model.addAttribute("address", address);
        return "registration";
    }

}