package model;

public class PieceU extends PieceImplementation {

    /**
     * constructor of the class
     */
    public PieceU(int width, int length) {
        super(width, length);
        createPiece();
    }

    private void createPiece() {

        if (getWidth() % 2 != 0 && getLength() % 2 != 0) {

            if (getWidth() > 2 && getLength() > 2) {
                for (int i = 0; i < super.width; i++) {
                    super.grid[super.length - 1][i] = true;
                }

                // Mettre toute la première ligne à true
                for (int i = 0; i < super.length; i++) {
                    super.grid[i][0] = true;
                    super.grid[i][super.width - 1] = true;

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
