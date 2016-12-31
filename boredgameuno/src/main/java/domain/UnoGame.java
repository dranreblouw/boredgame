package domain;

import domain.card.HiddenCardDeck;
import domain.game.AbstractGameModel;
import domain.game.EGameType;

import java.util.List;
import java.util.Map;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class UnoGame extends AbstractGameModel {

    private Map<User, List<UnoCard>> gameParticipantCardMap;
    private HiddenCardDeck stockCardPile = new HiddenCardDeck();
    private HiddenCardDeck disgardCardPile = new HiddenCardDeck<>();

    private Integer currentScore;

    public EGameType getGameType() {
        return EGameType.CARD;
    }



}
