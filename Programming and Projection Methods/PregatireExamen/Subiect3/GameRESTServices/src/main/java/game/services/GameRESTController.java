package game.services;


import game.model.Game;
import game.persistance.GameRepository;
import game.persistance.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/gamesapp/games")
public class GameRESTController {
    @Autowired
    private GameRepository game_repo;

    @RequestMapping(value = "/players/{id}", method = RequestMethod.GET)
    public List<Game> getAllPlayerGames(@PathVariable String id) {
        List<Game> games = new ArrayList<>();
        game_repo.findAllPlayerGames(id).forEach(x -> games.add(x));
        return games;
    }

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userError(RepositoryException e) {
        return e.getMessage();
    }
}
