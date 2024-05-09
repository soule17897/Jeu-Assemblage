package view;

import model.PieceModel;

import javax.swing.*;
import java.awt.*;

public class PieceView extends JFrame {
    private transient PieceModel pieceModel;

    public PieceView(PieceModel pieceModel) {
        this.pieceModel = pieceModel;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(pieceModel.getPiece().getClass().getSimpleName());

        // Utilisez un JPanel personnalisé pour dessiner la pièce
        PiecePanel piecePanel = new PiecePanel(pieceModel);

        piecePanel.setPreferredSize(new Dimension(200, 200));

        add(piecePanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateView() {
        repaint();
    }

    // JPanel personnalisé pour dessiner la pièce
    private class PiecePanel extends JPanel {
        private PieceModel pieceModel;

        public PiecePanel(PieceModel pieceModel) {
            this.pieceModel = pieceModel;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int cellSize = 20;
            boolean[][] grid = pieceModel.getPiece().getGrid();

            // Dessin de la grille avec des cases remplies en noir si elles sont occupées
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j]) {
                        g.setColor(Color.GRAY);
                        g.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    } else {
                        g.setColor(Color.WHITE);
                        g.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    }
                }
            }
        }
    }
}
