package boredgame.domain.uno;

import boredgame.domain.card.HiddenCardDeck;
import boredgame.domain.game.AbstractGameModel;
import boredgame.domain.game.EGameType;

import java.util.List;

public class UnoGame extends AbstractGameModel {

    private List<UnoPlayer> gameParticipantList;
    private HiddenCardDeck<UnoCard> stockCardPile = new HiddenCardDeck<>();
    private HiddenCardDeck<UnoCard> disgardCardPile = new HiddenCardDeck<>();

    private Integer currentScore;

    public EGameType getGameType() {
        return EGameType.CARD;
    }

    public UnoCard getLastDisgardedCard()
    {
        return disgardCardPile.viewLastAddedCard();
    }



}
