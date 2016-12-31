package domain.game;

import domain.User;

import java.util.List;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public abstract class AbstractGameModel {

    private List<User> gameParticipantList;
    abstract public EGameType getGameType();
}
