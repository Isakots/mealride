package hu.student.projlab.mealride.restaurant;

import hu.student.projlab.mealride.meal.Meal;
import hu.student.projlab.mealride.meal.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    private MealRepository mealRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MealRepository mealRepository) {
        this.restaurantRepository = restaurantRepository;
        this.mealRepository = mealRepository;
    }

    public void addRestaurant(RestaurantForm restaurantform) {

        Time open = formatStringToTime(restaurantform.getOpeningtime());
        Time close = formatStringToTime(restaurantform.getClosingtime());

        Restaurant restaurant = new Restaurant(restaurantform.getName(), restaurantform.getAvgdeliverytime(),
                restaurantform.getMinorderprice(), restaurantform.getDeliveryprice(), new ShoppingHours(open, close));

        restaurantRepository.save(restaurant);
    }


    public List<Restaurant> findAll() {
        List<Restaurant> rests = new ArrayList<>();
        restaurantRepository.findAll().forEach(rests::add);
        return rests;
    }

    public List<Meal> getMeals() {
        List<Meal> meals = new ArrayList<>();
        mealRepository.findAll().forEach(meals::add);
        return meals;
    }

    private Time formatStringToTime(String mypattern) {
        String[] split = mypattern.split(":");
        int[] formatted = new int[2];
        formatted[0] = Integer.parseInt(split[0]);
        formatted[1] = Integer.parseInt(split[1]);

        return new Time(formatted[0], formatted[1], 0);
    }

}