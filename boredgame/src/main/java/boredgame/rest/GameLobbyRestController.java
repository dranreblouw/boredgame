package boredgame.rest;

import boredgame.domain.gamelobby.EGameRequestStatus;
import boredgame.domain.gamelobby.GameRequest;
import boredgame.repository.GameRequestRepository;
import boredgame.rest.dto.GameRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bernard Louw on 02/01/2017.
 */
@RestController
@RequestMapping("/api/gamelobby")
public class GameLobbyRestController extends AbstractRestController {

    @Autowired
    private GameRequestRepository gameRequestRepository;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @RequestMapping(value = "/gamerequest/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public GameRequest find(@PathVariable("id") GameRequest gameRequest) {
        return gameRequest;
    }

    @RequestMapping(value = "/gamerequest/add", method = RequestMethod.POST)
    public GameRequest add(GameRequestDTO gameRequest, Authentication authentication) {

        GameRequest request = new GameRequest(gameRequest.getNumberOfParticipants(), gameRequest.getPaticipantAccountIds(), gameRequest.getGameType());
        return gameRequestRepository.save(request);
    }

    @RequestMapping(value = "/gamerequest/pending", method = {RequestMethod.GET, RequestMethod.POST})
    public List<GameRequest> findPendingGameRequests() {
        return gameRequestRepository.findByGameRequestStatus(EGameRequestStatus.WAITING_FOR_PLAYERS_TO_JOIN);
    }

    @RequestMapping(value = "/gamerequest/join/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public GameRequest findPendingGameRequests(@PathVariable("id") GameRequest gameRequest, Authentication authentication) {

        System.out.println();

        return gameRequest;
    }
}
