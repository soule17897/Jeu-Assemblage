package controller;

import model.Piece;
import model.PieceModel;
import model.PieceL;
import view.PieceView;

public class PieceController {
    private PieceModel pieceModel;
    private PieceView pieceView;

    public PieceController(PieceModel pieceModel, PieceView pieceView) {
        this.pieceModel = pieceModel;
        this.pieceView = pieceView;
        initController();
    }

    private void initController() {
        pieceModel.rotatePiece(Piece.Orientation.SOUTH);
        pieceView.updateView();
    }

    public static void main(String[] args) {
        // Example: Displaying the grid of a PieceL
        Piece pieceL = new PieceL(4, 3);
        PieceModel pieceModel = new PieceModel(pieceL);
        PieceView pieceView = new PieceView(pieceModel);
        new PieceController(pieceModel, pieceView);



    }
}

