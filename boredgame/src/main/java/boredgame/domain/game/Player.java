package boredgame.domain.game;

import java.io.Serializable;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class Player implements Serializable {

    private String userAccountID;

    public Player(final String userAccountID) {
        this.userAccountID = userAccountID;
    }

    public String getUserAccountID() {
        return userAccountID;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Player player = (Player) o;

        return getUserAccountID().equals(player.getUserAccountID());
    }

    @Override
    public int hashCode() {
        return getUserAccountID().hashCode();
    }
}
