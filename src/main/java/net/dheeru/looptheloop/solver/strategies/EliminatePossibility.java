package net.dheeru.looptheloop.solver.strategies;

import net.dheeru.looptheloop.solver.Board;

/**
 * An interface to be implemented by any class that eliminates possibilities.
 */
public interface EliminatePossibility {
  boolean eliminate(Board board);
}
