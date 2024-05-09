package model;

import java.util.Random;

public class StrategieConfigInitial1 implements StrategieCreation {

    @Override
    public void creerConfigurationInitiale(PlateauPuzzle plateau) {
        // Number of pieces to be added to the board
        int nbPieces = (int) (Math.random() * 8)+2;
        System.out.println("nbPieces = " + nbPieces);

        for (int i = 0; i < nbPieces; i++) {
            // on choisit une pièce aléatoire
            Piece piece = getRandomPiece();
            System.out.println("piece choisie \n" + piece.toString());

            boolean placed = tryPlacePiece(piece, plateau);

            // tant que la pièce n'est pas placée, on en choisit une autre
            while (!placed) {
                piece = getRandomPiece();
                placed = tryPlacePiece(piece, plateau);
            }
        }
    }

    /**
     * Methode permettant de placer une piece sur le plateau
     *
     * @param piece
     * @param plateau
     * @return
     */
    private boolean tryPlacePiece(Piece piece, PlateauPuzzle plateau) {
        int maxAttempts = 100; // Set a maximum number of attempts to avoid infinite loop

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            // Randomly generate a position on the plateau
            int x = (int) (Math.random() * (plateau.getWidth() - piece.getWidth() + 1));
            int y = (int) (Math.random() * (plateau.getLength() - piece.getLength() + 1));

            // Set the position for the piece
            Position pos = new Position(x, y);

            // Check if the piece can be placed at the generated position
            if (plateau.estPlacable(piece, pos)) {
                System.out.println("Piece placed at " + pos);
                // Add the piece to the plateau
                plateau.addPiece(piece, pos);
                return true; // Piece successfully placed
            }
        }

        return false;
    }

    /**
     * Methode permettant de choisir une piece aléatoire
     *
     * @return Piece
     */
    private Piece getRandomPiece() {
        Random random = new Random();
        int pieceType = random.nextInt(4); // Assuming you have 4 types of pieces

        int width = random.nextInt(2) * 2 + 3; // Garantit une largeur impaire de
        int length = random.nextInt(2) * 2 + 3; // Garantit une longueur impaire

        switch (pieceType) {
            case 0:
                return new PieceT(width, length);
            case 1:
                return new PieceU(width, length);
            case 2:
                return new PieceL(width, length);
            case 3:
                return new PieceRectangle(width, length);
            default:
                throw new IllegalArgumentException("Invalid piece type");
        }
    }

}
