package hu.student.projlab.mealride.exception;

public class UserAlreadyExistsException extends Exception {

    private String message;

    public UserAlreadyExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
