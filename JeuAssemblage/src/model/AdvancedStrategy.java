package model;

import java.util.ArrayList;

public class AdvancedStrategy implements Strategie {

    @Override
    public void jouer(PlateauPuzzle plateau) {
        ArrayList<Piece> pieces = plateau.getPieces();
        if (!pieces.isEmpty()) {
            Piece piece = findBestMove(pieces, plateau);
            System.out.println("Piece choisie : " + piece.getPosition());
            moveOrRotatePiece(piece, plateau);
        }
    }

    /**
     * Methode permettant de trouver le meilleur mouvement
     * Ici un meilleur mouvement est un mouvement qui minimise l'aire du plateau après le mouvement
     *
     * @param pieces
     * @param plateau
     * @return la pièce qui doit être déplacée
     */
    private Piece findBestMove(ArrayList<Piece> pieces, PlateauPuzzle plateau) {
        Piece bestPiece = null;
        double bestEvaluation = Double.MAX_VALUE;

        for (Piece piece : pieces) {
            for (Position pos : getAllPossibleMoves(plateau, piece)) {
                double evaluation = evaluateMove(plateau, piece, pos);
                if (evaluation < bestEvaluation) {
                    bestEvaluation = evaluation;
                    bestPiece = piece;
                }
            }
        }

        return bestPiece;
    }

    /**
     * Methode permettant de trouver tous les mouvements possibles pour une pièce
     *
     * @param plateau
     * @param piece
     * @return la liste des positions possibles pour la pièce
     */
    private ArrayList<Position> getAllPossibleMoves(PlateauPuzzle plateau, Piece piece) {
        ArrayList<Position> allPossibleMoves = new ArrayList<>();
        for (int i = 0; i < plateau.getLength(); i++) {
            for (int j = 0; j < plateau.getWidth(); j++) {
                Position pos = new Position(i, j);
                if(plateau.estPlacable(piece, pos)) {
                    allPossibleMoves.add(pos);
                }

                //on ajoute les rotations
                for (Piece.Orientation orientation : Piece.Orientation.values()) {
                    Piece tempPiece = piece.copy();
                    tempPiece.rotate(orientation);
                    if(plateau.estPlacable(tempPiece, pos)) {
                        allPossibleMoves.add(pos);
                    }
                }
            }
        }
        return allPossibleMoves;

    }

    /**
     * Méthode permettant d'évaluer un mouvement en fonction de l'aire du plateau après le mouvement
     * l'aire c'est le nombre de cases occupées par les pièces
     * @param plateau
     * @param piece
     * @param pos
     * @return l'aire du plateau après le mouvement
     */
    private double evaluateMove(PlateauPuzzle plateau, Piece piece, Position pos) {
        //on fait une copie du plateau pour ne pas modifier le plateau original
        PlateauPuzzle tempPlateau = plateau.copy();
        tempPlateau.addPiece(piece, pos);
        ArrayList<Position> positionOccupe = new ArrayList<>();
        //on récupère toutes les pièces du plateau
        for (Piece p : tempPlateau.getPieces()) {
            positionOccupe.addAll(tempPlateau.getOccupationPiece(p));
        }

        int minWidth = tempPlateau.getWidth();
        int maxWidth = 0;
        int minLength = tempPlateau.getLength();
        int maxLength = 0;

        for(Position p : positionOccupe) {
            minWidth = Math.min(minWidth, p.getX());
            maxWidth = Math.max(maxWidth, p.getX());
            minLength = Math.min(minLength, p.getY());
            maxLength = Math.max(maxLength, p.getY());
        }

        double aire = (maxWidth - minWidth+1) * (maxLength - minLength+1);
        return aire;
    }

    private void moveOrRotatePiece(Piece piece, PlateauPuzzle plateau) {
        int maxAttempts = 100; // Set a maximum number of attempts to avoid infinite loop

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            Position pos = getBestMovePosition(plateau, piece);

            Piece.Orientation orientation = getBestMoveOrientation(plateau, piece);

            if (canMove(piece, pos, plateau)) {
                plateau.deplacerPiece(piece, pos);
                piece.setPosition(pos);
                return; // deplacement réussi
            } else if (canRotate(piece, orientation, plateau)) {
                piece.rotate(orientation);
                piece.setPosition(pos);
                return; // rotation réussie
            }
        }

        System.out.println("impossible de trouver un mouvement ou une rotation possible valide apres " + maxAttempts + " tentatives");
    }

    private boolean canRotate(Piece piece, Piece.Orientation orientation, PlateauPuzzle plateau) {
        Position piecePosition = piece.getPosition();

        // Check if the piece can be rotated to the new orientation
        Piece tempPiece = piece.copy();

        // Make sure the piece has a valid position before calling estPlacable
        if (piecePosition != null) {
            tempPiece.rotate(orientation);
            return plateau.estPlacable(tempPiece, piecePosition);
        } else {
            return false;
        }
    }

    private boolean canMove(Piece piece, Position pos, PlateauPuzzle plateau) {
        return plateau.estPlacable(piece, pos);
    }

    private Piece.Orientation getBestMoveOrientation(PlateauPuzzle plateau, Piece piece) {
        Piece.Orientation bestOrientation = null;
        double bestEvaluation = Double.MAX_VALUE;

        for (Piece.Orientation orientation : Piece.Orientation.values()) {
            Piece tempPiece = piece.copy();
            tempPiece.rotate(orientation);

            double evaluation = evaluateMove(plateau, tempPiece, piece.getPosition());
            if (evaluation < bestEvaluation) {
                bestEvaluation = evaluation;
                bestOrientation = orientation;
            }
        }

        return bestOrientation;
    }

    private Position getBestMovePosition(PlateauPuzzle plateau, Piece piece) {
        Position bestPosition = null;
        double bestEvaluation = Double.MAX_VALUE;

        for (Position pos : getAllPossibleMoves(plateau, piece)) {
            double evaluation = evaluateMove(plateau, piece, pos);
            if (evaluation < bestEvaluation) {
                bestEvaluation = evaluation;
                bestPosition = pos;
            }
        }

        return bestPosition;
    }



}
