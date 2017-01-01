package boredgame.domain.card;

import boredgame.exception.InvalidDataException;

import java.util.Objects;

/**
 * Created by BERNARD7 on 31/12/2016.
 */

public abstract class SuiteCardDefinition<T extends ISuiteType> implements ISuiteDefinition<T> {

    private Integer cardNumber;
    private T suiteType;

    public SuiteCardDefinition(Integer cardNumber, T suiteType) {

        Objects.nonNull(cardNumber);
        if (!isCardNumberValid(cardNumber)) {
            throw new InvalidDataException("The card number " + cardNumber + "for suite " + suiteType);
        }
        this.cardNumber = cardNumber;
        this.suiteType = suiteType;
    }

    @Override
    public Integer getCardNumber() {
        return cardNumber;
    }

    public T getSuiteType() {
        return suiteType;
    }
}
