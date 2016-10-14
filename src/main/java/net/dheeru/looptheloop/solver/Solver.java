package net.dheeru.looptheloop.solver;

import net.dheeru.looptheloop.solver.strategies.eliminate.EliminatePossibility;
import net.dheeru.looptheloop.solver.strategies.set.SetStrategy;
import net.dheeru.looptheloop.solver.strategies.Strategy;

import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

/**
 * Main class for solving.
 */
public class Solver extends javax.swing.JFrame {
  private Solver(Board board) {
    final EliminatePossibility[] eliminatePossibilityStrategies = Strategy.getEliminatePossibilityStrategies();
    final SetStrategy[] setStrategies = Strategy.getSetStrategies();
    boolean changed = true;
    while (changed) {
      boolean changedThisRound = false;
      for (EliminatePossibility eliminatePossibility : eliminatePossibilityStrategies) {
        changedThisRound = eliminatePossibility.eliminate(board) || changedThisRound;
      }

      for (SetStrategy setStrategy : setStrategies) {
        changedThisRound = setStrategy.set(board) || changedThisRound;
      }

      changed = changedThisRound;
    }
    drawAnswer(board);
  }

  public static void main(String args[]) throws Exception {
    Board board = Board.readBoard(args[0]);

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Solver(board).setVisible(true);
      }
    });
  }

  private void drawAnswer(Board board) {
    Panel2 jPanel2 = new Panel2(board);
    this.setContentPane(jPanel2);
    this.setSize(new Dimension(420, 420));
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  }

  private static class Panel2 extends JPanel {
    private final Board board;
    private static final int SPACE = 40;
    Panel2(Board board) {
      // set a preferred size for the custom panel.
      setPreferredSize(new Dimension(420, 420));
      setSize(new Dimension(420, 420));
      this.board = board;
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      final Node[][] nodes = board.getNodes();
      for (int i = 0; i < nodes.length; i++) {
        for (int j = 0; j < nodes[i].length; j++) {
          final ArrayList<Line> lines = nodes[i][j].getLines();
          final ArrayList<Line> possibilities = nodes[i][j].getPossibilities();
          possibilities.removeAll(lines);
          for (Line possibility : possibilities) {
            int x1 = 0;
            int x2 = 0;
            int y1 = 0;
            int y2 = 0;
            switch (possibility) {
              case TOP:
                x1 = SPACE + j * SPACE;
                x2 = SPACE + j * SPACE + SPACE;
                y1 = SPACE * i + SPACE;
                y2 = SPACE * i + SPACE;
                break;
              case BOTTOM:
                x1 = SPACE + j * SPACE;
                x2 = SPACE + j * SPACE + SPACE;
                y1 = SPACE * i + SPACE + SPACE;
                y2 = SPACE * i + SPACE + SPACE;
                break;
              case LEFT:
                x1 = SPACE + j * SPACE;
                x2 = SPACE + j * SPACE;
                y1 = SPACE * i + SPACE;
                y2 = SPACE * i + SPACE + SPACE;
                break;
              case RIGHT:
                x1 = SPACE + j * SPACE + SPACE;
                x2 = SPACE + j * SPACE + SPACE;
                y1 = SPACE * i + SPACE;
                y2 = SPACE * i + SPACE + SPACE;
                break;
            }
            drawDashedLine(g, x1, y1, x2, y2);
          }

          for (Line line : lines) {
            int x1 = 0;
            int x2 = 0;
            int y1 = 0;
            int y2 = 0;
            switch (line) {
              case TOP:
                x1 = SPACE + j * SPACE;
                x2 = SPACE + j * SPACE + SPACE;
                y1 = SPACE * i + SPACE;
                y2 = SPACE * i + SPACE;
                break;
              case BOTTOM:
                x1 = SPACE + j * SPACE;
                x2 = SPACE + j * SPACE + SPACE;
                y1 = SPACE * i + SPACE + SPACE;
                y2 = SPACE * i + SPACE + SPACE;
                break;
              case LEFT:
                x1 = SPACE + j * SPACE;
                x2 = SPACE + j * SPACE;
                y1 = SPACE * i + SPACE;
                y2 = SPACE * i + SPACE + SPACE;
                break;
              case RIGHT:
                x1 = SPACE + j * SPACE + SPACE;
                x2 = SPACE + j * SPACE + SPACE;
                y1 = SPACE * i + SPACE;
                y2 = SPACE * i + SPACE + SPACE;
                break;
            }
            g.drawLine(x1, y1, x2, y2);
          }
          g.drawString(nodes[i][j].getNumLines() + "", SPACE + SPACE * j + SPACE / 3, SPACE + SPACE * i + SPACE / 2);
        }
      }
    }

    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){

      Graphics2D g2d = (Graphics2D) g.create();

      Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 1);
      g2d.setStroke(dashed);
      g2d.drawLine(x1, y1, x2, y2);

      //gets rid of the copy
      g2d.dispose();
    }
  }
}

