package pieceTests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.PieceRectangle;
import model.PieceT;

public class PieceRectangleTest {

	@Test
	public void test() {
		
		// Création d'une pièce L valide avec des dimensions suffisantes
		PieceRectangle pieceRectangle = new PieceRectangle(23, 8);
        // Vérification que la pièce est correctement créée
		for (int i = 0; i < pieceRectangle.getLength(); i++) {
            for (int j = 0; j < pieceRectangle.getWidth(); j++) {
            	assertTrue("La position "+i+ ","+j, pieceRectangle.isOccupied(j,i));
            }
        }		      
	}

}
