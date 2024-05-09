package core;
import model.Piece;
import model.PieceL;
import model.PieceRectangle;
import model.PieceT;
import model.PieceU;

public class DemoPiece {

	public static void main(String[] args) {
		// Exemple avec une pièce T
		PieceT pieceT = new PieceT(5, 5 );
		System.out.println("Piece T avant rotation:");
		System.out.println(pieceT.toString());

		// Rotation de la pièce T vers l'est
		pieceT.rotate(Piece.Orientation.EAST);
		System.out.println("Piece T après rotation vers l'est:");
		System.out.println(pieceT.toString());

		// Rotation de la pièce T vers le sud
		pieceT.rotate(Piece.Orientation.SOUTH);
		System.out.println("Piece T après rotation vers le sud:");
		System.out.println(pieceT.toString());

		// Rotation de la pièce T vers l'ouest
		pieceT.rotate(Piece.Orientation.WEST);
		System.out.println("Piece T après rotation vers l'ouest:");
		System.out.println(pieceT.toString());

		// Exemple avec une pièce U
		PieceU pieceU = new PieceU(4, 4);
		System.out.println("Piece U avant rotation:");
		System.out.println(pieceU);

		// Rotation de la pièce U vers l'ouest
		pieceU.rotate(Piece.Orientation.WEST);
		System.out.println("Piece U après rotation vers l'ouest:");
		System.out.println(pieceU.toString());

		// Rotation de la pièce U vers l'est
		pieceU.rotate(Piece.Orientation.EAST);
		System.out.println("Piece U après rotation vers l'est:");
		System.out.println(pieceU.toString());

		// Rotation de la pièce U vers le sud
		pieceU.rotate(Piece.Orientation.SOUTH);
		System.out.println("Piece U après rotation vers le sud :");
		System.out.println(pieceU.toString());

		// Piece L
		PieceL pieceL = new PieceL(4, 3);
		System.out.println("Piece L avant rotation:");
		System.out.println(pieceL.toString());

		// Rotation de la pièce L vers le sud
		pieceL.rotate(Piece.Orientation.SOUTH);
		System.out.println("Piece L après rotation vers le sud:");
		System.out.println(pieceL.toString());

		// Rotation de la pièce L vers l'ouest
		pieceL.rotate(Piece.Orientation.WEST);
		System.out.println("Piece L après rotation vers l'ouest:");
		System.out.println(pieceL.toString());

		// Rotation de la pièce L vers l'est
		pieceL.rotate(Piece.Orientation.EAST);
		System.out.println("Piece L après rotation vers l'est:");
		System.out.println(pieceL.toString());

		// Piece rectangle
		Piece pieceRectangle = new PieceRectangle(4, 3);
		System.out.println("Piece rectangle avant rotation:");
		System.out.println(pieceRectangle.toString());

		// Rotation de la pièce rectangle vers le sud
		pieceRectangle.rotate(Piece.Orientation.SOUTH);
		System.out.println("Piece rectangle après rotation vers le sud:");
		System.out.println(pieceRectangle.toString());

		// Rotation de la pièce rectangle vers l'ouest
		pieceRectangle.rotate(Piece.Orientation.WEST);
		System.out.println("Piece rectangle après rotation vers l'ouest:");
		System.out.println(pieceRectangle.toString());

		// Rotation de la pièce rectangle vers l'est
		pieceRectangle.rotate(Piece.Orientation.EAST);
		System.out.println("Piece rectangle après rotation vers l'est:");
		System.out.println(pieceRectangle.toString());
	}
}
