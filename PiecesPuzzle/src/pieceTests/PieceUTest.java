package pieceTests;
import static org.junit.Assert.*;
import org.junit.Test;
import model.PieceL;
import model.PieceU;

public class PieceUTest {

	@Test
	public void test() {
		// Création d'une pièce U valide avec des dimensions suffisantes
        PieceU pieceU = new PieceU(5, 5);
        // Vérification que la pièce est correctement créée
        assertTrue("La position (0,0) doit être true",pieceU.isOccupied(0, 0));
        assertTrue("La position (4,0) doit être true",pieceU.isOccupied(0,4));
        assertTrue("La position (2,0) true",pieceU.isOccupied(0, 2));
        assertTrue("La position (4,2) true",pieceU.isOccupied(2, 4));
        assertTrue("La position (4,4) true",pieceU.isOccupied(4, 4));
        assertFalse("La position (2,1) true",pieceU.isOccupied(1, 2));
        assertFalse("La position (0,2) true",pieceU.isOccupied(2, 0));
        try {
            new PieceU(2, 2);
            // Si aucune exception n'est levée, le test échouera
            // car une exception IllegalArgumentException est attendue
            throw new AssertionError("IllegalArgumentException was not thrown");
        } catch (IllegalArgumentException e) {
            // Si une IllegalArgumentException est attrapée, le test passera
            // car une exception est correctement levée lors de la création de la pièce invalide
        }
   
	}
	}


