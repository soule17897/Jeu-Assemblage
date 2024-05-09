package pieceTests;

import static org.junit.Assert.*;
import org.junit.Test;
import model.PieceL;

public class PieceLTest {

	@Test
	public void testCreatePiece() {
		// Création d'une pièce T valide avec des dimensions suffisantes
        PieceL pieceL = new PieceL(3, 3);
        // Vérification que la pièce est correctement créée
        assertTrue("La position (0,0 true",pieceL.isOccupied(0, 0));
        assertTrue("La position (1,0 true",pieceL.isOccupied(0, 1));
        assertTrue("La position (2,0) true",pieceL.isOccupied(0, 2));
        assertTrue("La position (2,1) true",pieceL.isOccupied(1, 2));
        assertTrue("La position (2,2) true",pieceL.isOccupied(2, 2));
        try {
            new PieceL(2, 2);
            // Si aucune exception n'est levée, le test échouera
            // car une exception IllegalArgumentException est attendue
            throw new AssertionError("IllegalArgumentException  n'est pas lancé ");
        } catch (IllegalArgumentException e) {
            // Si une IllegalArgumentException est attrapée, le test passera
            // car une exception est correctement levée lors de la création de la pièce invalide
        }
        
     
	}
}
