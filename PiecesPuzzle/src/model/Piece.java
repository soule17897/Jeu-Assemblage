package model;

import observer.ListenableModel;

public interface Piece extends ListenableModel {

    /**
     * Enum that represents the orientation of the piece
     */
    public enum Orientation {
        NORTH, EAST, SOUTH, WEST
    }

    /**
     * Retourne la position de la piece sur un plateau.
     *
     * @return la position de la piece sur le plateau.
     */
    Position getPosition();

    /**
     * Retourne la largeur de la pièce.
     *
     * @return la largeur de la pièce.
     */
    int getWidth();

    /**
     * Retourne la longueur de la pièce.
     *
     * @return la longueur de la pièce.
     */
    int getLength();

    /**
     * Vérifie si les coordonnées données sont occupées par la pièce.
     *
     * @param x représente la position de la colonne de la grille.
     * @param y représente la position de la ligne de la grille.
     * @return true si la pièce occupe la position spécifiée, sinon false.
     */
    boolean isOccupied(int x, int y);

    /**
     * Fait tourner la pièce dans la direction spécifiée.
     *
     * @param orientation l'orientation vers laquelle tourner la pièce.
     */

    void rotate(Orientation orientation);

    /**
     * Retourne la grille de la pièce.
     *
     * @return la grille de la pièce.
     */
    boolean[][] getGrid();

    /**
     * Permet de modifier la position de la pièce sur le plateau.
     *
     * @param position la nouvelle position de la pièce.
     */
    void setPosition(Position position);

    /**
     * Retourne la position centrale de la pièce.
     *
     * @return la position centrale de la pièce.
     */
    public Position getCentrePiece();

    /**
     * Copie de la piece
     *
     * @return une copie de la piece
     */
    Piece copy();

    /**
     * Récupère l'orientation actuelle de la pièce.
     *
     * @return L'orientation de la pièce, représentée par une valeur de
     *         l'énumération {@link Orientation}.
     */
    public Orientation getOrientation();

    /**
     * Obtient l'orientation suivante dans l'ordre prédéfini des orientations.
     *
     * @return L'orientation suivante.
     */
    public Orientation getOrientationNext();

}
