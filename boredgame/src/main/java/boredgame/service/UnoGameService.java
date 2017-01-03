package boredgame.service;

import boredgame.domain.uno.internal.UnoGame;
import boredgame.domain.uno.internal.UnoPlayer;
import boredgame.domain.users.UserAccount;
import boredgame.exception.InvalidDataException;
import boredgame.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
@Service
public class UnoGameService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UnoGame initialiseUnoGame(List<String> players) {

        List<UnoPlayer> validPlayers = getUnoPlayers(players);
        //create game with players and initial deck
        //final UnoGame unoGame = new UnoGame(validPlayers, getUnoCardDeck());

        //return unoGame;
        return null;
    }

    private List<UnoPlayer> getUnoPlayers(final List<String> players) {
        Map<Boolean, List<UserAccount>> resultsMap = players.stream().map(userAccountRepository::findOne)
                .collect(partitioningBy(usr -> usr != null && usr.getRoles().contains("PLAYER")));

        List<UserAccount> invalidPlayers = resultsMap.get(Boolean.FALSE);
        List<UnoPlayer> validPlayers = resultsMap.get(Boolean.TRUE).stream().map(UserAccount::getId).map(UnoPlayer::new).collect(toList());
        if (invalidPlayers.size() > 0) {
            throw new InvalidDataException("The following users are not valid - " + invalidPlayers.stream().map(UserAccount::getId).collect(toSet()));
        }
        if (validPlayers.size() < 2) {
            throw new InvalidDataException("This game requires at least 2 players");
        }
        return validPlayers;
    }

}
