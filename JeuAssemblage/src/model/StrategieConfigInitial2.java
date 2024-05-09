package model;

import java.util.Random;

/**
 * Cette classe est une stratégie pour la création de la configuration initiale d'une partie.
 *Elle permet d'avoir une configuration initiale selon une densité de pièces par cellule donnée.
 * La densité contrôle à quel point le plateau sera rempli de pièces.
 * Une densité plus élevée signifie plus de pièces, tandis qu'une densité plus basse signifie moins de pièces.
 * Cela permet de réguler le niveau de complexité ou de difficulté de la configuration initiale du puzzle.
 */
public class StrategieConfigInitial2 implements StrategieCreation {

    /**
     * Méthode pour créer la configuration initiale d'une partie.
     *
     * @param plateau
     */
    @Override
    public void creerConfigurationInitiale(PlateauPuzzle plateau) {
        //plus la densité est grande, plus il y a de pièces
        double densityPerCell = 0.02;

        int celluleTotal = plateau.getWidth() * plateau.getLength();
        //le nombre total de pièces est le nombre de cellules multiplié par la densité
        int totalPieces = (int) (celluleTotal * densityPerCell);

        for (int i = 0; i < totalPieces; i++) {
            Piece piece = getRandomPiece();
            int x = (int) (Math.random() * plateau.getWidth());
            int y = (int) (Math.random() * plateau.getLength());
            Position position = new Position(x, y);
            plateau.addPiece(piece, position);
        }
    }

    private Piece getRandomPiece() {
        Random random = new Random();
        int pieceType = random.nextInt(4); // Assuming you have 4 types of pieces

        int width = random.nextInt(2) * 2 + 3; // Garantit une largeur impaire
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
