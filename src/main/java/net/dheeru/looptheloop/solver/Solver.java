package net.dheeru.looptheloop.solver;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
  Solver(Board board) {
    initComponents(board);
  }

  public static void main(String args[]) throws Exception {
    Board board = Board.readBoard(args[0]);

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Solver(board).setVisible(true);
      }
    });
  }

  private void initComponents(Board board) {
    Panel2 jPanel2 = new Panel2(board);
    this.setContentPane(jPanel2);
    this.setSize(new Dimension(420, 420));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
          final ArrayList<Line> possibilities = nodes[i][j].getPossibilities();
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
            g.drawString(nodes[i][j].getNumLines() + "", SPACE + SPACE * j + SPACE / 3, SPACE + SPACE * i + SPACE / 2);
          }
        }
      }
    }

    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){

      //creates a copy of the Graphics instance
      Graphics2D g2d = (Graphics2D) g.create();

      //set the stroke of the copy, not the original
      Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1}, 0);
      g2d.setStroke(dashed);
      g2d.drawLine(x1, y1, x2, y2);

      //gets rid of the copy
      g2d.dispose();
    }
  }
}
