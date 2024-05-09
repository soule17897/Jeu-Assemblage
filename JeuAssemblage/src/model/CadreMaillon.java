package model;

public class CadreMaillon extends MaillonPlacement {

    @Override
    public boolean estValide(PlateauPuzzle plateau, Piece piece, Position pos) {
        // Calcul des coordonnées relatives de la pièce par rapport à sa position
        // centrale.
        int x = pos.getX() - piece.getCentrePiece().getX();
        int y = pos.getY() - piece.getCentrePiece().getY();

        // Place temporairement la pièce à la position spécifiée pour vérifier les
        // collisions.
        Position posPieceInit = piece.getPosition();

        piece.setPosition(pos);

        // Vérifie si la pièce dépasse les limites du plateau.
        if (x < 0 || y < 0 || (x + (piece.getWidth() - 1)) >= plateau.getWidth()
                || (y + (piece.getLength() - 1)) >= plateau.getLength()) {
            // Si la pièce dépasse les limites, annule le placement et retourne false.
            piece.setPosition(posPieceInit);
            return false;
        }
        return next(plateau, piece, pos);
    }

}
