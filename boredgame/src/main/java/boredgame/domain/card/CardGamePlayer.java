package boredgame.domain.card;

import boredgame.domain.game.Player;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class CardGamePlayer<T extends IAbstractCard<D>, D extends ICardDefinition> extends Player {

    private HiddenCardDeck<T, D> cardsInHand = new HiddenCardDeck<>();

    public CardGamePlayer(final String userAccountID) {
        super(userAccountID);
    }

    public HiddenCardDeck<T, D> getCardsInHand() {
        return cardsInHand;
    }

    protected boolean removeCardFromDeck(D cardDefinition){
        return cardsInHand.removeFromDeck(cardDefinition);
    }



}
