package hu.student.projlab.mealride.restaurant;


import hu.student.projlab.mealride.meal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class RestaurantController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public String listRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());

        return "restaurants";
    }

    @GetMapping("/administration/restaurants")
    public String getAllRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.findAll());
        return "administration/restaurants";
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


    @GetMapping("restaurant/logs")
    public String getRestaurantLogs() {

        return "restaurant/logs";
    }

    @GetMapping("restaurant/menu")
    public String getRestaurantMenu(Model model) {
        model.addAttribute("menu", restaurantService.getMeals());
        return "restaurant/menu";
    }

    @PostMapping("restaurant/menu/delete")
    public ModelAndView deleteMealFromMenu(@ModelAttribute(value="meal") Meal meal, final BindingResult results,
                                             ModelAndView modelAndView) {

        if(results.hasErrors()) {
            modelAndView.addObject("errormessage", "There are some error!");
            modelAndView.addObject("menu", restaurantService.getMeals());
            modelAndView.setViewName("restaurant/menu");
            return modelAndView;
        }

        modelAndView.setViewName("restaurant/menu");
        return modelAndView;
    }

    @PostMapping("restaurant/menu/modify")
    public ModelAndView modifyMealFromMenu(@ModelAttribute(value="meal") Meal meal, final BindingResult results,
                                             ModelAndView modelAndView) {

        if(results.hasErrors()) {
            modelAndView.addObject("error", "There are some error!");
            modelAndView.addObject("menu", restaurantService.getMeals());
            modelAndView.setViewName("restaurant/menu");
            return modelAndView;
        }

        modelAndView.setViewName("restaurant/menu");
        return modelAndView;
    }

    @GetMapping("restaurant/newmeal")
    public String addNewMealToMenu(@ModelAttribute(value="meal") Meal meal, Model model) {
        model.addAttribute("meal", new Meal());
        return "restaurant/newmeal";
    }


}
