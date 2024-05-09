package model;

public class PieceL extends PieceImplementation {

    /**
     * 
     * constructor of the class
     * 
     */

    public PieceL(int width, int length) {
        super(width, length);
        createPiece();
    }

    private void createPiece() {
        // Logique spécifique pour une pièce en forme de L

        // Assurez-vous que la grille est assez grande pour contenir la pièce en forme
        // de L
        if (getWidth() % 2 != 0 && getLength() % 2 != 0) {
            if (getWidth() > 2 && getLength() > 2) {
                // Mettre toute la dernière colonne à true
                for (int i = 0; i < super.width; i++) {
                    super.grid[super.length - 1][i] = true;
                }

                // Mettre toute la première ligne à true
                for (int i = 0; i < super.length; i++) {
                    super.grid[i][0] = true;
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
