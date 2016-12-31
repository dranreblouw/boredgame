package boredgame.domain.card;

import boredgame.exception.InvalidDataException;

import java.util.Objects;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public abstract class SuiteCard<T extends ISuiteDefinition> extends AbstractCard {

    private T suiteDefinition;
    private Integer cardNumber;

    public SuiteCard(T suiteDefinition, Integer cardNumber) {

        Objects.requireNonNull(suiteDefinition, "Suite cannot be null");
        if(!suiteDefinition.isCardNumberValid(cardNumber))
        {
            throw new InvalidDataException("The card number "+  cardNumber + "for suite " +suiteDefinition);
        }
        this.suiteDefinition = suiteDefinition;
        this.cardNumber = cardNumber;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public T getSuiteDefinition() {
        return suiteDefinition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuiteCard<?> suiteCard = (SuiteCard<?>) o;

        if (!getSuiteDefinition().equals(suiteCard.getSuiteDefinition())) return false;
        return getCardNumber().equals(suiteCard.getCardNumber());
    }

    @Override
    public int hashCode() {
        int result = getSuiteDefinition().hashCode();
        result = 31 * result + getCardNumber().hashCode();
        return result;
    }
}
