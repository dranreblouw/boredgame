package boredgame.repository;

import boredgame.domain.gamelobby.EGameRequestStatus;
import boredgame.domain.gamelobby.GameRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Bernard Louw on 02/01/2017.
 */
public interface GameRequestRepository extends MongoRepository<GameRequest, String> {

    List<GameRequest> findByGameRequestStatus(EGameRequestStatus gameRequestStatus);
}
