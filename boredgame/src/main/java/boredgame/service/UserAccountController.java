package boredgame.service;

import boredgame.domain.users.UserAccount;
import boredgame.exception.ErrorResponse;
import boredgame.exception.InvalidDataException;
import boredgame.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
@RestController
@RequestMapping("/api/users")
public class UserAccountController {

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
    public UserAccount showUserForm(@PathVariable("id") UserAccount user) {
        return user;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }


}
