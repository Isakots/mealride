package hu.student.projlab.mealride.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void addRestaurant(Restaurant rest) {
        restaurantRepository.save(rest);
    }

    public List<Restaurant> findAll() {
        List<Restaurant> rests = new ArrayList<>();
        restaurantRepository.findAll().forEach(rests::add);
        return rests;
    }


}
