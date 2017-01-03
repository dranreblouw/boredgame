package boredgame.rest.dto;

import boredgame.domain.game.EGameRegister;

import java.util.Set;

/**
 * Created by Bernard Louw on 02/01/2017.
 */
public class GameRequestDTO {

    private int numberOfParticipants;
    private Set<String> paticipantAccountIds;
    private EGameRegister gameType;

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(final int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public Set<String> getPaticipantAccountIds() {
        return paticipantAccountIds;
    }

    public void setPaticipantAccountIds(final Set<String> paticipantAccountIds) {
        this.paticipantAccountIds = paticipantAccountIds;
    }

    public EGameRegister getGameType() {
        return gameType;
    }

    public void setGameType(final EGameRegister gameType) {
        this.gameType = gameType;
    }
}
