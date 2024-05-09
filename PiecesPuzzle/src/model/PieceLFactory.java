package model;

public class PieceLFactory implements PieceFactory {

	@Override
	public Piece createPiece(int width, int length) {
		// TODO Auto-generated method stub
		return new PieceL(width, length);
	}

}
