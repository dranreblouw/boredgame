package boredgame.domain.game;

/**
 * Created by Bernard Louw on 02/01/2017.
 */
public enum EGameRegister {

    UNO(EGameType.CARD);

    private final EGameType gameType;

    EGameRegister(final EGameType gameType) {

        this.gameType = gameType;
    }

    public EGameType getGameType() {
        return gameType;
    }
}
