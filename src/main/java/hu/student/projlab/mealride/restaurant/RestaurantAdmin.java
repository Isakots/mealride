package hu.student.projlab.mealride.restaurant;

public class RestaurantAdmin {

    private String email;
    private Long restid;

    public RestaurantAdmin() {
    }

    public RestaurantAdmin(String email, Long restid) {
        this.email = email;
        this.restid = restid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRestid() {
        return restid;
    }

    public void setRestid(Long restid) {
        this.restid = restid;
    }
}
