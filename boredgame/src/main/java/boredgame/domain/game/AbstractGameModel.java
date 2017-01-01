package boredgame.domain.game;

import boredgame.exception.InvalidDataException;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public abstract class AbstractGameModel<U extends Player> {

    private NavigableSet<U> gameParticipantList = new TreeSet<>();

    private U currentUserTurn;
    private EGameDirection currentGameDirection = EGameDirection.FORWARD;

    abstract public EGameType getGameType();

    protected AbstractGameModel(final List<U> players) {

        if (players.isEmpty()) {
            throw new InvalidDataException("Cannot create a game with no players");
        }
        gameParticipantList.addAll(players);
    }

    public NavigableSet<U> getGameParticipantList() {
        return Collections.unmodifiableNavigableSet(gameParticipantList);
    }

    protected U continueToNextTurn() {
        return setCurrentUserTurn(currentGameDirection.getNextPayer(this));
    }

    protected EGameDirection changeGameDirection() {
        return currentGameDirection == EGameDirection.FORWARD ? EGameDirection.REVERSE : EGameDirection.FORWARD;
    }

    protected U setCurrentUserTurn(U player) {
        Objects.nonNull(player);
        return currentUserTurn = player;
    }

    public U getCurrentPlayerTurn() {
        return SerializationUtils.clone(currentUserTurn);
    }
    public U peekNextPlayerTurn() {
        return SerializationUtils.clone(currentGameDirection.getNextPayer(this));
    }

    public EGameDirection getGameDirection() {
        return currentGameDirection;
    }
}
