package boredgame.domain.card;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public abstract class SpecialCardDefinition<T extends ISpecialCardType> implements ISpecialCardDefinition<T> {

    private T specialCardType;

    public SpecialCardDefinition(final T specialCardType) {
        this.specialCardType = specialCardType;
    }

    public T getSpecialCardType() {
        return specialCardType;
    }
}
