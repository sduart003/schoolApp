package exceptionhandlers;

public class MissingDataException extends Exception {

    public MissingDataException() {
        super();
    }
    
    public MissingDataException(String errorMessage) {
        super("Missing Data : " + errorMessage);
    }
}
