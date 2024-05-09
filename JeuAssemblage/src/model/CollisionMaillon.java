package model;

import java.util.ArrayList;

public class CollisionMaillon extends MaillonPlacement {

    @Override
    public boolean estValide(PlateauPuzzle plateau, Piece piece, Position pos) {
        // Place temporairement la pièce à la position spécifiée pour vérifier les
        // collisions.
        Position posPieceInit = piece.getPosition();

        piece.setPosition(pos);

        // Vérifie les collisions avec les autres pièces déjà placées sur le plateau.
        for (Piece p : plateau.getPieces()) {
            // Obtient les positions d'intersection entre la pièce en cours et la pièce à
            // placer.
        	
            if (!p.equals(piece)) {
                ArrayList<Position> intersection = plateau.intersection(p, piece);
                // Vérifie chaque position d'intersection pour une éventuelle collision.
                for (Position positionIntersection : intersection) {
                    // Calcul des positions relatives dans les deux pièces en collision.
                    int posX1 = positionIntersection.getX() - p.getPosition().getX() + p.getCentrePiece().getX();
                    int posY1 = positionIntersection.getY() - p.getPosition().getY() + p.getCentrePiece().getY();

                    int posX2 = positionIntersection.getX() - pos.getX() + piece.getCentrePiece().getX();
                    int posY2 = positionIntersection.getY() - pos.getY() + piece.getCentrePiece().getY();

                    // Vérifie si les cases correspondantes dans les deux pièces sont occupées
                    // simultanément.
                    if (p.isOccupied(posX1, posY1) && piece.isOccupied(posX2, posY2)) {
                        // Si une collision est détectée, annule le placement et retourne false.
                        piece.setPosition(posPieceInit);
                        return false;
                    }
                }
            }
        }

        return next(plateau, piece, pos);
    }

}
