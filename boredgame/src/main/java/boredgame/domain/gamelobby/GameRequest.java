package boredgame.domain.gamelobby;

import boredgame.domain.game.EGameRegister;
import boredgame.domain.users.UserAccount;
import boredgame.exception.InvalidDataException;
import org.springframework.security.core.Authentication;

import javax.validation.constraints.Min;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Bernard Louw on 02/01/2017.
 */
public class GameRequest {

    private String id;
    @Min(2)
    private int numberOfParticipants;
    final private Set<String> participants;
    private EGameRegister gameType;
    private EGameRequestStatus gameRequestStatus;

    public GameRequest(final int numberOfParticipants, final Set<String> participants, final EGameRegister gameType) {

        this.numberOfParticipants = numberOfParticipants;
        this.participants = participants;
        this.gameType = gameType;
    }

    public GameRequest(final Set<String> participants, final EGameRegister gameType) {
        this(participants.size(), participants, gameType);
    }

    private String gameId;


    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(final int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public Set<String> getParticipants() {
        return participants;
    }

    public EGameRequestStatus getGameRequestStatus() {

        if (numberOfParticipants == participants.size()) {
            return EGameRequestStatus.READY;
        }
        return EGameRequestStatus.WAITING_FOR_PLAYERS_TO_JOIN;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(final String gameId) {
        this.gameId = gameId;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void joinGame(UserAccount participantUserAccount, Authentication authentication) {
        Objects.nonNull(participantUserAccount);
        final String userAccountId = participantUserAccount.getId();
        Objects.nonNull(userAccountId);
        if (this.getGameRequestStatus() != EGameRequestStatus.WAITING_FOR_PLAYERS_TO_JOIN) {
            throw new InvalidDataException("The requested game can no longer be joined");
        }
        if (participants.contains(userAccountId)) {
            throw new InvalidDataException("User " + userAccountId + " has already joined the game");
        }

        participants.add(userAccountId);
    }
}
