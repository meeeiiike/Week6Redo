package ie.atu.week5redo.controller.errorHandling;

public class DuplicateExceptionHandling extends RuntimeException {
    private String field;

    public DuplicateExceptionHandling(String message) {
        super(message);
    }

    public DuplicateExceptionHandling(String message, String field) {
        super(message);
        this.field = field;
    }
}
