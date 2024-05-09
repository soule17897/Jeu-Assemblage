package model;

import java.util.ArrayList;

import model.Piece.Orientation;
import observer.AbstractListenableModel;
import observer.ModelListener;

public class PlateauPuzzle extends AbstractListenableModel implements ModelListener {

    private ArrayList<Piece> pieces;
    private int width;
    private int length;

    public PlateauPuzzle(ArrayList<Piece> pieces, int width, int length) {
        this.pieces = pieces;
        this.width = width;
        this.length = length;
    }

    public PlateauPuzzle(int width, int length) {
        this(new ArrayList<>(), width, length);
    }

    /**
     * Methode permettant de recuperer la largeur du plateau
     *
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Methode permettant de recuperer la longueur du plateau
     *
     * @return length
     */
    public int getLength() {
        return length;
    }

    /**
     * Methode permettant de recuperer la liste des pieces du plateau
     *
     * @return pieces
     */
    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public int getOccupationVolume() {
        // Initialisation des variables pour déterminer les limites du plateau
        int maxWidth = 0;
        int minWidth = this.width - 1;

        int maxLength = 0; // Valeur initiale pour garantir la mise à jour
        int minLength = this.length - 1; // Valeur initiale pour garantir la mise à jour

        // Parcours des pièces placées sur le plateau
        for (Piece p : this.pieces) {
            // Obtention des positions occupées par la pièce
            ArrayList<Position> occupation = this.getOccupationPiece(p);

            // Parcours des positions occupées par la pièce
            for (Position pos : occupation) {
                int x = pos.getX();
                int y = pos.getY();

                // Mise à jour des limites du plateau en fonction des positions occupées par la
                // pièce
                maxWidth = Math.max(maxWidth, x);
                minWidth = Math.min(minWidth, x);

                maxLength = Math.max(maxLength, y);
                minLength = Math.min(minLength, y);
            }
        }

        // Calcul de la largeur et de la longueur qu'occupent les pièces
        int largeur = maxWidth - minWidth + 1;
        int longueur = maxLength - minLength + 1;

        // Calcul du volume d'occupation des pieces
        return largeur * longueur;
    }

    /**
     * Methode permettant d'ajouter une piece au plateau
     *
     * @param piece la piece a ajouter
     * @param p     position de la piece sur le place
     */
    public void addPiece(Piece piece, Position p) {
        if (!this.pieces.contains(piece)) {
            if (estPlacable(piece, p)) {
                piece.setPosition(p);
                this.pieces.add(piece);
                piece.addModelListener(this);
            } else {
                System.out.println("Collision détectée. La pièce ne peut pas être ajoutée.");
            }
        } else {
            System.out.println("Cette pièce est déjà sur le plateau.");
        }

    }

    /**
     * Methode permettant de retirer une piece du plateau
     *
     * @param piece la piece a retirer
     */
    public void removePiece(Piece piece) {
        if (this.pieces.contains(piece)) {
            this.pieces.remove(piece);
            piece.removeModelListener(this);
        } else {
            System.out.println("Cette pièce n'est pas sur le plateau.");
        }
    }

    /**
     * Obtient la liste des positions occupées par une pièce sur le plateau.
     *
     * @param piece La pièce pour laquelle on souhaite obtenir les positions
     *              occupées.
     * @return Une liste de Position représentant les positions occupées par
     *         la pièce.
     */
    public ArrayList<Position> getOccupationPiece(Piece piece) {
        // Liste pour stocker les positions occupées par la pièce
        ArrayList<Position> occupation = new ArrayList<>();

        // Obtenir la position du centre de la pièce sur le plateau
        Position posPiecePlateau = piece.getPosition();
        if (posPiecePlateau != null) {
            // Obtenir la position du centre de la pièce par rapport à sa propre grille
            Position posPieceCentre = piece.getCentrePiece();

            // Calculer les coordonnées du haut gauche de la piece dans
            // le plateau
            int x = posPiecePlateau.getX() - posPieceCentre.getX();
            int y = posPiecePlateau.getY() - posPieceCentre.getY();

            // Parcourir la grille de la pièce
            for (int i = 0; i < piece.getLength(); i++) {
                for (int j = 0; j < piece.getWidth(); j++) {
                    // Vérifier si la case de la grille est occupée (true)
                    if (piece.isOccupied(j, i)) {
                        // Ajouter la position relative à la position du centre de la pièce à la liste
                        // d'occupation
                        occupation.add(new Position(x + j, y + i));
                    }
                }
            }
        }

        // Retourner la liste des positions occupées par la pièce
        return occupation;
    }

    /**
     * Vérifie si une pièce peut être placée à une certaine position sur le plateau.
     *
     * @param piece La pièce à placer.
     * @param pos   La position à laquelle la pièce est envisagée d'être placée.
     * @return true si la pièce peut être placée à la position spécifiée, false
     *         sinon.
     */
    public boolean estPlacable(Piece piece, Position pos) {
        // Initialiser la chaîne de responsabilité
        MaillonPlacement collisionValide = new CollisionMaillon();
        MaillonPlacement cadreValide = new CadreMaillon();

        cadreValide.setNextMaillon(collisionValide);

        Piece pieceCopy = piece.copy();
        PlateauPuzzle plateauCopy = this.copy();
        int index = this.pieces.indexOf(piece);
        if(index != -1){
            pieceCopy = plateauCopy.getPieces().get(index);
            plateauCopy.removePiece(plateauCopy.getPieces().get(index));
        }
        // Vérifier la validité avec la chaîne de responsabilité
        return cadreValide.estValide(plateauCopy, pieceCopy, pos);
    }

    /**
     * Obtient une liste des positions communes entre deux pièces.
     *
     * @param p1 La première pièce.
     * @param p2 La deuxième pièce.
     * @return Une liste d'objets Position représentant les positions communes entre
     *         les deux pièces.
     */
    public ArrayList<Position> intersection(Piece p1, Piece p2) {
        // Liste pour stocker les positions communes entre les deux pièces
        ArrayList<Position> intersection = new ArrayList<Position>();
        // Liste pour stocker les positions du cadre de la première pièce
        ArrayList<Position> cadreP1 = new ArrayList<Position>();

        // Obtient la positions (0,0) du cadre de la première pièce sur le plateau
        Position debCadreP1 = debutCadre(p1);

        // Obtient la positions (0,0) du cadre de la deuxième pièce sur le plateau
        Position debCadreP2 = debutCadre(p2);

        // Remplit la liste cadreP1 avec les positions du cadre de la première pièce
        for (int i = 0; i < p1.getWidth(); i++) {
            for (int j = 0; j < p1.getLength(); j++) {
                cadreP1.add(new Position(debCadreP1.getX() + i, debCadreP1.getY() + j));
            }
        }

        // Parcours de la grille de la deuxième pièce
        for (int i = 0; i < p2.getWidth(); i++) {
            for (int j = 0; j < p2.getLength(); j++) {
                Position p = new Position(debCadreP2.getX() + i, debCadreP2.getY() + j);
                // Vérifie si la position est présente dans le cadre de la première pièce
                if (cadreP1.contains(p)) {
                    // Ajoute la position à la liste d'intersection
                    intersection.add(p);
                }
            }
        }
        // Retourne la liste des positions communes entre les deux pièces
        return intersection;
    }

    /**
     * Obtient la position du coin supérieur gauche du cadre d'une pièce sur le
     * plateau.
     *
     * @param p La pièce pour laquelle on souhaite obtenir la position du coin
     *          supérieur gauche du cadre.
     * @return Un objet Position représentant la position du coin supérieur gauche
     *         du cadre de la pièce.
     */
    public Position debutCadre(Piece p) {
        // Obtient la position du centre de la pièce sur le plateau
        Position pos = p.getPosition();

        // Calcule la position du coin supérieur gauche du cadre par rapport au centre
        // de la pièce
        int row = pos.getY() - p.getCentrePiece().getY();
        int col = pos.getX() - p.getCentrePiece().getX();

        // Retourne la position du coin supérieur gauche du cadre
        return new Position(col, row);
    }

    /**
     * Methode permettant de deplacer une piece sur le plateau par rapport a son centre
     *
     * @param piece la pièce à déplacer
     * @param pos   la nouvelle position de la pièce
     */
    public void deplacerPiece(Piece piece, Position pos) {
        if (estPlacable(piece, pos)) {
            piece.setPosition(pos);
        } else {
            System.out.println("Collision détectée. La pièce ne peut pas être déplacée.");
        }
        fireChange();
    }

    /**
     * Methode permettant de faire pivoter une piece sur le plateau par rapport a son centre
     *
     * @param piece       la pièce à faire pivoter
     * @param orientation la nouvelle orientation de la pièce
     */
    public void rotatePiece(Piece piece, Orientation orientation) {
        // Sauvegarde de l'orientation initiale de la pièce
        Orientation orientationInitPiece = piece.getOrientation();

        // Vérification si la pièce est présente sur le plateau
        if (!this.pieces.contains(piece)) {
            throw new IllegalArgumentException("Cette pièce n'est pas sur le plateau !");
        }

        // Rotation de la pièce selon la nouvelle orientation
        piece.rotate(orientation);

        // Vérification si la pièce, après rotation, est placée de manière valide sur le
        // plateau
        if (!estPlacable(piece, piece.getPosition())) {
            // Si la pièce n'est pas placée de manière valide, annulation de la rotation
            piece.rotate(orientationInitPiece);
            System.out.println("Collision détectée. L'orientation donnée n'est pas possible.");
        }
    }


    /**
     * Methode permettant de faire une copie du plateau
     *
     * @return PlateauPuzzle
     */
    public PlateauPuzzle copy() {
        // Création d'une nouvelle liste de pièces
        ArrayList<Piece> pieces = new ArrayList<>();

        // Parcours des pièces du plateau
        for (Piece p : this.pieces) {
            // Ajout d'une copie de la pièce à la nouvelle liste de pièces
            pieces.add(p.copy());
        }

        // Création d'une copie du plateau
        return new PlateauPuzzle(pieces, this.width, this.length);
    }

    /**
     * Methode permettant de savoir le nombre de pieces sur le plateau
     *
     * @return int
     */
    public int getNbPieces() {
        return this.pieces.size();
    }

    @Override
    public String toString() {
        // Création d'une grille vide
        boolean[][] grid = new boolean[this.length][this.width];

        // Parcours des pièces placées sur le plateau
        for (Piece p : this.pieces) {
            // Obtention des positions occupées par la pièce
            ArrayList<Position> occupation = this.getOccupationPiece(p);

            // Remplissage de la grille avec les positions occupées par la pièce
            for (Position pos : occupation) {
                if (pos.getX() >= 0 && pos.getX() < this.width && pos.getY() >= 0 && pos.getY() < this.length) {
                    grid[pos.getY()][pos.getX()] = true;
                } else {
                    System.out.println("la pièce \n" + p + " est hors du plateau.");
                }
            }
        }

        // Création d'une chaîne de caractères pour afficher la grille
        String grilleStirng = "   ";

        for (int i = 0; i < this.width; i++) {
            if (i > 9) {
                grilleStirng += i + " ";
            } else {
                grilleStirng += i + "  ";
            }
        }
        grilleStirng += "\n";
        // Parcours de la grille
        for (int i = 0; i < this.length; i++) {
            String ligne = "";
            if (i > 9) {
                ligne += i + " ";
            } else {
                ligne += i + "  ";
            }
            for (int j = 0; j < this.width; j++) {
                // Ajout d'un caractère à la chaîne de caractères en fonction de la valeur de
                // la case de la grille
                ligne += grid[i][j] ? "#  " : "-  ";
            }
            // Ajout d'un retour à la ligne à la chaîne de caractères
            ligne += "\n";
            grilleStirng += ligne;
        }

        // Retourne la chaîne de caractères représentant la grille
        return grilleStirng;
    }

    public Piece[][] getGrillePiece() {
        Piece[][] grilllePieces = new Piece[this.length][this.width];
        for (Piece p : this.pieces) {
            ArrayList<Position> occupation = this.getOccupationPiece(p);

            for (Position pos : occupation) {
                grilllePieces[pos.getY()][pos.getX()] = p;
            }
        }
        return grilllePieces;
    }

    @Override
    public void somethingHasChanged(Object source) {
        fireChange();
    }

}
