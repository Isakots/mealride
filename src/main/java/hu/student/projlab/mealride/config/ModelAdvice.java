package hu.student.projlab.mealride.config;

import hu.student.projlab.mealride.restaurant.Restaurant;
import hu.student.projlab.mealride.restaurant.RestaurantService;
import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice(annotations = Controller.class)
public class ModelAdvice {

    private UserService userService;

    private RestaurantService restaurantService;

    @Autowired
    public ModelAdvice(UserService userService, RestaurantService restaurantService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    @ModelAttribute("username")
    public String getUser() {

        User user = userService.getCurrentUser();
        if(user == null)
            return "Guest";
        return user.getFirstname()+" "+user.getLastname();
    }

    @ModelAttribute("loggedin_restaurant")
    public String getRestaurant() {

        User user = userService.getCurrentUser();

        if(user == null)
            return "Restaurant";

        Restaurant restaurant = restaurantService.getRestaurantById(userService.getRestaurantId(user.getId()));

        return restaurant.getName();
    }

}
