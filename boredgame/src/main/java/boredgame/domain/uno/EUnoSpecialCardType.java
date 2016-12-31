package boredgame.domain.uno;

import boredgame.domain.card.ISpecialCardDefinition;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public enum EUnoSpecialCardType implements ISpecialCardDefinition {

    WILD_CARD_DRAW_FOUR,
    WILD_CARD_DRAW_TWO,
    WILD_CARD,
    SKIP_CARD,
    REVERSE_CARD;

    public static Stream<UnoSpecialCard> getNCardsForSpecialType(EUnoSpecialCardType cardType, Integer cardNum) {
        Objects.nonNull(cardType);
        return IntStream.range(0, cardNum).boxed().map(x -> new UnoSpecialCard(cardType));
    }
}
