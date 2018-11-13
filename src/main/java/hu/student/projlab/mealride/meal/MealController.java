package hu.student.projlab.mealride.meal;


import hu.student.projlab.mealride.cart.ShoppingCart;
import hu.student.projlab.mealride.cart.ShoppingCartService;
import hu.student.projlab.mealride.restaurant.Restaurant;
import hu.student.projlab.mealride.restaurant.RestaurantService;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;


@Controller
class MealController {

    private MealService mealService;

    private UserService userService;

    private RestaurantService restaurantService;

    @Resource(name = "shoppingCart")
    private ShoppingCart shoppingCart;

    @Autowired
    public MealController(MealService mealService, UserService userService,
                          RestaurantService restaurantService) {
        this.mealService = mealService;
        this.userService = userService;
        this.restaurantService = restaurantService;
    }


    @GetMapping("/restaurant/menu")
    public String getRestaurantMenu(Model model) {
        model.addAttribute("meal", new Meal());
        model.addAttribute("meals", userService.getCurrentUser().getRestaurant().getMenu());;
        return "restaurant/menu";
    }

    @PostMapping("/restaurant/menu/delete")
    public ModelAndView deleteMealFromMenu(@RequestParam(value = "mealId") Long mealId, ModelAndView modelAndView,
                                           final BindingResult results) {

        modelAndView = new ModelAndView(new RedirectView("/restaurant/menu"));

        if (results.hasErrors()) {
            modelAndView.addObject("errormessage", "There are some error!");
            modelAndView.addObject("menu", mealService.getMeals());
            return modelAndView;
        }

        //try-catch
        mealService.deleteMealFromMenu(mealId);

        return modelAndView;
    }

    @PostMapping("/restaurant/menu/modify")
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

    @PostMapping("/restaurant/menu/newmeal")
    public String addNewMealToMenu(@ModelAttribute(value="meal") Meal meal, Model model, final BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("error", "There are some error!");
            model.addAttribute("menu", mealService.getMeals());
            model.addAttribute("meal", meal);
            return "restaurant/menu";
        }

        mealService.addNewMealToMenu(meal);

        return "redirect:/restaurant/menu";
    }

    @GetMapping("/restaurants/{restId}/menu")
    public ModelAndView getRestaurantMenu(@PathVariable(value = "restId") Long restId, ModelAndView modelAndView) {

       Restaurant restaurant = restaurantService.getRestaurantById(restId);

        if(restaurant == null) {
            modelAndView.addObject("error", "Restaurant cannot be found!");
            modelAndView.setViewName("/restaurant");
            return modelAndView;
        }
        modelAndView.addObject("meals", restaurant.getMenu());
        modelAndView.addObject("rest", restaurant);
        modelAndView.addObject("cartitems", shoppingCart.getCartItems());
        modelAndView.addObject("price", shoppingCart.getFullPrice());
        modelAndView.setViewName("/menu");
        return modelAndView;
    }

    @PostMapping("/restaurants/{restId}/menu")
    public ModelAndView queryForRestaurantMenu(@PathVariable(value = "restId") Long restId, ModelAndView modelAndView, final BindingResult result) {

        //modelAndView = new ModelAndView(new RedirectView("/restaurants/"+restId+"/menu"));

        if (result.hasErrors()) {
            modelAndView.addObject("error", "There are some error!");
            modelAndView.setViewName("/restaurants");
            return modelAndView;
        }

        Restaurant restaurant = restaurantService.getRestaurantById(restId);

        if(restaurant == null) {
            modelAndView.addObject("error", "Restaurant cannot be found!");
            modelAndView.setViewName("/restaurants");
            return modelAndView;
        }

        modelAndView.addObject("meals", restaurant.getMenu());
        modelAndView.addObject("rest", restaurant);
        modelAndView.addObject("cartitems", shoppingCart.getCartItems());
        modelAndView.addObject("price", shoppingCart.getFullPrice());
        modelAndView.setViewName("/menu");
        return modelAndView;
    }

    @PostMapping("/restaurants/{restId}/menu/addmeal")
    public ModelAndView addNewMealToCart(@PathVariable(value = "restId") Long restId, @RequestParam(value="mealId") Long mealId, ModelAndView modelAndView,
                                          final BindingResult result) {

        modelAndView = new ModelAndView(new RedirectView("/restaurants/"+restId+"/menu"));

        if (result.hasErrors()) {
            modelAndView.addObject("error", "There are some error!");
            return modelAndView;
        }

        Restaurant restaurant = restaurantService.getRestaurantById(restId);

        if(restaurant == null) {
            modelAndView.addObject("error", "Restaurant cannot be found!");
            return modelAndView;
        }

        // check if meal is exists in try-catch block!
        Meal meal = mealService.getMealById(mealId);
        shoppingCart.addItem(meal);

        modelAndView.addObject("meals", restaurant.getMenu());
        modelAndView.addObject("rest", restaurant);
        modelAndView.addObject("cartitems", shoppingCart.getCartItems());
        return modelAndView;
    }



}
