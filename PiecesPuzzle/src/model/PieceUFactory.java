package model;

public class PieceUFactory implements PieceFactory {

	@Override
	public Piece createPiece(int width, int length) {
		// TODO Auto-generated method stub
		return new PieceU(width, length);
	}

}
