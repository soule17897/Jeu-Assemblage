package TestAssemblages;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Scanner;

import model.JoueurHumain;

public class StrategieJoueurHumainTest {


    @Test
    public void testStrategieJoueurHumainConstructor() {
        // Données de test
        String nomJoueur = "John Doe";
        Scanner scanner = new Scanner(System.in);

        // Création de l'instance de la classe avec le constructeur
         JoueurHumain  humain  = new JoueurHumain(nomJoueur, scanner);

        // Vérification que l'instance est créée avec succès
        assertFalse("L'instance de StrategieJoueurHumain ne devrait pas être nulle",humain.equals(null));

        // Vérification des valeurs des attributs
        assertEquals("Le nom du joueur ne correspond pas",nomJoueur, humain.getNom());
        
    }
}
