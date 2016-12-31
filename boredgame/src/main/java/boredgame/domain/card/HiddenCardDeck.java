package boredgame.domain.card;

import org.apache.commons.lang3.SerializationUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class HiddenCardDeck<T extends IAbstractCard> {

    private List<T> cardDeck = new LinkedList<>();

    public void addToDesk(T card) {
        Objects.nonNull(card);
        cardDeck.add(card);
    }

    public void addAllToDesk(Collection<T> cards) {
        Objects.nonNull(cards);
        //get rid of the nulls
        Map<Boolean, List<T>> split = cards.stream().collect(Collectors.partitioningBy(Objects::isNull));
        cardDeck.addAll(split.get(Boolean.FALSE));
    }

    public Optional<T> removeFromDeck() {
        Optional<T> maybeCard = Optional.ofNullable(cardDeck.iterator().next());
        maybeCard.ifPresent(card -> cardDeck.iterator().remove());
        return maybeCard;
    }

    public T viewLastAddedCard() {
        if (!cardDeck.isEmpty()) {
            return SerializationUtils.clone(cardDeck.get(cardDeck.size() - 1));
        } else {
            return null;
        }
    }

    public List<T> viewDeck() {
        return Collections.unmodifiableList(cardDeck);
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
