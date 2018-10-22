package hu.student.projlab.mealride.cart;

import hu.student.projlab.mealride.meal.Meal;

public class CartItem {

    private Meal meal;
    private Integer amount;

    public CartItem() {
    }

    public CartItem(Meal meal, Integer amount) {
        this.meal = meal;
        this.amount = amount;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
