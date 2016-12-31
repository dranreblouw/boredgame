package boredgame.domain.game;

import boredgame.domain.UserAccount;

import java.util.List;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public abstract class AbstractGameModel {

    private List<UserAccount> gameParticipantList;
    abstract public EGameType getGameType();
}
