package boredgame.domain.game;

import java.util.NavigableSet;
import java.util.Optional;

/**
 * Created by Bernard Louw on 01/01/2017.
 */
public enum EGameDirection {

    FORWARD {
        @Override
        public <U extends Player> U getFirstPlayer(final NavigableSet<U> gameParticipantList) {
            return gameParticipantList.first();
        }

        @Override
        protected <U extends Player> U getNextLogicalPlayer(AbstractGameModel<U> abstractGameModel) {

            final NavigableSet<U> gameParticipantList = abstractGameModel
                    .getGameParticipantList();

            return Optional.ofNullable(gameParticipantList
                    .higher(abstractGameModel.getCurrentPlayerTurn()))
                    .orElseGet(() -> gameParticipantList.first());
        }


    },
    REVERSE {
        @Override
        public <U extends Player> U getFirstPlayer(final NavigableSet<U> gameParticipantList) {
            return gameParticipantList.last();
        }

        @Override
        protected <U extends Player> U getNextLogicalPlayer(AbstractGameModel<U> abstractGameModel) {
            final NavigableSet<U> gameParticipantList = abstractGameModel
                    .getGameParticipantList();

            return Optional.ofNullable(gameParticipantList
                    .lower(abstractGameModel.getCurrentPlayerTurn()))
                    .orElseGet(() -> gameParticipantList.last());
        }


    };

    public abstract <U extends Player> U getFirstPlayer(NavigableSet<U> gameParticipantList);

    protected abstract <U extends Player> U getNextLogicalPlayer(AbstractGameModel<U> abstractGameModel);

    public static <U extends Player> U getNextPayer(AbstractGameModel<U> abstractGameModel, EGameDirection currentGameDirection) {
        final Player currentUserTurn = abstractGameModel.getCurrentPlayerTurn();
        if (currentUserTurn != null) {
            return currentGameDirection.getNextLogicalPlayer(abstractGameModel);
        } else {
            return currentGameDirection.getFirstPlayer(abstractGameModel.getGameParticipantList());
        }
    }

    public <U extends Player> U getNextPayer(AbstractGameModel<U> abstractGameModel) {
        return EGameDirection.getNextPayer(abstractGameModel, this);
    }


}
