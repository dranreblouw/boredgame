package boredgame.domain.card;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class HiddenCardDeck<T extends IAbstractCard> {

    private List<T> cardDeck = new LinkedList<T>();

    @Nonnull
    public void addToDesk(T card) {
        Objects.nonNull(card);
        cardDeck.add(card);
    }

    public void addAllToDesk(Collection<T> cards) {
        Objects.nonNull(cards);
        //get rid of the nulls
        Map<Boolean, List<T>> split = cards.stream().collect(Collectors.partitioningBy(o -> o == null));
        cardDeck.addAll(split.get(Boolean.FALSE));
    }

    public Optional<T> removeFromDeck() {
        Optional<T> maybeCard = Optional.ofNullable(cardDeck.iterator().next());
        maybeCard.ifPresent(card -> cardDeck.iterator().remove());
        return maybeCard;
    }

    public boolean isDeskEmpty() {
        return cardDeck.isEmpty();
    }

    public int getDeckSize() {
        return cardDeck.size();
    }

    protected List<T> getCardDeck() {
        return cardDeck;
    }
}
