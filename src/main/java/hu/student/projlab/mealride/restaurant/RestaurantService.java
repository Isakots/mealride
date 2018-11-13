package hu.student.projlab.mealride.restaurant;

import hu.student.projlab.mealride.exception.RestaurantNotExistingException;
import hu.student.projlab.mealride.meal.Meal;
import hu.student.projlab.mealride.meal.MealRepository;
import hu.student.projlab.mealride.user.User;
import hu.student.projlab.mealride.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
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

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.getRestaurantById(id);
    }

    public Restaurant addRestaurantWorker(Long id, User user) throws RestaurantNotExistingException {
        Restaurant restaurant = getRestaurantById(id);
        if(restaurant == null) {
            throw new RestaurantNotExistingException("The restaurant doesn't exist.");
        }
        restaurant.getWorkers().add(user);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public void deleteRestaurantWorker(Restaurant restaurant, User user) {
        restaurant.getWorkers().remove(user);
        restaurantRepository.save(restaurant);
    }


    private Time formatStringToTime(String mypattern) {
        String[] split = mypattern.split(":");
        int[] formatted = new int[2];
        formatted[0] = Integer.parseInt(split[0]);
        formatted[1] = Integer.parseInt(split[1]);

        return new Time(formatted[0], formatted[1], 0);
    }

}