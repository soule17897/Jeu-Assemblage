package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JoueurHumain  extends AbstractJoueur{

	
	private Scanner scanner;
	
	/**
     * Constructeur de la classe.
     *
     * @param nom     Le nom du joueur humain.
     * @param scanner Le scanner pour la saisie des données.
     */
   
	
	public JoueurHumain(String nom ,Scanner scanner) {
		super(nom);
		this.scanner = scanner;
	}
	
   
    /**
     * Demande à l'utilisateur de saisir un nombre entier positif.
     *
     * @param scanner Le scanner pour la saisie.
     * @param text    Le texte affiché pour la demande.
     * @return Le nombre saisi par l'utilisateur.
     */
    public static int askNumber(Scanner scanner, String text) {
        int num;

        while (true) {
            System.out.println(text);
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                if (num >= 0) {
                    break;
                } else {
                    System.out.println("Veuillez entrer un nombre supérieur ou égal à 0.");
                }
            } else {
                System.out.println("Veuillez entrer un nombre valide.");
                scanner.next();
            }
        }
        return num;
    }

    
    /**
     * Demande à l'utilisateur de choisir un nombre parmi une liste de choix
     * valides.
     *
     * @param scanner Le scanner pour la saisie.
     * @param text    Le texte affiché pour la demande.
     * @param choices La liste des choix valides.
     * @return Le choix saisi par l'utilisateur.
     */
    public static int askNumberChoice(Scanner scanner, String text, List<Integer> choices) {
        int num;
        while (true) {
            int choix = askNumber(scanner, text);
            if (choices.contains(choix)) {
                num = choix;
                break;
            } else {
                System.out.println("Veuillez saisir un nombre parmi celles proposées");
            }
        }
        return num;
    }

    /**
     * Méthode permettant au joueur humain de jouer sur un plateau de puzzle.
     *
     * @param plateau Le plateau de puzzle sur lequel le joueur joue.
     */
    @Override
    public void jouer(PlateauPuzzle plateau) {
        String text = "Joueur " + nom + " quel est votre choix ?\n" +
                "1. Déplacer une pièce\n" +
                "2. Tourner une pièce\n";

        // Scanner scanner = new Scanner(System.in);
        int choix = askNumberChoice(scanner, text, List.of(1, 2));
        switch (choix) {
            case 1:
                String textChoixPieceMove = "Quelle pièce voulez-vous déplacer ?\n";
                int i = 0;
                List<Integer> choices = new ArrayList<>();
                for (Piece piece : plateau.getPieces()) {
                    choices.add(i);
                    System.out.println(i + ". \n" + piece.toString());
                    i++;
                }
                int pieceIndex = askNumberChoice(scanner, textChoixPieceMove, choices);
                Piece piece = plateau.getPieces().get(pieceIndex);
                System.out.println("Quelle position voulez-vous lui donner ?\n");
                int x = askNumber(scanner, "x : ");
                int y = askNumber(scanner, "y : ");
                Position position = new Position(x, y);
                plateau.deplacerPiece(piece, position);
                break;
            case 2:
                String textChoixPieceTurne = "Quelle pièce voulez-vous tourner ?\n";
                int j = 0;
                List<Integer> choicePiece = new ArrayList<>();

                for (Piece piece1 : plateau.getPieces()) {
                    choicePiece.add(j);
                    System.out.println(j + ". \n" + piece1.toString());
                    j++;
                }
                int pieceIndex1 = askNumberChoice(scanner, textChoixPieceTurne, choicePiece);
                Piece piece1 = plateau.getPieces().get(pieceIndex1);
                String textOrientation = "Quelle orientation voulez-vous lui donner ?\n";
                textOrientation += "1. \n" + Piece.Orientation.NORTH;
                textOrientation += "\n2. \n" + Piece.Orientation.EAST;
                textOrientation += "\n3. \n" + Piece.Orientation.SOUTH;
                textOrientation += "\n4. \n" + Piece.Orientation.WEST;
                int orientationIndex = askNumberChoice(scanner, textOrientation, List.of(1, 2, 3, 4));
                Piece.Orientation orientation = null;
                switch (orientationIndex) {
                    case 1:
                        orientation = Piece.Orientation.NORTH;
                        break;
                    case 2:
                        orientation = Piece.Orientation.EAST;
                        break;
                    case 3:
                        orientation = Piece.Orientation.SOUTH;
                        break;
                    case 4:
                        orientation = Piece.Orientation.WEST;
                        break;
                }
                plateau.rotatePiece(piece1, orientation);
                break;
        }
    }
}
