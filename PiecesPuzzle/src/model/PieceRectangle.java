package model;

public class PieceRectangle extends PieceImplementation {

    /**
     * constructor of the class
     */
    public PieceRectangle(int width, int length) {
        super(width, length);
        createPiece();

    }

    private void createPiece() {
        for (int i = 0; i < getLength(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                super.grid[i][j] = true;
            }
        }
    }

}
