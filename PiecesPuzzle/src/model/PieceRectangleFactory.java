package model;

public class PieceRectangleFactory implements PieceFactory {

	@Override
	public Piece createPiece(int width, int length) {
		// TODO Auto-generated method stub
		return new PieceRectangle(width, length);
	}

}
