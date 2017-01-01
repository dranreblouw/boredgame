package boredgame.domain.uno.internal;

import boredgame.domain.card.ISpecialCardType;
import boredgame.domain.uno.IUnoCardDefinition;
import boredgame.domain.uno.UnoAdditionalTurnInfo;
import boredgame.domain.uno.UnoPlayableCard;
import boredgame.domain.uno.UnoSpecialPlayableCard;
import boredgame.exception.InvalidDataException;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public enum EUnoSpecialCardType implements ISpecialCardType {

    WILD_CARD_DRAW_FOUR(false) {
        @Override
        void applyToModel(final UnoGame<UnoPlayableCard<IUnoCardDefinition>, IUnoCardDefinition> gameModel, final UnoAdditionalTurnInfo additionalTurnInfo) {

            changeSuite(gameModel, additionalTurnInfo);
            //skip forward to next guy
            gameModel.advanceNextTurn();
            //add cards to the next player in line
            addNCardsToPlayer(gameModel, 4);
        }
    },
    DRAW_TWO(true) {
        @Override
        void applyToModel(final UnoGame<UnoPlayableCard<IUnoCardDefinition>, IUnoCardDefinition> gameModel, final UnoAdditionalTurnInfo additionalTurnInfo) {

            //skip forward to next guy
            gameModel.advanceNextTurn();
            //add cards to the next player in line
            addNCardsToPlayer(gameModel, 2);
        }
    },
    WILD_CARD(false) {
        @Override
        void applyToModel(final UnoGame<UnoPlayableCard<IUnoCardDefinition>, IUnoCardDefinition> gameModel, final UnoAdditionalTurnInfo additionalTurnInfo) {

            changeSuite(gameModel, additionalTurnInfo);
        }
    },
    SKIP_CARD(true) {
        @Override
        void applyToModel(final UnoGame<UnoPlayableCard<IUnoCardDefinition>, IUnoCardDefinition> gameModel, final UnoAdditionalTurnInfo additionalTurnInfo) {
            //skip the next guy
            gameModel.advanceNextTurn();
        }
    },
    REVERSE_CARD(true) {
        @Override
        void applyToModel(final UnoGame<UnoPlayableCard<IUnoCardDefinition>, IUnoCardDefinition> gameModel, final UnoAdditionalTurnInfo additionalTurnInfo) {
            gameModel.reverseGameDirection();
        }
    };

    private static void addNCardsToPlayer(final UnoGame<UnoPlayableCard<IUnoCardDefinition>, IUnoCardDefinition> gameModel, final int amountOfCardsToDraw) {
        IntStream.range(0, amountOfCardsToDraw).forEach(x -> gameModel.getCurrentPlayerTurn().getCardsInHand().addToDesk(gameModel.getNextCardFromStock()));
    }

    private static void changeSuite(final UnoGame<UnoPlayableCard<IUnoCardDefinition>, IUnoCardDefinition> gameModel, final UnoAdditionalTurnInfo additionalTurnInfo) {
        final String suiteStr = Optional.ofNullable(additionalTurnInfo.getProperties().get(EAdditionalTurnInfoType.CHANGE_COLOR_TO)).orElseThrow(() ->
                new InvalidDataException("A wild card was played without the next suite being specified"));
        final EUnoSuiteType unoSuiteType = Optional.ofNullable(EUnoSuiteType.valueOf(suiteStr))
                .orElseThrow(() -> new InvalidDataException("A wild card was played without a valid next suite being specified"));

        gameModel.setCurrentSuiteInPlay(unoSuiteType);
    }

    private boolean startingCard;

    EUnoSpecialCardType(final boolean startingCard) {
        this.startingCard = startingCard;
    }

    public static <T extends UnoPlayableCard<D>, D extends IUnoCardDefinition> Stream<T> getNCardsForSpecialType(EUnoSpecialCardType cardType, Integer cardNum) {
        Objects.nonNull(cardType);
        return IntStream.range(0, cardNum).boxed().map(x -> (T) new UnoSpecialPlayableCard(new UnoSpecialCardDefinition(cardType)));
    }

    public boolean isStartingCard() {
        return startingCard;
    }

    abstract void applyToModel(UnoGame<UnoPlayableCard<IUnoCardDefinition>, IUnoCardDefinition> gameModel, final UnoAdditionalTurnInfo additionalTurnInfo);
}
