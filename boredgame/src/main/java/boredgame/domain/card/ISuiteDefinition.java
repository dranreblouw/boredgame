package boredgame.domain.card;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public interface ISuiteDefinition<T extends ISuiteType> extends ICardDefinition {

    boolean isCardNumberValid(Integer cardNumber);
    public Integer getCardNumber();
    T getSuiteType();
}
