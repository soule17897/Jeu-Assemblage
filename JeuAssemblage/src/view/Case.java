package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Piece;

/**
 * Représente une case dans la grille de l'interface graphique.
 * Elle possède les attributs x et y qui représentent les coordonnées d'une position.
 * @author thiam221
 */
public class Case extends JButton {

    private int x;
    private int y;
    private Piece p;

    /**
     * Constructeur de la classe Case.
     *
     * @param x La coordonnée x de la case.
     * @param y La coordonnée y de la case.
     */
    public Case(int x, int y) {
        this.x = x;
        this.y = y;
        // Initialement, redessine la case et définit sa couleur de fond en blanc.
        this.repaint();
        this.setBackground(Color.WHITE);
        // Rend la case non focalisable.
        this.setFocusable(false);
    }

    /**
     * Méthode getter pour récupérer la pièce associée à la case.
     *
     * @return L'objet Piece associé à la case.
     */
    public Piece getPiece() {
        return this.p;
    }

    /**
     * Méthode setter pour définir un objet Piece pour la case.
     *
     * @param p L'objet Piece à définir pour la case.
     */
    public void setPiece(Piece p) {
        this.p = p;
    }

}
