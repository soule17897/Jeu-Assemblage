package pieceTests;

/***
 * PieceImplementationTest regroupe les tests sur toutes les methodes de la classe PieceImplementation
 */


import static org.junit.Assert.*;

import org.junit.Test;
import model.Piece.Orientation;
import model.PieceImplementation;
import model.PieceL;
import model.PieceRectangle;
import model.PieceT;
import model.PieceU;
import model.Position;

public class PieceImplementationTest {

	@Test
	public void testConstructeur(){
		PieceImplementation pieceL = new PieceL(5, 7);
		PieceImplementation pieceT = new PieceT(9,9);
		PieceImplementation pieceU = new PieceU(11, 5);
		PieceImplementation pieceRectangle = new PieceRectangle(12, 4);
		
		assertEquals("la largeur de la piece L doit être égal à 5",5,pieceL.getWidth());
		assertEquals("la longueur de la piece L doit être égal à 7",7,pieceL.getLength());
		assertEquals("la largeur de la piece T doit être égal à 9",9,pieceT.getWidth());
		assertEquals("la longueur de la piece T doit être égal à 9",9,pieceT.getLength());
		assertEquals("la largeur de la piece U doit être égal à 11",11,pieceU.getWidth());
		assertEquals("la longueur de la piece U doit être égal à 5",5,pieceU.getLength());
		assertEquals("la largeur de la piece Rectangle doit être égal à 12",12,pieceRectangle.getWidth());
		assertEquals("la longueur de la piece Rectangle doit être égal à 4",4,pieceRectangle.getLength());
		assertNull("la position de la piece L doit etre null au debut",pieceL.getPosition());
		assertEquals("l'orientation de la piece doit etre nord au debut",Orientation.NORTH,pieceU.getOrientation());
		
	}
	
	@Test
	public void testIsOccupied() {
		PieceImplementation pieceT = new PieceT(3,5);
		assertTrue("la position (0,0) de la piece doit etre occupé",pieceT.isOccupied(0, 0));
		assertTrue("la position (0,1) de la piece doit etre occupé",pieceT.isOccupied(1, 0));
		assertTrue("la position (0,2) de la piece doit etre occupé",pieceT.isOccupied(2, 0));
		assertFalse("la position (2,2) de la piece doit etre occupé",pieceT.isOccupied(2, 2));
	}
	
	@Test
	public void testRotate() {
		PieceImplementation pieceT = new PieceT(3,5);
		assertEquals("Oriarntation par defaut est North", Orientation.NORTH,pieceT.getOrientation());
		assertTrue("la position (0,0) de la piece doit etre initialement occupé",pieceT.isOccupied(0, 0));

		pieceT.rotate(Orientation.EAST);
		assertEquals("la nouvelle orientation de la piece doit etre EAST", Orientation.EAST,pieceT.getOrientation());
		assertFalse("la position (0,0) de la piece doit plus etre occupé",pieceT.isOccupied(0, 0));
		assertTrue("la position (0,4) de la piece doit etre occupé",pieceT.isOccupied(4, 0));

		
		pieceT.rotate(Orientation.SOUTH);
		assertEquals("la nouvelle orientation de la piece doit etre SOUTH", Orientation.SOUTH,pieceT.getOrientation());
		assertTrue("la position (4,0) de la piece doit etre occupé",pieceT.isOccupied(0, 4));
		assertFalse("la position (0,0) de la piece doit plus etre occupé",pieceT.isOccupied(0, 0));

		
		pieceT.rotate(Orientation.WEST);
		assertEquals("la nouvelle orientation de la piece doit etre WEST", Orientation.WEST,pieceT.getOrientation());
		assertFalse("la position (0,4) de la piece doit etre occupé",pieceT.isOccupied(4, 0));
		assertTrue("la position (0,0) de la piece doit etre occupé",pieceT.isOccupied(0, 0));
	}
	
	@Test
	public void testCopy() {
		PieceImplementation pieceT = new PieceT(7,3);
		PieceImplementation copyPiece = pieceT.copy();
		assertEquals("les deux pieces doivent avoir les meme largeur", pieceT.getWidth(),copyPiece.getWidth());
		assertEquals("les deux pieces doivent avoir les meme longueur", pieceT.getLength(),copyPiece.getLength());
		assertEquals("les deux pieces doivent avoir les meme orientation", pieceT.getOrientation(),copyPiece.getOrientation());
		assertEquals("les deux pieces doivent avoir les meme positions de centre", pieceT.getCentrePiece(),copyPiece.getCentrePiece());
		
		
	}

}
