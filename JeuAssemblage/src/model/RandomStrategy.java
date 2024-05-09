package model;

import model.Piece.Orientation;

import java.util.ArrayList;

public class RandomStrategy implements Strategie {

    @Override
    public void jouer(PlateauPuzzle plateau) {
        ArrayList<Piece> pieces = plateau.getPieces();
        if (!pieces.isEmpty()) {
            Piece piece = pieces.get((int) (Math.random() * pieces.size()));
            System.out.println("Piece choisie : " + piece.getPosition());
            moveOrRotatePiece(piece, plateau);
        }
    }

    private void moveOrRotatePiece(Piece piece, PlateauPuzzle plateau) {
        int maxAttempts = 100; // Set a maximum number of attempts to avoid infinite loop

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            Position pos = getRandomPosition(plateau);

            Piece.Orientation orientation = getRandomOrientation();

            if (canMove(piece, pos, plateau)) {
                plateau.deplacerPiece(piece, pos);
                return; // Move successful
            } else if (canRotate(piece, orientation, plateau)) {
                piece.rotate(orientation);
                return; // Rotation successful
            }
        }

        System.out.println("impossible de trouver un mouvement ou une rotation possible valide apres " + maxAttempts
                + " tentatives");
    }

    private Position getRandomPosition(PlateauPuzzle plateau) {
        Piece piece = plateau.getPieces().get((int) (Math.random() * plateau.getPieces().size()));
        int x = (int) (Math.random() * (plateau.getWidth() - 1 - piece.getWidth() + 2));
        int y = (int) (Math.random() * (plateau.getLength() - 1 - piece.getLength() + 2));
        Position pos = new Position(x, y);
        System.out.println("Selected position: " + pos);
        return pos;
    }

    private Piece.Orientation getRandomOrientation() {
        return Piece.Orientation.values()[(int) (Math.random() * Piece.Orientation.values().length)];
    }

    private boolean canMove(Piece piece, Position pos, PlateauPuzzle plateau) {
        // Check if the piece can be moved to the new position
        return plateau.estPlacable(piece, pos);
    }

    private boolean canRotate(Piece piece, Orientation orientation, PlateauPuzzle plateau) {
        // Check if the piece can be rotated to the new orientation
        Orientation orientationInit = piece.getOrientation();
        piece.rotate(orientation);
        if (plateau.estPlacable(piece, piece.getPosition())) {
            piece.rotate(orientationInit);
            return true;
        }

        piece.rotate(orientationInit);
        return false;
    }

}
