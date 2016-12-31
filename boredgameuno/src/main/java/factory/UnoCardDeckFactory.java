package factory;

import domain.*;
import org.springframework.beans.factory.FactoryBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static domain.EUnoSpecialCardType.*;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class UnoCardDeckFactory implements FactoryBean<UnoCardDeck> {

    @Override
    public UnoCardDeck getObject() throws Exception {

        List<UnoCard> cards = new ArrayList<>();
        EUnoSuiteType.getCardOfNumberForEachSuite(0).forEach(cards::add);
        //for card numbers 1 to 10
        IntStream.range(1, 10).boxed()
                //add to cards of each suite
                .flatMap(cardNum -> EUnoSuiteType.getCardOfNumberForEachSuite(cardNum, 2)).forEach(cards::add);

        Stream.of(WILD_CARD_DRAW_TWO, WILD_CARD_DRAW_TWO, SKIP_CARD, REVERSE_CARD)
                .flatMap(cardType -> getNCardsForSpecialType(cardType, 2))
                .forEach(cards::add);
        Stream.of(WILD_CARD_DRAW_FOUR, WILD_CARD)
                .flatMap(cardType -> getNCardsForSpecialType(cardType, 4))
                .forEach(cards::add);

        //should be 108 cards in this bitch
        return new UnoCardDeck(cards, true);
    }

    @Override
    public Class<?> getObjectType() {
        return UnoCardDeck.class;
    }
}
