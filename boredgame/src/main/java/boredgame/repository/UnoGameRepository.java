package boredgame.repository;

import boredgame.domain.uno.UnoGame;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public interface UnoGameRepository extends MongoRepository<UnoGame, String> {
}
