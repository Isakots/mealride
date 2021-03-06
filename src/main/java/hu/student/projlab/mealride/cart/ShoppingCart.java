package hu.student.projlab.mealride.cart;

import hu.student.projlab.mealride.meal.Meal;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addItem(Meal meal) {

        boolean itemAlreadyExists = false;

        for(CartItem item: cartItems) {
            if(item.getMeal().equals(meal)) {
                item.setAmount(item.getAmount()+1);
                itemAlreadyExists = true;
            }
        }
        if(!itemAlreadyExists) {
            CartItem item = new CartItem(meal, 1);
            cartItems.add(item);
        }

    }

    public void deleteItem(CartItem item) {
        cartItems.remove(item);
    }

    public boolean hasItem(Meal meal) {
        for(CartItem item: cartItems) {
            if(item.getMeal().equals(meal))
                return true;
        }
        return false;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void printItems() {
        for(CartItem item: cartItems) {
            System.out.println(item.getMeal()+"\tAmount: "+item.getAmount());
        }
    }

    public int getFullPrice() {
        if(cartItems.isEmpty())
            return 0;

        int sum = 0;
        for(CartItem item: cartItems) {
            sum += item.getAmount()*item.getMeal().getPrice();
        }
        return sum;

    }

}
