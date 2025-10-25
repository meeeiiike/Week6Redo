package ie.atu.week5redo.controller.errorHandling;

public class NotFoundHandling extends RuntimeException{
    private String field;

    public NotFoundHandling(String message) {
        super(message);
    }

    public NotFoundHandling(String message, String field) {
        super(message);
        this.field = field;
    }

}
