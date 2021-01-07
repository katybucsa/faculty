package game.persistance;


import game.model.Player;

public class PlayerValidator implements Validator<Player> {
    @Override
    public void validate(Player entity) {
        String err="";
        if(entity.getId().equals(""))
            err+="Username nu poate fi vid!";
        if(entity.getPassword().equals(""))
            err+="Parola nu poate fi vida";
        if(!err.equals(""))
            throw new ValidationException(err);
    }
}
