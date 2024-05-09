package TestAssemblages;

import static org.junit.Assert.*;

import org.junit.Test;

import model.PlateauPuzzle;
import model.Position;
import model.RandomStrategy;

public class RandomStrategyTest {

	   @Test
	    public void testJouer() {
	        // Création d'un plateau avec des pièces
	        PlateauPuzzle plateau = new PlateauPuzzle(10, 10); // Taille du plateau (10x10)
	        // Ajout de pièces au plateau
	        // ...

	        // Instanciation de la stratégie
	        RandomStrategy strategie = new RandomStrategy();

	        // Vérification que la méthode jouer ne génère pas d'exceptions
	        try {
	            strategie.jouer(plateau);
	            // Si aucune exception n'est levée, vous pouvez considérer le test comme réussi
	        } catch (Exception e) {
	            fail("L'exception ne devrait pas être levée : " + e.getMessage());
	        }
	    }

}
