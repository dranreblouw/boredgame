package boredgame.domain.game;

import boredgame.domain.card.HiddenCardDeck;
import boredgame.domain.card.IAbstractCard;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class CardGamePlayer<T extends IAbstractCard> {

    private HiddenCardDeck<T> cardsInHand = new HiddenCardDeck<>();


    public HiddenCardDeck<T> getCardsInHand() {
        return cardsInHand;
    }

    public void setCardsInHand(HiddenCardDeck<T> cardsInHand) {
        this.cardsInHand = cardsInHand;
    }
}
