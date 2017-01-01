package boredgame.domain.uno;

import boredgame.domain.card.ISuiteDefinition;
import boredgame.domain.uno.internal.EUnoSuiteType;

/**
 * Created by Bernard Louw on 01/01/2017.
 */
public interface IUnoSuiteCardDefinition<T extends EUnoSuiteType> extends ISuiteDefinition<T>, IUnoCardDefinition {
}
