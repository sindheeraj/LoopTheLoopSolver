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
}
