package boredgame.domain.uno;

import boredgame.domain.card.AbstractPlayableCard;

/**
 * Created by Bernard Louw on 01/01/2017.
 */
public class UnoPlayableCard<T extends IUnoCardDefinition> extends AbstractPlayableCard<T> {

    public UnoPlayableCard(final T cardDefinition) {
            super(cardDefinition);
    }

    public boolean isSpecialCard()
    {
        return getCardDefinition() instanceof IUnoSpecialCardDefinition ? true : false;
    }
}
