package bugs.services;

public class AppException extends RuntimeException {
    public AppException(String msg){
        super(msg);
    }
}
