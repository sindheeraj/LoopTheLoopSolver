package net.dheeru.looptheloop.solver;

import java.util.ArrayList;

/**
 * Represents each node in loop the loop solver.
 */
public class Node {
  private final ArrayList<Line> possibilities = new ArrayList<>();
  private final int numLines;

  private Node(int numLines) {
    possibilities.add(Line.TOP);
    possibilities.add(Line.BOTTOM);
    possibilities.add(Line.LEFT);
    possibilities.add(Line.RIGHT);
    this.numLines = numLines;
  }

  public static Node newNode(int numLines) {
    return new Node(numLines);
  }

  public int getNumLines() {
    return numLines;
  }

  public boolean eliminatePossibility(Line possibility) {
    return this.possibilities.remove(possibility);
  }

  public ArrayList<Line> getPossibilities() {
    return possibilities;
  }
}
