package boredgame.domain.gamelobby;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class GameRequestValidator implements Validator {

    /**
     * This Validator validates *just* Person instances
     */
    public boolean supports(Class<?> clazz) {
        return GameRequest.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {
        GameRequest gameRequest = (GameRequest) obj;
        if(gameRequest.getNumberOfParticipants() < gameRequest.getParticipants().size())
        e.rejectValue("Number of requested participants cannot be smaller than paticipants supplied", "numberOfParticipants");
    }
}