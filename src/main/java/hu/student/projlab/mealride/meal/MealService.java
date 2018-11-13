package hu.student.projlab.mealride.meal;


import hu.student.projlab.mealride.restaurant.Restaurant;
import hu.student.projlab.mealride.restaurant.RestaurantRepository;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {

    private MealRepository mealRepository;

    private RestaurantRepository restaurantRepository;

    private UserService userService;

    @Autowired
    public MealService(MealRepository mealRepository, RestaurantRepository restaurantRepository, UserService userService) {
        this.mealRepository = mealRepository;
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }


    List<Meal> getMeals() {
        List<Meal> meals = new ArrayList<>();
        mealRepository.findAll().forEach(meals::add);
        return meals;
    }

    void addNewMealToMenu(Meal meal) {
        meal = mealRepository.save(meal);
        Restaurant restaurant = userService.getCurrentUser().getRestaurant();
        restaurant.getMenu().add(meal);
        restaurantRepository.save(restaurant);
    }

    void deleteMealFromMenu(Long id) {
        Meal meal = mealRepository.getOne(id);
        mealRepository.deleteById(id);
        Restaurant restaurant = userService.getCurrentUser().getRestaurant();
        restaurant.getMenu().remove(meal);
        restaurantRepository.save(restaurant);
    }

    Meal getMealById(Long Id){
        return mealRepository.getMealById(Id);
    }


}
