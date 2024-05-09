package model;

public class PieceModel{
    private Piece piece;

    public PieceModel(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public void rotatePiece(Piece.Orientation orientation) {
        piece.rotate(orientation);
    }
}

