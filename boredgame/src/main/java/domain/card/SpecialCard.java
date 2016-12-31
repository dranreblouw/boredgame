package domain.card;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public abstract class SpecialCard<T extends ISpecialCardDefinition> extends AbstractCard {

    private T specialCardDefiniition;

    public SpecialCard(T specialCardDefiniition) {
        this.specialCardDefiniition = specialCardDefiniition;
    }

    public T getSpecialCardDefiniition() {
        return specialCardDefiniition;
    }
}
