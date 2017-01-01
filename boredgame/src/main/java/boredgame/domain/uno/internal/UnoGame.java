package boredgame.domain.uno.internal;

import boredgame.domain.card.HiddenCardDeck;
import boredgame.domain.game.AbstractGameModel;
import boredgame.domain.game.EGameDirection;
import boredgame.domain.game.EGameType;
import boredgame.domain.uno.IUnoCardDefinition;
import boredgame.domain.uno.UnoAdditionalTurnInfo;
import boredgame.domain.uno.UnoPlayableCard;
import boredgame.exception.InvalidDataException;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static boredgame.domain.uno.internal.EUnoSpecialCardType.*;

public class UnoGame<T extends UnoPlayableCard<D>, D extends IUnoCardDefinition> extends AbstractGameModel<UnoPlayer<D>> {

    private static final int REQUIRED_DECK_SIZE = 108;

    private HiddenCardDeck<T, D> stockCardPile = new HiddenCardDeck<>();
    private HiddenCardDeck<T, D> disgardCardPile = new HiddenCardDeck<>();

    private Integer currentScore;

    private EUnoSuiteType currentSuiteInPlay;
    private Integer currentNumberInPlay;

    UnoGame(final List<UnoPlayer<D>> players) {
        super(players);
        setRandomFirstPlayer();
        dealOutInitialDeck(createInitialDeck());
    }

    private void setRandomFirstPlayer() {
        int index = new Random().nextInt(getGameParticipantList().size());
        @SuppressWarnings("unchecked")
        final UnoPlayer<D> firstPlayer = (UnoPlayer<D>) getGameParticipantList().toArray()[index];
        setCurrentUserTurn(firstPlayer);
    }

    private HiddenCardDeck<T, D> createInitialDeck() {

        HiddenCardDeck<T, D> initialDeck = new HiddenCardDeck<>();

        EUnoSuiteType.<T, D>getCardOfNumberForEachSuite(0)
                .forEach(initialDeck::addToDesk);
        //for card numbers 1 to 10
        IntStream.range(1, 10).boxed()
                //add to cards of each suite
                .flatMap(cardNum -> EUnoSuiteType.<T, D>getCardOfNumberForEachSuite(cardNum, 2))
                .forEach(initialDeck::addToDesk);

        Stream.of(DRAW_TWO, DRAW_TWO, SKIP_CARD, REVERSE_CARD)
                .flatMap(cardType -> EUnoSpecialCardType.<T, D>getNCardsForSpecialType(cardType, 2))
                .forEach(initialDeck::addToDesk);
        Stream.of(WILD_CARD_DRAW_FOUR, WILD_CARD)
                .flatMap(cardType -> EUnoSpecialCardType.<T, D>getNCardsForSpecialType(cardType, 4))
                .forEach(initialDeck::addToDesk);

        if (initialDeck.getDeckSize() != REQUIRED_DECK_SIZE) {
            throw new InvalidDataException("A Uno card desk has to contain exactly " + REQUIRED_DECK_SIZE + "cards");
        }
        initialDeck.shuffleCards();
        return initialDeck;
    }


    private void dealOutInitialDeck(final HiddenCardDeck<T, D> unoCardDeck) {

        //deal out to participants
        IntStream.range(0, 7).boxed()
                .flatMap(integer -> getGameParticipantList().stream())
                .forEach(unoPlayer -> unoCardDeck.removeFromTopDeck()
                        .ifPresent(unoPlayer.getCardsInHand()::addToDesk));

        //add the rest of the cards to the stock pile
        stockCardPile.addAllToDesk(unoCardDeck.getCardDeck());

        //start the game
        boolean appropriateCardFound = false;
        while (appropriateCardFound) {
            T playableCard = stockCardPile.peekTopOfDeck().orElseThrow(() -> new InvalidDataException("Deck does not have enough cards to start game"));
            //legit card found
            if (!playableCard.isSpecialCard() ||
                    (playableCard.isSpecialCard() &&
                            !((UnoSpecialCardDefinition) playableCard.getCardDefinition())
                                    .getSpecialCardType().isStartingCard())) {
                stockCardPile.removeFromTopDeck().ifPresent(t -> this.playCard(getCurrentPlayerTurn(), playableCard, new UnoAdditionalTurnInfo()));
                appropriateCardFound = true;
            } else {
                //just shuffle again
                stockCardPile.shuffleCards();
            }
        }
    }

    private T disgardCard(T playableCard) {

        disgardCardPile.addToDesk(playableCard);
        return playableCard;
    }

    void setCurrentNumberInPlay(final Integer currentNumberInPlay) {
        this.currentNumberInPlay = currentNumberInPlay;
        this.currentScore += currentNumberInPlay;
    }

    void setCurrentSuiteInPlay(final EUnoSuiteType currentSuiteInPlay) {
        this.currentSuiteInPlay = currentSuiteInPlay;
    }

    UnoPlayer<D> advanceNextTurn() {
        return continueToNextTurn();
    }

    EGameDirection reverseGameDirection() {
        return changeGameDirection();
    }

    T getNextCardFromStock() {
        Optional<T> nextCard = stockCardPile.removeFromTopDeck();
        if (nextCard.isPresent()) {
            return nextCard.get();
        } else {
            transferDisgardedCardsToStock();
            return stockCardPile.removeFromTopDeck().orElseThrow(() -> new InvalidDataException("Something went wrong... stock of cards is empty"));
        }

    }

    private void transferDisgardedCardsToStock() {

        final T savedTopCard = disgardCardPile.removeFromTopDeck()
                .orElseThrow(() -> new InvalidDataException("Something went wrong... disgard pile is empty"));
        stockCardPile = disgardCardPile;
        disgardCardPile = new HiddenCardDeck<>();
        disgardCardPile.addToDesk(savedTopCard);
    }

    @Override
    public EGameType getGameType() {
        return EGameType.CARD;
    }

    EUnoSuiteType getCurrentSuiteInPlay() {
        return currentSuiteInPlay;
    }

    Integer getCurrentNumberInPlay() {
        return currentNumberInPlay;
    }

    Integer getCurrentScore() {
        return currentScore;
    }

    void playCard(UnoPlayer<D> unoPlayer, T iUnoCard, UnoAdditionalTurnInfo additionalTurnInfo) {

        if (unoPlayer != getCurrentPlayerTurn()) {
            throw new InvalidDataException("It is not player's " + unoPlayer.getUserAccountID() + "turn");
        }

        //check if valid card is being played
        final boolean validCard = iUnoCard.getCardDefinition().canCardBePlayed(this);
        if (!validCard) {
            throw new InvalidDataException("The card played %s is not valid. You can currently play a card with number %s or a card of color %s or any word card ",
                    new String[]{iUnoCard.getCardDefinition().toString(), getCurrentNumberInPlay() + "", getCurrentSuiteInPlay().toString()});

        }
        //remove the played card from the players deck
        unoPlayer.removeCard(iUnoCard.getCardDefinition());
        //throw it onto the disgard pile
        disgardCard(iUnoCard);
        //apply the card to the game
        iUnoCard.getCardDefinition().applyCardToModel(this, additionalTurnInfo);

    }
}
