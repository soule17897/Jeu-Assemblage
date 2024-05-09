package view;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;

import model.PlateauPuzzle;
import model.StrategieConfigInitial1;

/**
 * Une classe exécutable permettant de faire une démo.
 * @author thiam221
 */
public class Demo {

    public static void main(String[] args) {

        // Crée un plateau de puzzle avec une taille de 40x40.
        PlateauPuzzle plateau = new PlateauPuzzle(40, 40);

        // Initialise la configuration initiale avec une stratégie spécifique.
        StrategieConfigInitial1 config = new StrategieConfigInitial1();
        config.creerConfigurationInitiale(plateau);

        // Lance l'interface graphique dans l'Event Dispatch Thread (EDT).
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Crée une instance de JeuGUI avec le plateau initialisé.
                JeuGUI jeuTetris = new JeuGUI(plateau);
                // Rend l'interface graphique visible.
                jeuTetris.setVisible(true);
            }
        });

    }

}
