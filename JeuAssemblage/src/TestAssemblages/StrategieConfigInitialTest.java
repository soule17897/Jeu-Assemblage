package TestAssemblages;

import static org.junit.Assert.*;

import org.junit.Test;

import model.PieceT;
import model.PlateauPuzzle;
import model.StrategieConfigInitial1;

public class StrategieConfigInitialTest {

   @Test
	public void testCreerConfigurationInitiale() {
	       // Création d'un plateau
        PlateauPuzzle plateau = new PlateauPuzzle(20, 20); // Taille du plateau (10x10)

        // Instanciation de la stratégie
        StrategieConfigInitial1 strategie = new StrategieConfigInitial1();
        strategie.creerConfigurationInitiale(plateau);

        // Vérification que le plateau contient des pièces après l'exécution de la méthode
        assertTrue(plateau.getPieces().size() > 0);
	}
 

}


