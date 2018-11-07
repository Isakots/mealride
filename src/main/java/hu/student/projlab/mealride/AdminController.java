package hu.student.projlab.mealride;

import hu.student.projlab.mealride.restaurant.RestaurantForm;
import hu.student.projlab.mealride.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/administration")
class AdminController {

    private RestaurantService restaurantService;

    @Autowired
    public AdminController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());
        return "administration/restaurants";
    }

    @GetMapping("/add-restaurant")
    public String getNewlRestaurantForm(Model model) {
        model.addAttribute("restaurantForm", new RestaurantForm());
        return "administration/add-restaurant";
    }

    @GetMapping("/logs")
    public String getApplicationLogs() {

        return "administration/logs";
    }

    @PostMapping("/add-restaurant")
    public ModelAndView addNewRestaurant(@ModelAttribute(value="restaurantForm") RestaurantForm restaurantform,
                                         final BindingResult results, ModelAndView modelandView) {

        if(results.hasErrors()) {
            modelandView.addObject("error", "There are some validation error!");
            modelandView.addObject("restaurantForm", restaurantform);
            modelandView.setViewName("administration/add-restaurant");
            return modelandView;
        }
        try {

            restaurantService.addRestaurant(restaurantform);

        }catch(Exception exception) {
            modelandView.addObject("error", "Time formats are wrong! " +
                    "Please add opening and closing time like the pattern shows!");
            modelandView.addObject("restaurantForm", restaurantform);
            modelandView.setViewName("administration/add-restaurant");
            return modelandView;
        }

        modelandView.addObject("restaurants", restaurantService.findAll());
        modelandView.setViewName("/administration/restaurants");
        return modelandView;
    }


}
