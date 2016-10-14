package net.dheeru.looptheloop.solver.strategies.eliminate;

import net.dheeru.looptheloop.solver.Board;
import net.dheeru.looptheloop.solver.Line;
import net.dheeru.looptheloop.solver.Node;

import java.util.ArrayList;

/**
 * If a line has no possibility of continuing on either end then that line should be eliminated.
 */
public class HangingLineEliminate implements EliminatePossibility {
  @Override
  public boolean eliminate(Board board) {
    final Node[][] nodes = board.getNodes();
    if (nodes.length == 0) {
      return false;
    }

    boolean changed = false;
    for (int i = 0; i < nodes.length; i++) {
      for (int j = 0; j < nodes[i].length; j++) {
        final ArrayList<Line> possibilities = new ArrayList<>(nodes[i][j].getPossibilities());

        for (Line possibility : possibilities) {
          switch (possibility) {
            case TOP:
              if (checkDanglingTop(i, j, nodes, board)) {
                changed = board.eliminatePossibility(i, j, Line.TOP) || changed;
              }
              break;
            case RIGHT:
              if (checkDanglingRight(i, j, nodes, board)) {
                changed = board.eliminatePossibility(i, j, Line.RIGHT) || changed;
              }
              break;
            case BOTTOM:
              if (checkDanglingBottom(i, j, nodes, board)) {
                changed = board.eliminatePossibility(i, j, Line.BOTTOM) || changed;
              }
              break;
            case LEFT:
              if (checkDanglingLeft(i, j, nodes, board)) {
                changed = board.eliminatePossibility(i, j, Line.LEFT) || changed;
              }
              break;
          }
        }
      }
    }
    return changed;
  }

  private static boolean checkDanglingLeft(int i, int j, Node[][] nodes, Board board) {
    // check top
    boolean topExists = false;
    if (nodes[i][j].hasPossibility(Line.TOP)) {
      topExists = true;
    }
    if (j > 0) {
      if (nodes[i][j - 1].hasPossibility(Line.TOP)) {
        topExists = true;
      }
    }
    if (i > 0) {
      if (nodes[i - 1][j].hasPossibility(Line.LEFT)) {
        topExists = true;
      }
    }

    // check bottom
    boolean bottomExists = false;
    if (nodes[i][j].hasPossibility(Line.BOTTOM)) {
      bottomExists = true;
    }
    if (j > 0) {
      if (nodes[i][j - 1].hasPossibility(Line.BOTTOM)) {
        bottomExists = true;
      }
    }
    if (i < nodes.length - 1) {
      if (nodes[i + 1][j].hasPossibility(Line.LEFT)) {
        bottomExists = true;
      }
    }

    return !topExists || !bottomExists;
  }

  private static boolean checkDanglingBottom(int i, int j, Node[][] nodes, Board board) {
    // check left
    boolean leftExists = false;
    if (nodes[i][j].hasPossibility(Line.LEFT)) {
      leftExists = true;
    }
    if (j > 0) {
      if (nodes[i][j - 1].hasPossibility(Line.BOTTOM)) {
        leftExists = true;
      }
    }
    if (i < nodes.length - 1) {
      if (nodes[i + 1][j].hasPossibility(Line.LEFT)) {
        leftExists = true;
      }
    }

    // check right
    boolean rightExists = false;
    if (nodes[i][j].hasPossibility(Line.RIGHT)) {
      rightExists = true;
    }
    if (j < nodes[0].length - 1) {
      if (nodes[i][j + 1].hasPossibility(Line.BOTTOM)) {
        rightExists = true;
      }
    }
    if (i < nodes.length - 1) {
      if (nodes[i + 1][j].hasPossibility(Line.RIGHT)) {
        rightExists = true;
      }
    }

    return !leftExists || !rightExists;
  }

  private static boolean checkDanglingRight(int i, int j, Node[][] nodes, Board board) {
    // check top
    boolean topExists = false;
    if (nodes[i][j].hasPossibility(Line.TOP)) {
      topExists = true;
    }
    if (j < nodes[0].length - 1) {
      if (nodes[i][j + 1].hasPossibility(Line.TOP)) {
        topExists = true;
      }
    }
    if (i > 0) {
      if (nodes[i - 1][j].hasPossibility(Line.RIGHT)) {
        topExists = true;
      }
    }

    // check bottom
    boolean bottomExists = false;
    if (nodes[i][j].hasPossibility(Line.BOTTOM)) {
      bottomExists = true;
    }
    if (j < nodes[0].length - 1) {
      if (nodes[i][j + 1].hasPossibility(Line.BOTTOM)) {
        bottomExists = true;
      }
    }
    if (i < nodes.length - 1) {
      if (nodes[i + 1][j].hasPossibility(Line.RIGHT)) {
        bottomExists = true;
      }
    }

    return !topExists || !bottomExists;
  }

  private static boolean checkDanglingTop(int i, int j, Node[][] nodes, Board board) {
    // check left
    boolean leftExists = false;
    if (nodes[i][j].hasPossibility(Line.LEFT)) {
      leftExists = true;
    }
    if (j > 0) {
      if (nodes[i][j - 1].hasPossibility(Line.TOP)) {
        leftExists = true;
      }
    }
    if (i > 0) {
      if (nodes[i - 1][j].hasPossibility(Line.LEFT)) {
        leftExists = true;
      }
    }

    // check right
    boolean rightExists = false;
    if (nodes[i][j].hasPossibility(Line.RIGHT)) {
      rightExists = true;
    }
    if (j < nodes[0].length - 1) {
      if (nodes[i][j + 1].hasPossibility(Line.TOP)) {
        rightExists = true;
      }
    }
    if (i > 0) {
      if (nodes[i - 1][j].hasPossibility(Line.RIGHT)) {
        rightExists = true;
      }
    }

    return !leftExists || !rightExists;
  }
}
