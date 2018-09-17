package hu.student.projlab.mealride.restaurant;


import java.sql.Time;

public class ShoppingHours {

    private Time open;
    private Time close;

    public ShoppingHours() {
    }

    public ShoppingHours(Time open, Time close) {
        this.open = open;
        this.close = close;
    }

    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    public Time getClose() {
        return close;
    }

    public void setClose(Time close) {
        this.close = close;
    }
}
