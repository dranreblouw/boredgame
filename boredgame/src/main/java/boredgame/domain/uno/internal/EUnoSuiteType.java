package boredgame.domain.uno.internal;

import boredgame.domain.card.ISuiteType;
import boredgame.domain.uno.IUnoCardDefinition;
import boredgame.domain.uno.UnoPlayableCard;
import boredgame.domain.uno.UnoSuitePlayableCard;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public enum EUnoSuiteType implements ISuiteType {

    RED(0, 9),
    BLUE(0, 9),
    GREEN(0, 9),
    YELLOW(0, 9);

    private int minCardValue = 0;
    private int maxCardValue = 9;

    EUnoSuiteType(int minCardValue, int maxCardValue) {
        this.minCardValue = minCardValue;
        this.maxCardValue = maxCardValue;
    }

    public static <T extends UnoPlayableCard<D>, D extends IUnoCardDefinition> Stream<T> getCardOfNumberForEachSuite(Integer cardNumber) {

        return Arrays.stream(values())
                .filter(suiteType -> suiteType.isCardNumberValid(cardNumber))
                .map(suiteType -> (T) new UnoSuitePlayableCard(new UnoSuiteCardDefinition(cardNumber, suiteType)));
    }

    public static <T extends UnoPlayableCard<D>, D extends IUnoCardDefinition> Stream<T> getCardOfNumberForEachSuite(Integer cardNumber, Integer cardsPerCardNumber) {
        return IntStream.range(0, cardsPerCardNumber).boxed().flatMap(value -> getCardOfNumberForEachSuite(cardNumber));
    }

    @Override
    public boolean isCardNumberValid(Integer cardNumber) {
        return minCardValue <= cardNumber && maxCardValue >= cardNumber;
    }
}
