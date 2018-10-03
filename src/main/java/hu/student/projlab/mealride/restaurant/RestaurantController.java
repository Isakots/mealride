package hu.student.projlab.mealride.restaurant;


import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;


    @GetMapping("/restaurants")
    public String listRestaurants(Model model) {
        model.addAttribute("rests", restaurantService.getRests());

        return "restaurants";
    }

}
