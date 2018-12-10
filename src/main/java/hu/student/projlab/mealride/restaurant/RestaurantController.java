package hu.student.projlab.mealride.restaurant;


import hu.student.projlab.mealride.exception.RestaurantNotExistingException;
import hu.student.projlab.mealride.order.OrderService;
import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/restaurant")
class RestaurantController {

    private RestaurantService restaurantService;

    private UserService userService;

    private OrderService orderService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, UserService userService, OrderService orderService) {
        this.restaurantService = restaurantService;
        this.userService = userService;
        this.orderService = orderService;
    }



    @GetMapping("")
    public String listRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());

        return "restaurants";
    }

    @GetMapping("/previous-orders")
    public String previousOrders(Model model) {
        System.out.println("Restaurant ID is: "+ userService.getCurrentUser().getRestaurant().getId());
        model.addAttribute("orders", orderService.getRestaurantOrders(userService.getCurrentUser().getRestaurant().getId()));
        return "restaurant/previous-orders";
    }

    @GetMapping("/logs")
    public String getRestaurantLogs() {

        return "restaurant/logs";
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
            Restaurant restaurant = restaurantService.addRestaurantWorker(currentRestaurant.getId(), userExists);
            userService.setUserToRestaurantWorker(restaurant, userExists);
        }
        catch(RestaurantNotExistingException e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("restaurant/manage-workers");
            return modelAndView;
        }
        catch(Exception e) {
            modelAndView.addObject("error", "Some unknown error occured.");
            modelAndView.setViewName("redirect:/restaurant/manage-workers");
            return modelAndView;
        }

        modelAndView.addObject("success", "The user has been added to Restaurant successfully!");
        modelAndView.setViewName("redirect:/restaurant/manage-workers");
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
