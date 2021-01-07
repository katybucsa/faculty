package company.persistance;


import company.model.User;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User entity) {
        String err="";
        if(entity.getID().equals(""))
            err+="Username nu poate fi vid!";
        if(entity.getPassword().equals(""))
            err+="Parola nu poate fi vida";
        if(!err.equals(""))
            throw new ValidationException(err);
    }
}
