package hu.student.projlab.mealride.restaurant;


import hu.student.projlab.mealride.cart.ShoppingCartService;
import hu.student.projlab.mealride.meal.Meal;
import hu.student.projlab.mealride.meal.MealService;
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
@RequestMapping(value="/restaurant")
class RestaurantController {

    private RestaurantService restaurantService;

    private ShoppingCartService shoppingCartService;

    private MealService mealService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, ShoppingCartService shoppingCartService, MealService mealService) {
        this.restaurantService = restaurantService;
        this.shoppingCartService = shoppingCartService;
        this.mealService = mealService;
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
    public ModelAndView deleteMealFromMenu(@ModelAttribute(value="meal") Meal meal, final BindingResult results,
                                             ModelAndView modelAndView) {

        if(results.hasErrors()) {
            modelAndView.addObject("errormessage", "There are some error!");
            modelAndView.addObject("menu", mealService.getMeals());
            modelAndView.setViewName("restaurant/menu");
            return modelAndView;
        }

        modelAndView.setViewName("restaurant/menu");
        return modelAndView;
    }

    @PostMapping("/menu/modify")
    public ModelAndView modifyMealFromMenu(@ModelAttribute(value="meal") Meal meal, final BindingResult results,
                                             ModelAndView modelAndView) {

        if(results.hasErrors()) {
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
        //model.addAttribute("meal", new Meal());
        return "restaurant/manage-workers";
    }


}
