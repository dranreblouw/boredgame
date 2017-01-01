package boredgame.domain.card;

import org.apache.commons.lang3.SerializationUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public class HiddenCardDeck<T extends IAbstractCard<D>, D extends ICardDefinition> {

    private Queue<T> cardDeck = new LinkedList<>();

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

    public Optional<T> removeFromTopDeck() {
        Optional<T> maybeCard = Optional.ofNullable(cardDeck.iterator().next());
        maybeCard.ifPresent(card -> cardDeck.poll());
        return maybeCard;
    }

    //package protected
    boolean removeFromDeck(D cardDefinition) {
        final Iterator<T> iterator = cardDeck.iterator();
        while (iterator.hasNext()) {
            T playableCard = iterator.next();
            if (playableCard.getCardDefinition().equals(cardDefinition)) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    public Optional<T> peekTopOfDeck() {
        return Optional.ofNullable(SerializationUtils.clone(cardDeck.peek()));
    }

    public void shuffleCards() {
        List<T> tempList = new ArrayList<>(cardDeck);
        Collections.shuffle(tempList);
        cardDeck.clear();
        cardDeck.addAll(tempList);
    }

    public Collection<T> viewDeck() {
        return Collections.unmodifiableCollection(cardDeck);
    }

    public boolean isDeskEmpty() {
        return cardDeck.isEmpty();
    }

    public int getDeckSize() {
        return cardDeck.size();
    }

    public Collection<T> getCardDeck() {
        return Collections.unmodifiableCollection(cardDeck);
    }
}
