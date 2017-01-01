package boredgame.domain.card;

import java.io.Serializable;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public interface IAbstractCard<T extends ICardDefinition> extends Serializable {

    public abstract T getCardDefinition();

}
