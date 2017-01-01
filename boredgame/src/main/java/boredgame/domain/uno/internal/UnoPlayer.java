package boredgame.domain.uno.internal;

import boredgame.domain.card.CardGamePlayer;
import boredgame.domain.uno.UnoPlayableCard;
import boredgame.domain.uno.IUnoCardDefinition;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class UnoPlayer<D extends IUnoCardDefinition> extends CardGamePlayer<UnoPlayableCard<D>, D> {

    public UnoPlayer(final String userAccountID) {
        super(userAccountID);
    }

    boolean removeCard(D cardDefinition){
        return removeCardFromDeck(cardDefinition);
    }
}
