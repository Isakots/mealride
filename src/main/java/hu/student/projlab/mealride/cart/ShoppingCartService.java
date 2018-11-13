package hu.student.projlab.mealride.cart;


import hu.student.projlab.mealride.meal.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    private ShoppingCart shoppingCart;

    @Autowired
    public ShoppingCartService(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addMealToCart(Meal meal) {
        shoppingCart.addItem(meal);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
