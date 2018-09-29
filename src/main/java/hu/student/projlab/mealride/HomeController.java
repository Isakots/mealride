package hu.student.projlab.mealride;


import hu.student.projlab.mealride.deliveryaddress.DeliveryAddress;
import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import hu.student.projlab.mealride.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {



    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;


    @GetMapping("/")
    public String getHomepage() {
        return "index";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        User user = new User();
        DeliveryAddress address = new DeliveryAddress();
        model.addAttribute("user", user);
        model.addAttribute("address", address);
        return "registration";
    }

    @GetMapping("/restaurants")
    public String listRests(Model model) {
        model.addAttribute("rests", restaurantService.getRests());
        return "restaurants";
    }


}