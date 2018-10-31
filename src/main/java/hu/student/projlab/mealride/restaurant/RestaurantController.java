package hu.student.projlab.mealride.restaurant;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Time;

@Controller
class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public String listRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());

        return "restaurants";
    }

    @GetMapping("/administration/restaurants")
    public String getAllRestaurants() {

        return "restaurants";
    }

    @GetMapping("/administration/add-restaurant")
    public String getNewlRestaurantForm(Model model) {
        model.addAttribute("restaurantForm", new RestaurantForm());
        return "administration/add-restaurant";
    }

    @PostMapping("administration/add-restaurant")
    public ModelAndView addNewRestaurant(@ModelAttribute(value="restaurantForm") RestaurantForm restaurantform,
                                         final BindingResult results, ModelAndView modelandView) {

        if(results.hasErrors()) {
            modelandView.addObject("error", "There are some validation error!");
            modelandView.addObject("restaurantForm", restaurantform);
            modelandView.setViewName("administration/add-restaurant");
            return modelandView;
        }
        try {
            Time open = formatStringToTime(restaurantform.getOpeningtime());
            Time close = formatStringToTime(restaurantform.getClosingtime());

            Restaurant restaurant= new Restaurant(restaurantform.getName(),restaurantform.getAvgdeliverytime(),
                    restaurantform.getMinorderprice(), restaurantform.getDeliveryprice(),new ShoppingHours(open,close));
            restaurantService.addRestaurant(restaurant);

        }catch(Exception exception) {
            modelandView.addObject("error", "Time formats are wrong! " +
                    "Please add opening and closing time like the pattern shows!");
            modelandView.addObject("restaurantForm", restaurantform);
            modelandView.setViewName("administration/add-restaurant");
            return modelandView;
        }

        modelandView.addObject("restaurants", restaurantService.findAll());
        modelandView.setViewName("restaurants");
        return modelandView;
    }

    @GetMapping("administration/logs")
    public String getApplicationLogs() {

        return "administration/logs";
    }

    private Time formatStringToTime(String mypattern) {
        String[] split = mypattern.split(":");
        int[] formatted = new int[2];
        formatted[0] = Integer.parseInt(split[0]);
        formatted[1] = Integer.parseInt(split[1]);

        return new Time(formatted[0],formatted[1],0);
    }


}
