package boredgame.service;

import boredgame.domain.gamelobby.GameRequest;
import boredgame.repository.GameRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * Created by Bernard Louw on 02/01/2017.
 */
@Validated
@Service
public class GameLobbyService {

    @Autowired
    private GameRequestRepository gameRequestRepository;

    public GameRequest add(@Valid GameRequest gameRequest, Authentication authentication)
    {
        return gameRequestRepository.save(gameRequest);
    }
}
