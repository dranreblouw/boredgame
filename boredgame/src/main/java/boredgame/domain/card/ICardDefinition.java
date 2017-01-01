package boredgame.domain.card;

import boredgame.domain.game.AbstractGameModel;
import boredgame.domain.uno.UnoAdditionalTurnInfo;

/**
 * Created by Bernard Louw on 01/01/2017.
 */
public interface ICardDefinition {

    boolean canCardBePlayed(final AbstractGameModel<?> gameModel);
    void applyCardToModel(final AbstractGameModel<?> gameModel, final UnoAdditionalTurnInfo additionalTurnInfo);
}
