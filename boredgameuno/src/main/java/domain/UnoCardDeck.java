package domain;

import domain.card.HiddenCardDeck;
import exception.InvalidDataException;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class UnoCardDeck extends HiddenCardDeck<UnoCard> {

    private static int REQUIRED_DECK_SIZE = 108;

    public UnoCardDeck(Collection<UnoCard> initialDeck, boolean shuffle) {

        addAllToDesk(initialDeck);

        if(getDeckSize() != REQUIRED_DECK_SIZE)
        {
            throw new InvalidDataException("A Uno card desk has to contain exactly " + REQUIRED_DECK_SIZE + "cards");
        }
        if(shuffle)
        {
            Collections.shuffle(getCardDeck());
        }
    }
}
