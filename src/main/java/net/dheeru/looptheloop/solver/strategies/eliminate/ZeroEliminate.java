package net.dheeru.looptheloop.solver.strategies.eliminate;

import net.dheeru.looptheloop.solver.Board;
import net.dheeru.looptheloop.solver.Line;
import net.dheeru.looptheloop.solver.Node;

/**
 * Eliminates possibilities if a number is 0.
 */
public class ZeroEliminate implements EliminatePossibility {
  @Override
  public boolean eliminate(Board board) {
    final Node[][] nodes = board.getNodes();

    boolean removed = false;
    for (int i = 0; i < nodes.length; i++) {
      for (int j = 0; j < nodes[i].length; j++) {
        if (nodes[i][j].getNumLines() == 0) {
          removed = board.eliminatePossibility(i, j, Line.TOP) || removed;
          removed = board.eliminatePossibility(i, j, Line.RIGHT) || removed;
          removed = board.eliminatePossibility(i, j, Line.BOTTOM) || removed;
          removed = board.eliminatePossibility(i, j, Line.LEFT) || removed;
        }
      }
    }

    return removed;
  }
}
