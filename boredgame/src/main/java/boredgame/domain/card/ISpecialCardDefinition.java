package boredgame.domain.card;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public interface ISpecialCardDefinition<T extends ISpecialCardType> extends ICardDefinition {

    T getSpecialCardType();
}
