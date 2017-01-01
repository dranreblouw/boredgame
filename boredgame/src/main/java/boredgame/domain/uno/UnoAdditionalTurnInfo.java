package boredgame.domain.uno;

import boredgame.domain.uno.internal.EAdditionalTurnInfoType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bernard Louw on 01/01/2017.
 */
public class UnoAdditionalTurnInfo {

    Map<EAdditionalTurnInfoType, String> properties = new HashMap<>();

    public Map<EAdditionalTurnInfoType, String> getProperties() {
        return properties;
    }

}
