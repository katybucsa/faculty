package company.persistance;

public class ValidationException extends RuntimeException{
    public ValidationException(String msg){
        super(msg);
    }
}
