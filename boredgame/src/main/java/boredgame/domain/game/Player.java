package boredgame.domain.game;

import boredgame.domain.UserAccount;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class Player {

    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
