package net.dheeru.looptheloop.solver.strategies;

import net.dheeru.looptheloop.solver.Board;
import net.dheeru.looptheloop.solver.Line;
import net.dheeru.looptheloop.solver.Node;

import java.util.ArrayList;

/**
 * Eliminate cross possibilities.
 */
public class CrossEliminate implements EliminatePossibility {
  @Override
  public boolean eliminate(Board board) {
    final Node[][] nodes = board.getNodes();
    if (nodes.length == 0) {
      return false;
    }
    final int length = nodes.length;
    final int breadth = nodes[0].length;

    boolean changed = false;
    // Check if two lines form an L share or rotated L shape, then eliminate any other possibilities that can
    // intersect with it.
    for (int i = 0; i < nodes.length; i++) {
      for (int j = 0; j < nodes[i].length; j++) {
        final ArrayList<Line> lines = nodes[i][j].getLines();
        if (lines.contains(Line.LEFT) && lines.contains(Line.TOP)) {
          changed = eliminateTopLeft(i, j, board) || changed;
          changed = eliminateLeftTop(i, j, board) || changed;
        }
        if (lines.contains(Line.TOP) && lines.contains(Line.RIGHT)) {
          changed = eliminateTopRight(i, j, board) || changed;
          changed = eliminateRightTop(i, j, board) || changed;
        }
        if (lines.contains(Line.RIGHT) && lines.contains(Line.BOTTOM)) {
          changed = eliminateBottomRight(i, j, board) || changed;
          changed = eliminateRightBottom(i, j, board) || changed;
        }
        if (lines.contains(Line.BOTTOM) && lines.contains(Line.LEFT)) {
          changed = eliminateBottomLeft(i, j, board) || changed;
          changed = eliminateLeftBottom(i, j, board) || changed;
        }
      }
    }

    // If a straight vertical line is made from two lines a & b, then any other possibility of lines meeting at the
    // meeting point of a & b should be eliminated.
    for (int j = 0; j <= breadth; j++) {
      for (int i = 0; i < length - 1; i++) {
        if (j < breadth) {
          final ArrayList<Line> lines1 = nodes[i][j].getLines();
          final ArrayList<Line> lines2 = nodes[i + 1][j].getLines();
          if (lines1.contains(Line.LEFT) && lines2.contains(Line.LEFT)) {
            changed = eliminateLeftBottom(i, j, board) || changed;
            changed = nodes[i][j].eliminatePossibility(Line.BOTTOM) || changed;
          }
        } else {
          final ArrayList<Line> lines1 = nodes[i][j - 1].getLines();
          final ArrayList<Line> lines2 = nodes[i + 1][j - 1].getLines();
          if (lines1.contains(Line.RIGHT) && lines2.contains(Line.RIGHT)) {
            changed = nodes[i][j].eliminatePossibility(Line.BOTTOM) || changed;
          }
        }
      }
    }

    // If a straight horizontal line is made from two lines a & b, then any other possibility of lines meeting at the
    // meeting point of a & b should be eliminated.
    for (int i = 0; i <= length; i++) {
      for (int j = 0; j < breadth - 1; j++) {
        if (i < length) {
          final ArrayList<Line> lines1 = nodes[i][j].getLines();
          final ArrayList<Line> lines2 = nodes[i][j + 1].getLines();
          if (lines1.contains(Line.TOP) && lines2.contains(Line.TOP)) {
            changed = eliminateRightTop(i, j, board) || changed;
            changed = nodes[i][j].eliminatePossibility(Line.RIGHT) || changed;
          }
        } else {
          final ArrayList<Line> lines1 = nodes[i - 1][j].getLines();
          final ArrayList<Line> lines2 = nodes[i - 1][j + 1].getLines();
          if (lines1.contains(Line.BOTTOM) && lines2.contains(Line.BOTTOM)) {
            changed = nodes[i][j].eliminatePossibility(Line.RIGHT) || changed;
          }
        }
      }
    }

    return changed;
  }

  private static boolean eliminateLeftBottom(int i, int j, Board board) {
    if (i >= board.getNodes().length - 1) {
      return false;
    }

    return board.eliminatePossibility(i + 1, j, Line.LEFT);
  }

  private static boolean eliminateBottomLeft(int i, int j, Board board) {
    if (j <= 0) {
      return false;
    }

    return board.eliminatePossibility(i, j - 1, Line.BOTTOM);
  }

  private static boolean eliminateRightBottom(int i, int j, Board board) {
    if (i >= board.getNodes().length - 1) {
      return false;
    }

    return board.eliminatePossibility(i + 1, j, Line.RIGHT);
  }

  private static boolean eliminateBottomRight(int i, int j, Board board) {
    if (j >= board.getNodes()[i].length - 1) {
      return false;
    }

    return board.eliminatePossibility(i, j + 1, Line.BOTTOM);
  }

  private static boolean eliminateRightTop(int i, int j, Board board) {
    if (i <= 0) {
      return false;
    }

    return board.eliminatePossibility(i - 1, j, Line.RIGHT);
  }

  private static boolean eliminateTopRight(int i, int j, Board board) {
    if (j >= board.getNodes()[i].length - 1) {
      return false;
    }

    return board.eliminatePossibility(i, j + 1, Line.TOP);
  }

  private static boolean eliminateLeftTop(int i, int j, Board board) {
    if (i <= 0) {
      return false;
    }

    return board.eliminatePossibility(i - 1, j, Line.LEFT);
  }

  private static boolean eliminateTopLeft(int i, int j, Board board) {
    if (j <= 0) {
      return false;
    }

    return board.eliminatePossibility(i, j - 1, Line.TOP);
  }
}
