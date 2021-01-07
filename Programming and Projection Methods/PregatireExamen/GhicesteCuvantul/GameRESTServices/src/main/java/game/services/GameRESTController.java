package game.services;


import game.model.Game;

import game.model.GamePlayer;
import game.persistance.IGamePlayerRepository;
import game.persistance.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/games")
public class GameRESTController {
    @Autowired
    private IGamePlayerRepository gamePlayerRepo;

    @RequestMapping(value = "/{idg}/players/{id}", method = RequestMethod.GET)
    public GamePlayer getAllPlayerGames(@PathVariable int idg, @PathVariable String id) {
//        List<Game> games = new ArrayList<>();
        return gamePlayerRepo.findPlayerGameContribution(idg, id);
//        return games;
    }

    @ExceptionHandler(RepositoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userError(RepositoryException e) {
        return e.getMessage();
    }
}
