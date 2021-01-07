package sample.repository;


/**
 * own class of exceptions
 * ValidationException extended from RuntimeException
 */
public class ValidationException extends RuntimeException{
    public ValidationException(String err){
        super(err);
    }
}
