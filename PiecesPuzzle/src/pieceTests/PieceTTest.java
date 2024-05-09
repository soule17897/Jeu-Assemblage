package pieceTests;

import static org.junit.Assert.*;
import org.junit.Test;

import model.PieceT;

public class PieceTTest {

	@Test
	public void test() {
		// Création d'une pièce L valide avec des dimensions suffisantes
		PieceT pieceT = new PieceT(3, 3);
        // Vérification que la pièce est correctement créée
        assertTrue("La position (0,0 true",pieceT.isOccupied(0, 0));
        assertTrue("La position (0,1 true",pieceT.isOccupied(1, 0));
        assertTrue("La position (0,2) true",pieceT.isOccupied(2,0));
        assertTrue("La position (1,1) true",pieceT.isOccupied(1,1));
        assertTrue("La position (2,1) true",pieceT.isOccupied(1,2)); 
        try {
            new PieceT(2, 2);
            // Si aucune exception n'est levée, le test échouera
            // car une exception IllegalArgumentException est attendue
            throw new AssertionError("IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Si une IllegalArgumentException est attrapée, le test passera
            // car une exception est correctement levée lors de la création de la pièce invalide
        }

	}

}
