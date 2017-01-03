package boredgame.rest;

import boredgame.domain.uno.internal.UnoGame;
import boredgame.repository.UnoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
@RestController
@RequestMapping("/api/game/uno")
public class UnoRestController extends AbstractRestController {

    @Autowired
    private UnoGameRepository unoGameRepository;

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public UnoGame newGame() {

        //UnoCardDeck unoCardDeck = new UnoGameService().getObject();


        //unoGameRepository.save(game);
        return null;
    }



}
