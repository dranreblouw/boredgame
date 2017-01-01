package boredgame.repository;

import boredgame.domain.users.UserAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {
}
