package hu.student.projlab.mealride.restaurant;


import hu.student.projlab.mealride.cart.ShoppingCartService;
import hu.student.projlab.mealride.exception.RestaurantNotExistingException;
import hu.student.projlab.mealride.meal.Meal;
import hu.student.projlab.mealride.meal.MealService;
import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
@RequestMapping(value = "/restaurant")
class RestaurantController {

    private RestaurantService restaurantService;

    private ShoppingCartService shoppingCartService;

    private MealService mealService;

    private UserService userService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, ShoppingCartService shoppingCartService,
                                MealService mealService, UserService userService) {
        this.restaurantService = restaurantService;
        this.shoppingCartService = shoppingCartService;
        this.mealService = mealService;
        this.userService = userService;
    }


    @GetMapping("")
    public String listRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());

        return "restaurants";
    }

    @GetMapping("/logs")
    public String getRestaurantLogs() {

        return "restaurant/logs";
    }

    @GetMapping("/menu")
    public String getRestaurantMenu(Model model) {
        model.addAttribute("menu", mealService.getMeals());
        return "restaurant/menu";
    }

    @PostMapping("/menu/delete")
    public ModelAndView deleteMealFromMenu(@ModelAttribute(value = "meal") Meal meal, ModelAndView modelAndView,
                                           final BindingResult results) {

        if (results.hasErrors()) {
            modelAndView.addObject("errormessage", "There are some error!");
            modelAndView.addObject("menu", mealService.getMeals());
            modelAndView.setViewName("restaurant/menu");
            return modelAndView;
        }

        modelAndView.setViewName("restaurant/menu");
        return modelAndView;
    }

    @PostMapping("/menu/modify")
    public ModelAndView modifyMealFromMenu(@ModelAttribute(value = "meal") Meal meal, ModelAndView modelAndView,
                                           final BindingResult results) {

        if (results.hasErrors()) {
            modelAndView.addObject("error", "There are some error!");
            modelAndView.addObject("menu", mealService.getMeals());
            modelAndView.setViewName("restaurant/menu");
            return modelAndView;
        }

        modelAndView.setViewName("restaurant/menu");
        return modelAndView;
    }

    @GetMapping("/newmeal")
    public String getNewMealForm(Model model) {
        model.addAttribute("meal", new Meal());
        return "restaurant/newmeal";
    }

    @GetMapping("/manage-workers")
    public String getWorkerManager(Model model) {
        model.addAttribute("worker", new User());
        model.addAttribute("workers", userService.getCurrentUser().getRestaurant().getWorkers());
        return "restaurant/manage-workers";
    }

    @PostMapping("/manage-workers/inviteworker")
    public ModelAndView inviteWorker(@ModelAttribute(value = "worker") User user, ModelAndView modelAndView,
                                     final BindingResult result) {
        if(result.hasErrors()) {
            modelAndView.addObject("error", "There are some error!");
            modelAndView.setViewName("redirect:/restaurant/manage-workers");
            return modelAndView;
        }

        User userExists = userService.findUserByEmail(user.getEmail());

        if(userExists == null) {
            modelAndView.addObject("error", "The user, with the specified email address, doesn't exist!");
            modelAndView.setViewName("redirect:/restaurant/manage-workers");
            return modelAndView;
        }

        try {
            Restaurant currentRestaurant = userService.getCurrentUser().getRestaurant();
            Restaurant restaurant = restaurantService.addRestaurantWorker(currentRestaurant.getId(), user);
            userService.setUserToRestaurantWorker(restaurant, userExists);
        }
        catch(RestaurantNotExistingException e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("restaurant/manage-workers");
            //modelAndView.addObject("restaurants", restaurantService.findAll());
            return modelAndView;
        }
        catch(Exception e) {
            modelAndView.addObject("error", "Some unknown error occured.");
        }

        modelAndView.addObject("success", "The user has been added to Restaurant successfully!");
        modelAndView.setViewName("redirect:/restaurant/manage-workers");
        return modelAndView;
    }

    @PostMapping("/manage-workers/modify")
    public ModelAndView modifyWorker(@RequestParam(value="workerId") Long workerId, ModelAndView modelAndView) {
        return modelAndView;
    }

    @PostMapping("/manage-workers/delete")
    public ModelAndView deleteWorker(@RequestParam(value="workerId") Long workerId,ModelAndView modelAndView) {


        User userExists = userService.getUserById(workerId);

        if(userExists == null) {
            modelAndView.addObject("error", "The user, with the specified email address, doesn't exist!");
            modelAndView.setViewName("redirect:/restaurant/manage-workers");
            return modelAndView;
        }

        userService.deleteWorkerFromRestaurant(userExists);
        restaurantService.deleteRestaurantWorker(userService.getCurrentUser().getRestaurant(),userExists);

        modelAndView.addObject("success", "The user has been removed from Restaurant successfully!");
        modelAndView.setViewName("redirect:/restaurant/manage-workers");

        return modelAndView;
    }


}
