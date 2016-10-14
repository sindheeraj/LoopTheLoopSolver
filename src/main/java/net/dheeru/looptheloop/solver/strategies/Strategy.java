package net.dheeru.looptheloop.solver.strategies;

import net.dheeru.looptheloop.solver.strategies.eliminate.CrossEliminate;
import net.dheeru.looptheloop.solver.strategies.eliminate.EliminatePossibility;
import net.dheeru.looptheloop.solver.strategies.set.PossibilitiesMatchNumLinesSetLines;
import net.dheeru.looptheloop.solver.strategies.set.SetStrategy;

/**
 * Return different strategies.
 */
public class Strategy {
  private Strategy() {
  }

  public static EliminatePossibility[] getEliminatePossibilityStrategies() {
    return new EliminatePossibility[] {
        new EliminatePossibility.ZeroEliminate(),
        new CrossEliminate(),
    };
  }

  public static SetStrategy[] getSetStrategies() {
    return new SetStrategy[] {
        new PossibilitiesMatchNumLinesSetLines(),
    };
  }
}
