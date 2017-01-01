package boredgame.domain.card;

import java.util.Objects;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public abstract class AbstractPlayableCard<T extends ICardDefinition> implements IAbstractCard<T> {

    private T cardDefinition;

    public AbstractPlayableCard(final T cardDefinition) {
        Objects.requireNonNull(cardDefinition, "Card definition cannot be null");
        this.cardDefinition = cardDefinition;
    }

    @Override
    public T getCardDefinition() {
        return cardDefinition;
    }

}
