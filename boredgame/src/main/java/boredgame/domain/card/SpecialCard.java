package boredgame.domain.card;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialCard<?> that = (SpecialCard<?>) o;

        return getSpecialCardDefiniition().equals(that.getSpecialCardDefiniition());
    }

    @Override
    public int hashCode() {
        return getSpecialCardDefiniition().hashCode();
    }


}
