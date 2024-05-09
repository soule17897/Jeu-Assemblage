package core;

import java.util.Scanner;

import model.*;

public class Game {

    PlateauPuzzle plateau;
    Joueur joueur;

    public Game(PlateauPuzzle plateau, Joueur joueur, StrategieCreation strategieConfigInitiale) {
        this.plateau = plateau;
        this.joueur = joueur;

        strategieConfigInitiale.creerConfigurationInitiale(plateau);
    }

    public void play() {
        System.out.println("Initial State:");
        System.out.println(plateau);

        for (int turn = 1; turn <= 20; turn++) {
            System.out.println("Espace occupé : " + plateau.getOccupationVolume());
            System.out.println("\nTurn " + turn + ":");
            joueur.jouer(plateau);
            System.out.println(plateau);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demander à l'utilisateur la largeur du plateau
        System.out.println("Entrez la largeur du plateau:");
        int width = scanner.nextInt();

        // Demander à l'utilisateur la longueur du plateau
        System.out.println("Entrez la longueur du plateau:");
        int length = scanner.nextInt();

        // Créer le plateau avec les tailles spécifiées par l'utilisateur
        PlateauPuzzle plateau = new PlateauPuzzle(width, length);

        // Demander à l'utilisateur la configuration initiale à utiliser
        System.out.println("Quelle configuration initiale voulez-vous utiliser?");
        System.out.println("1. Configuration initiale 1");
        System.out.println("2. Configuration initiale 2");
        int configInitiale = scanner.nextInt();
        if (configInitiale == 1) {
            StrategieCreation strategieConfigInitiale = new StrategieConfigInitial1();
            strategieConfigInitiale.creerConfigurationInitiale(plateau);
        } else if (configInitiale == 2) {
            StrategieCreation gridFillStrategy = new StrategieConfigInitial2();
            gridFillStrategy.creerConfigurationInitiale(plateau);
        } else {
            System.out.println("Veuillez entrer un nombre valide.");
            scanner.next();
        }

        //quel joueur voulez-vous utiliser?
        System.out.println("Quel joueur voulez-vous utiliser?");
        System.out.println("1. Joueur humain");
        System.out.println("2. Joueur aléatoire");
        System.out.println("3. Joueur avancé");
        int joueurChoix = scanner.nextInt();
        AbstractJoueur joueur = null;
        if (joueurChoix == 1) {
            System.out.println("Quel est votre nom? ");
            String nom = scanner.next();
            joueur = new JoueurHumain(nom, scanner);
        } else if (joueurChoix == 2) {
            joueur = new JoueurMachine(new RandomStrategy());
        } else if (joueurChoix == 3) {
            joueur = new JoueurMachine(new AdvancedStrategy());
        } else {
            System.out.println("Veuillez entrer un nombre valide.");
            scanner.next();
        }

        // Vérifier si joueur est toujours null avant d'entrer dans la boucle
        if (joueur != null) {
            // Afficher le nombre de pièces qui sont dans le plateau
            System.out.println("Nombre de pièces dans le plateau : " + plateau.getNbPieces());
            // Afficher le plateau initial
            System.out.println(plateau);

            // Combien de tours voulez-vous jouer?
            System.out.println("Combien de tours voulez-vous jouer?");
            int nbTours = scanner.nextInt();

            for (int i = 0; i < nbTours; i++) {
                System.out.println("\nTour " + i + ":");
                joueur.jouer(plateau);
                System.out.println(plateau);
            }
        } else {
            System.out.println("Joueur non initialisé. Programme terminé.");
        }

        // Fermer le scanner
        scanner.close();

    }
}
