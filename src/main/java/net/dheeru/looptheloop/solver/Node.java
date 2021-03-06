package net.dheeru.looptheloop.solver;

import java.util.ArrayList;

/**
 * Represents each node in loop the loop solver.
 */
public class Node {
  private final ArrayList<Line> possibilities = new ArrayList<>();
  private final ArrayList<Line> lines = new ArrayList<>();
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

  public boolean addLine(Line line) {
    if (lines.contains(line)) {
      return false;
    }
    lines.add(line);
    return false;
  }

  public ArrayList<Line> getLines() {
    return lines;
  }

  public boolean hasPossibility(Line line) {
    return possibilities.contains(line);
  }
}
