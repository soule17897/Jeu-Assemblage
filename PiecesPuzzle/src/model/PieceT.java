package model;

public class PieceT extends PieceImplementation {

    /**
     * constructor of the class
     */
    public PieceT(int width, int length) {
        super(width, length);
        createPiece();
    }

    private void createPiece() {
        // int centreWidth = this.getWidth() / 2;
        // int centreLength = this.getLength() / 2;

        if (getWidth() % 2 != 0 && getLength() % 2 != 0) {
            if (getWidth() > 2 && getLength() > 2) {
                for (int i = 0; i < width; i++) {
                    super.grid[0][i] = true;
                }
                int milieu = (int) width / 2;

                for (int j = 0; j < length; j++) {
                    super.grid[j][milieu] = true;
                }
            } else {
                throw new IllegalArgumentException(
                        "La largeur et la longueur de la pièce doivent être supérieures à 2.");
            }
        } else {
            throw new IllegalArgumentException("La longueur et la largeur doivent être des nombres impairs.");
        }

    }

}
