package boredgame.domain.uno.internal;

import boredgame.domain.card.SuiteCardDefinition;
import boredgame.domain.game.AbstractGameModel;
import boredgame.domain.uno.IUnoSuiteCardDefinition;
import boredgame.domain.uno.UnoAdditionalTurnInfo;

/**
 * Created by Bernard Louw on 01/01/2017.
 */
public class UnoSuiteCardDefinition extends SuiteCardDefinition<EUnoSuiteType> implements IUnoSuiteCardDefinition<EUnoSuiteType> {

    public UnoSuiteCardDefinition(final Integer cardNo, final EUnoSuiteType eUnoSuiteType) {
        super(cardNo, eUnoSuiteType);
    }

    @Override
    public boolean isCardNumberValid(final Integer cardNumber) {
        return getSuiteType().isCardNumberValid(cardNumber);
    }

    @Override
    public boolean canCardBePlayed(final AbstractGameModel<?> gameModel) {
        UnoGame<?, ?> unoGame = (UnoGame<?, ?>) gameModel;
        final EUnoSuiteType currentSuiteInPlay = unoGame.getCurrentSuiteInPlay();

        //suite is undefined - change color card was played
        if (currentSuiteInPlay == null) {
            return true;
        }
        //if it matches number
        if (unoGame.getCurrentNumberInPlay().equals(getCardNumber())) {
            return true;
        }
        //matches suite
        if (currentSuiteInPlay != null && currentSuiteInPlay.equals(getSuiteType())) {
            return true;
        }
        return false;
    }

    @Override
    public void applyCardToModel(final AbstractGameModel<?> gameModel, final UnoAdditionalTurnInfo additionalTurnInfo) {
        UnoGame<?,?> unoGame = (UnoGame<?, ?>) gameModel;
        unoGame.setCurrentNumberInPlay(getCardNumber());
        unoGame.setCurrentSuiteInPlay(getSuiteType());
        unoGame.advanceNextTurn();
    }
}
