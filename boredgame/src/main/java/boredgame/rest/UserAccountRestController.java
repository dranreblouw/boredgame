package boredgame.rest;

import boredgame.domain.users.UserAccount;
import boredgame.exception.InvalidDataException;
import boredgame.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
@RestController
@RequestMapping("/api/users")
public class UserAccountRestController extends AbstractRestController {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserAccount addUserAccount(UserAccount userAccount, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidDataException(result.toString());
        }
        userAccount.setId(null);
        userAccount.getRoles().clear();
        return userAccountRepository.insert(userAccount);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserAccount find(@PathVariable("id") UserAccount user) {
        return user;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

}
