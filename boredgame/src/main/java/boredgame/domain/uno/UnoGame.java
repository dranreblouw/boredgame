package boredgame.domain.uno;

import boredgame.domain.UserAccount;
import boredgame.domain.card.HiddenCardDeck;
import boredgame.domain.game.AbstractGameModel;
import boredgame.domain.game.EGameType;

import java.util.List;
import java.util.Map;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class UnoGame extends AbstractGameModel {

    private Map<UserAccount, List<UnoCard>> gameParticipantCardMap;
    private HiddenCardDeck stockCardPile = new HiddenCardDeck();
    private HiddenCardDeck disgardCardPile = new HiddenCardDeck<>();

    private UnoCardDeck unoCardDeck;
    private Integer currentScore;



    public EGameType getGameType() {
        return EGameType.CARD;
    }



}
