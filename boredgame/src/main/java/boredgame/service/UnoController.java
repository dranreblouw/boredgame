package boredgame.service;

import boredgame.domain.uno.UnoGame;
import boredgame.repository.UnoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by BERNARD7 on 31/12/2016.
 */
@RestController
@RequestMapping("/api/game/uno")
public class UnoController {

    @Autowired
    private UnoGameRepository unoGameRepository;

    @RequestMapping("new")
    public UnoGame newGame()
    {

    }


}
