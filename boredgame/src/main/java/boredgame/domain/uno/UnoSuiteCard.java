package boredgame.domain.uno;


import boredgame.domain.card.SuiteCard;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class UnoSuiteCard extends SuiteCard<EUnoSuiteType> implements UnoCard {

    public UnoSuiteCard(EUnoSuiteType suiteDefinition, Integer cardNumber) {
        super(suiteDefinition, cardNumber);
    }
}
