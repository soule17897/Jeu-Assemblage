package model;

public class PieceTFactory implements PieceFactory {

	@Override
	public Piece createPiece(int width,int length) {
		// TODO Auto-generated method stub
		return new PieceT(width, length);
	}

}
