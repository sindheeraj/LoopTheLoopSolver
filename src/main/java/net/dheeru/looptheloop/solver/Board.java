package net.dheeru.looptheloop.solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a board of loop the loop game.
 */
public class Board {
  private final Node[][] nodes;

  private Board(int rows, int cols) {
    this.nodes = new Node[rows][cols];
  }

  public static Board readBoard(String file) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(file));
    final int rows = scanner.nextInt();
    final int cols = scanner.nextInt();

    Board board = new Board(rows, cols);
    board.init(scanner);

    return board;
  }

  private void init(Scanner in) {
    for (int i = 0; i < this.nodes.length; i++) {
      for (int j = 0; j < this.nodes[i].length; j++) {
        char b = nextByte(in);
        if (b == '-') {
          this.nodes[i][j] = Node.newNode(-1);
        } else {
          this.nodes[i][j] = Node.newNode(b - '0');
        }
      }
    }
  }

  private static char nextByte(Scanner in) {
    char b = in.next(".").charAt(0);
    while (b > '9' && b < '0' && b != '-') {
      b = in.next(".").charAt(0);
    }

    return b;
  }

  public Node[][] getNodes() {
    return nodes;
  }

  public boolean eliminatePossibility(int i, int j, Line possibility) {
    boolean removed = false;
    removed = nodes[i][j].eliminatePossibility(possibility) || removed;
    switch (possibility) {
      case TOP:
        if (i > 0) {
          removed = nodes[i - 1][j].eliminatePossibility(Line.BOTTOM) || removed;
        }
        break;
      case RIGHT:
        if (j < nodes[i].length - 1) {
          removed = nodes[i][j + 1].eliminatePossibility(Line.LEFT) || removed;
        }
        break;
      case LEFT:
        if (j > 0) {
          removed = nodes[i][j - 1].eliminatePossibility(Line.RIGHT) || removed;
        }
        break;
      case BOTTOM:
        if (i < nodes.length - 1) {
          removed = nodes[i + 1][j].eliminatePossibility(Line.TOP) || removed;
        }
        break;
    }

    return removed;
  }
}
