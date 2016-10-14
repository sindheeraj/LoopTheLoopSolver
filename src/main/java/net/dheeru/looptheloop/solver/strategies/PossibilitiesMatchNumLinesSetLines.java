package net.dheeru.looptheloop.solver.strategies;

import net.dheeru.looptheloop.solver.Board;
import net.dheeru.looptheloop.solver.Line;
import net.dheeru.looptheloop.solver.Node;

import java.util.ArrayList;

/**
 * Set lines if num possibilities match the num lines required.
 */
public class PossibilitiesMatchNumLinesSetLines implements SetStrategy {
  @Override
  public boolean set(Board board) {
    final Node[][] nodes = board.getNodes();

    boolean changed = false;
    for (int i = 0; i < nodes.length; i++) {
      for (int j = 0; j < nodes[i].length; j++) {
        final ArrayList<Line> possibilities = nodes[i][j].getPossibilities();
        if (nodes[i][j].getNumLines() == possibilities.size()) {
          for (Line line : possibilities) {
            changed = board.setLine(i, j, line) || changed;
          }
        }
      }
    }

    return changed;
  }
}
