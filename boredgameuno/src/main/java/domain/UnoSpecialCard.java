package domain;

import domain.card.SpecialCard;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class UnoSpecialCard extends SpecialCard<EUnoSpecialCardType> implements UnoCard {

    public UnoSpecialCard(EUnoSpecialCardType specialCardDefiniition) {
        super(specialCardDefiniition);
    }
}
