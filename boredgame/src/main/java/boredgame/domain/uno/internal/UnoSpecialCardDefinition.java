package boredgame.domain.uno.internal;

import boredgame.domain.card.SpecialCardDefinition;
import boredgame.domain.game.AbstractGameModel;
import boredgame.domain.uno.IUnoCardDefinition;
import boredgame.domain.uno.IUnoSpecialCardDefinition;
import boredgame.domain.uno.UnoAdditionalTurnInfo;
import boredgame.domain.uno.UnoPlayableCard;

/**
 * Created by Bernard Louw on 01/01/2017.
 */
public class UnoSpecialCardDefinition extends SpecialCardDefinition<EUnoSpecialCardType> implements IUnoSpecialCardDefinition {

    public UnoSpecialCardDefinition(final EUnoSpecialCardType eUnoSpecialCardType) {
        super(eUnoSpecialCardType);
    }

    @Override
    public boolean canCardBePlayed(final AbstractGameModel<?> gameModel) {
        return true;
    }

    @Override
    public void applyCardToModel(final AbstractGameModel<?> gameModel, final UnoAdditionalTurnInfo additionalTurnInfo) {

        getSpecialCardType().applyToModel((UnoGame<UnoPlayableCard<IUnoCardDefinition>, IUnoCardDefinition>)gameModel, additionalTurnInfo);

    }
}
