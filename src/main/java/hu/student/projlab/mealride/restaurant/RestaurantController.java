package hu.student.projlab.mealride.restaurant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public String listRestaurants(Model model) {
        model.addAttribute("rests", restaurantService.getRests());

        return "restaurants";
    }

    @GetMapping("/administration/restaurants")
    public String getAllRestaurants() {

        return "restaurants1";
    }

    @PostMapping("administration/restaurants")
    public String addNewRestaurant() {

        return "redirect:/administration/restaurants";
    }

    @GetMapping("administration/logs")
    public String getApplicationLogs() {

        return "administration/logs";
    }


}
