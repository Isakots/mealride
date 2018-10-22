package hu.student.projlab.mealride.cart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    public void deleteItem(CartItem item) {
        cartItems.remove(item);
    }

}
