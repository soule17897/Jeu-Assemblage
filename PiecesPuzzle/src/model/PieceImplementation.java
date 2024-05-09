package model;

import observer.AbstractListenableModel;

/**
 * Classe qui représente les pièces.
 *
 * @author <22108455> Ahmed Youra, <22006338> ALPHANOR Robert
 */

public abstract class PieceImplementation extends AbstractListenableModel implements Piece {
    /**
     * Orientation de la pièce.
     */
    private Orientation orientation;

    /**
     * La largeur de la pièce.
     */
    protected int width;

    /**
     * La longueur de la pièce.
     */
    protected int length;

    /**
     * La position de la piece sur le plateau.
     */
    private Position positionCentre;

    /**
     * La grille de la pièce.
     */

    protected boolean[][] grid;

    /**
     * Constructeur de la classe.
     *
     * @param width  la largeur de la pièce.
     * @param length la longueur de la pièce.
     */

    public PieceImplementation(int width, int length) {

        this.width = width;

        this.length = length;

        this.grid = new boolean[length][width];

        this.positionCentre = null;

        this.orientation = Orientation.NORTH;

    }

    /**
     * Obtient l'orientation suivante dans l'ordre prédéfini des orientations.
     *
     * @return L'orientation suivante.
     */
    @Override
    public Orientation getOrientationNext() {
        switch (this.orientation) {

            case NORTH:
                return Orientation.EAST;
            case EAST:

                return Orientation.SOUTH;

            case SOUTH:
                return Orientation.WEST;

            default:
                return Orientation.NORTH;
        }
    }

    /**
     * Retourne la position centrale de la pièce.
     *
     * @return la position centrale de la pièce.
     */

    @Override

    public Position getCentrePiece() {

        return new Position(width / 2, length / 2);

    }

    /**
     * Permet de modifier la position de la pièce sur le plateau.
     *
     * @param position la nouvelle position de la pièce.
     */
    @Override
    public void setPosition(Position position) {

        this.positionCentre = position;
        fireChange();
    }

    /**
     * Method that returns the center position of the piece
     */

    @Override
    public Position getPosition() {

        return this.positionCentre;

    }

    /**
     * Retourne la largeur de la pièce.
     *
     * @return la largeur de la pièce.
     */
    @Override
    public int getWidth() {

        return this.width;

    }

    /**
     * Retourne la longueur de la pièce.
     *
     * @return la longueur de la pièce.
     */
    @Override
    public int getLength() {

        return this.length;

    }

    /**
     * Vérifie si les coordonnées données sont occupées par la pièce.
     *
     * @param x représente la position de la colonne de la grille.
     * @param y représente la position de la ligne de la grille.
     *
     * @return true si la pièce occupe la position spécifiée, sinon false.
     */
    @Override
    public boolean isOccupied(int y, int x) {

        int pieceH = this.grid.length;

        int pieceW = this.grid[0].length;

        switch (this.orientation) {

            case NORTH:

                return this.grid[x][y];

            case EAST:

                int valeurAbsolue = Math.abs(y - (pieceH - 1));

                return this.grid[valeurAbsolue][x];

            case SOUTH:

                int valeurAbsolueX = Math.abs(x - (pieceH - 1));

                int valeurAbsolueY = Math.abs(y - (pieceW - 1));

                return this.grid[valeurAbsolueX][valeurAbsolueY];

            default:

                int valeurAbsoluex = Math.abs(x - (pieceW - 1));

                return this.grid[y][valeurAbsoluex];

        }

    }

    /**
     * Retourne la grille de la pièce.
     *
     * @return la grille de la pièce.
     */
    public boolean[][] getGrid() {
        return this.grid;

    }

    /**
     * Récupère l'orientation actuelle de la pièce.
     *
     * @return L'orientation de la pièce, représentée par une valeur de
     *         l'énumération {@link Orientation}.
     */
    @Override
    public Orientation getOrientation() {
        return this.orientation;

    }

    /**
     * Fait tourner la pièce dans la direction spécifiée.
     *
     * @param orien l'orientation vers laquelle tourner la pièce.
     */
    public void rotate(Orientation orien) {

        int copyHeight = this.length;

        int copyWidth = this.width;

        if (this.orientation == Orientation.NORTH || this.orientation == Orientation.SOUTH) {

            if (orien == Orientation.NORTH || orien == Orientation.SOUTH) {

                this.orientation = orien;

            } else {

                this.orientation = orien;

                this.width = copyHeight;

                this.length = copyWidth;

            }

        } else {

            if (orien == Orientation.EAST || orien == Orientation.WEST) {

                this.orientation = orien;

            } else {

                this.orientation = orien;

                this.width = copyHeight;

                this.length = copyWidth;

            }

        }

        fireChange();

    }

    /**
     * Fait une copie de la pièce.
     *
     * @return
     */
    @Override
    public PieceImplementation copy() {
        PieceImplementation copy = null;
        switch (this.getClass().getSimpleName()) {
            case "PieceT":
                copy = new PieceT(this.width, this.length);
                break;
            case "PieceU":
                copy = new PieceU(this.width, this.length);
                break;
            case "PieceRectangle":
                copy = new PieceRectangle(this.width, this.length);
                break;
            case "PieceL":
                copy = new PieceL(this.width, this.length);
                break;
            default:
                break;
        }
        copy.orientation = this.orientation;
        copy.positionCentre = this.positionCentre;
        copy.grid = this.grid;
        return copy;
    }

    @Override
    public String toString() {
        Position pos = this.getPosition();
        if (pos != null) {
            System.out.println("Position du centre sur le plateau : (" + pos.getX() + "," + pos.getY() + ")");
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.getLength(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                str.append(this.isOccupied(j, i) ? "#  " : "   ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + width;
        result = 31 * result + length;
        result = 31 * result + (positionCentre != null ? positionCentre.hashCode() : 0);
        result = 31 * result + orientation.hashCode();

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

}
