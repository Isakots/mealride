package hu.student.projlab.mealride;

import hu.student.projlab.mealride.exception.RestaurantNotExistingException;
import hu.student.projlab.mealride.restaurant.Restaurant;
import hu.student.projlab.mealride.restaurant.RestaurantForm;
import hu.student.projlab.mealride.restaurant.RestaurantService;
import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/administration")
class AdminController {

    private RestaurantService restaurantService;

    private UserService userService;

    @Autowired
    public AdminController(RestaurantService restaurantService, UserService userService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }


    @GetMapping("/restaurants")
    public String getAllRestaurants(@RequestParam(value="error", required = false) String error,
                                    @RequestParam(value="success", required = false) String success,
                                    Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());
        model.addAttribute("rest1", new Restaurant());
        model.addAttribute("error", error);
        model.addAttribute("success", success);
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
                                         ModelAndView modelandView, final BindingResult results) {

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
        modelandView.setViewName("redirect:/administration/restaurants");
        return modelandView;
    }

    @PostMapping("/restaurants/delete")
    public ModelAndView deleteRestaurant(@RequestParam("restaurantId") Long id,  ModelAndView modelAndView, final BindingResult result) {

        if(result.hasErrors()) {
            modelAndView.addObject("error", "Some error are occured!");
            modelAndView.setViewName("redirect:/administration/restaurants");
            return modelAndView;
        }

        System.out.println(id);

        modelAndView.addObject("restaurants", restaurantService.findAll());
        modelAndView.setViewName("redirect:/administration/restaurants");
        return modelAndView;
    }

    @PostMapping("/restaurants/newadmin")
    public ModelAndView addNewAdminToRestaurant(@RequestParam("restaurantId") Long id, @RequestParam("email") String email,
                                                  ModelAndView modelAndView, final BindingResult result) {

        if(result.hasErrors()) {
            modelAndView.addObject("error", "Some error are occured!");
            modelAndView.setViewName("redirect:/administration/restaurants");
            return modelAndView;
        }

        try{
            User user = userService.findUserByEmail(email);
            if(user == null) {
                modelAndView.addObject("error", "The user, with the specified email address, doesn't exist!");
            }else {
                Restaurant restaurant = restaurantService.addRestaurantWorker(id, user);
                userService.setUserToRestaurantAdmin(restaurant, user);
            }

        }catch(RestaurantNotExistingException e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("administration/restaurants");
            modelAndView.addObject("restaurants", restaurantService.findAll());
            return modelAndView;
        }
        catch(Exception e) {
            modelAndView.addObject("error", "Some unknown error occured.");
        }

        modelAndView.addObject("restaurants", restaurantService.findAll());
        modelAndView.setViewName("redirect:/administration/restaurants");
        modelAndView.addObject("success", "Admin rights has been added succesfully!");
        return modelAndView;
    }

}
