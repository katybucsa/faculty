package game.services;


import game.model.Game;
import game.model.GamePlayer;
import game.persistance.GamePlayerRepository;
import game.persistance.GameRepository;
import game.persistance.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/games")
public class GameRESTController {
    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<GamePlayer> getAllPlayerGames(@PathVariable int id) {
        List<GamePlayer> games = new ArrayList<>();
        gamePlayerRepo.findPlayerGameContribution(id).forEach(x -> games.add(x));
        return games;
    }

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userError(RepositoryException e) {
        return e.getMessage();
    }
}
