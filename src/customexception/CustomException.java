package customexception;

public class CustomException extends Exception {
    static final long serialVersionUID = 1L;
    public CustomException(String message) {
        super(message);
    }
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

}
