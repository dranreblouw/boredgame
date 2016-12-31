package boredgame.service;

import boredgame.domain.UserAccount;
import boredgame.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
@RestController
@RequestMapping("/api/users")
public class UserAccountController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserAccount addUserAccount(UserAccount userAccount)
    {
        return userAccountRepository.insert(userAccount);
    }

}
