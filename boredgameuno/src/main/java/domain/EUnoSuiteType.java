package domain;

import domain.card.ISuiteDefinition;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public enum EUnoSuiteType implements ISuiteDefinition {

    RED,
    BLUE,
    GREEN,
    YELLOW;

    public static Stream<UnoSuiteCard> getCardOfNumberForEachSuite(Integer cardNumber) {

        return Arrays.stream(values())
                .filter(suiteType -> suiteType.isCardNumberValid(cardNumber))
                .map(suiteType -> new UnoSuiteCard(suiteType, cardNumber));
    }

    public static Stream<UnoSuiteCard> getCardOfNumberForEachSuite(Integer cardNumber, Integer cardsPerCardNumber) {

        return IntStream.range(0, cardsPerCardNumber).boxed().flatMap(value -> getCardOfNumberForEachSuite(cardNumber));
    }

}
