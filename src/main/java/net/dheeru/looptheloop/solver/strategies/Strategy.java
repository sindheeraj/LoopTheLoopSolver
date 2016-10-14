package net.dheeru.looptheloop.solver.strategies;

/**
 * Return different strategies.
 */
public class Strategy {
  private Strategy() {
  }

  public static EliminatePossibility[] getEliminatePossibilityStrategies() {
    return new EliminatePossibility[] {
        new ZeroEliminate(),
    };
  }

  public static SetStrategy[] getSetStrategies() {
    return new SetStrategy[] {
        new PossibilitiesMatchNumLinesSetLines(),
    };
  }
}
