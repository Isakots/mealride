package hu.student.projlab.mealride.cart;

import hu.student.projlab.mealride.meal.Meal;

public class CartItem {

    private Meal meal;
    private int amount;

    public CartItem() {
    }

    public CartItem(Meal meal, int amount) {
        this.meal = meal;
        this.amount = amount;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
