package hu.student.projlab.mealride.exception;

public class PasswordNotMatchingException extends Throwable {

    private String message;

    public PasswordNotMatchingException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
