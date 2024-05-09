package TestAssemblages;
/**
 * cette classe represente les tests sur toutes les methodes utile de la classe plateauPuzzle
 */


import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import model.Piece;
import model.PieceImplementation;
import model.PieceL;
import model.PieceT;
import model.PieceU;
import model.PlateauPuzzle;
import model.Position;

public class PlateauPuzzleTest {

	@Test
	 public void testConstructorWithPieces() {
        // Création d'une liste de pièces
        ArrayList<Piece> pieces = new ArrayList<>();
        pieces.add(new PieceT(3,5));
        pieces.add(new PieceL(3,3));
        pieces.add(new PieceU(5,5));

        // Création d'un plateau de puzzle avec des pièces et des dimensions spécifiées
        int width = 5;
        int length = 7;
        PlateauPuzzle plateau = new PlateauPuzzle(pieces, width, length);

        // Vérification que les attributs du plateau sont correctement initialisés
        assertEquals("La liste de pièces doit être initialisée avec celle fournie",pieces, plateau.getPieces());
        assertEquals("La largeur doit être initialisée avec la valeur spécifiée",width, plateau.getWidth());
        assertEquals("La longueur doit être initialisée avec la valeur spécifiée",length, plateau.getLength());
    }
	
	 @Test
	    public void testConstructorWithoutPieces() {
	        // Création d'un plateau de puzzle sans pièces et avec des dimensions spécifiées
	        int width = 3;
	        int length = 4;
	        PlateauPuzzle plateau = new PlateauPuzzle(width, length);

	        // Vérification que les attributs du plateau sont correctement initialisés
	        assertNotNull("La liste de pièces ne doit pas être null",plateau.getPieces());
	        assertEquals("La liste de pièces doit être vide",0, plateau.getPieces().size());
	        assertEquals("La largeur doit être initialisée avec la valeur spécifiée",width, plateau.getWidth());
	        assertEquals("La longueur doit être initialisée avec la valeur spécifiée",length, plateau.getLength());
	    }
	 
	 @Test
	  public void testDebutCadre() {
		 Piece piece = new PieceT(3, 5);
		 piece.setPosition(new Position(3, 3));
		 
		 //dans les conditions normales la position du centre doit être (1,2)
		 
		 int width = 10;
	     int length = 10;
	     PlateauPuzzle plateau = new PlateauPuzzle(width, length);
	     Position p =plateau.debutCadre(piece);
	     assertEquals("la coordonnee x du debut doit etre 2 ",2,p.getX());
	     assertEquals("la coordonnee y du debut doit etre 1 ",1,p.getY());
		 
	 }
	 
	 @Test
	 public void testIntersection() {
		 Piece piece1 = new PieceT(5,3);
		 Piece piece2 = new PieceT(5,3);
		 //dans la grande grille  chaque piece est place en fonction de sa position et celle de son centre
		 piece1.setPosition(new Position(2,2));
		 piece2.setPosition(new Position(3,3));
		 int width = 10;
	     int length = 10;
	     PlateauPuzzle plateau = new PlateauPuzzle(width, length);
	     ArrayList<Position> listePositions = plateau.intersection(piece1, piece2);
	     assertFalse("l'intersection ne doit pas être vide",listePositions.isEmpty());
	     assertEquals("la taille de la liste0 d'intersection doit etre 8",8,listePositions.size());
	     assertTrue("la liste d'intersection contient la position (1,2)",listePositions.contains(new Position(1,2)));
	     assertTrue("la liste d'intersection contient la position (1,3)",listePositions.contains(new Position(1,3)));
	     assertTrue("la liste d'intersection contient la position (2,2)",listePositions.contains(new Position(2,2)));
	     assertTrue("la liste d'intersection contient la position (2,3)",listePositions.contains(new Position(2,3)));
	     assertTrue("la liste d'intersection contient la position (3,3)",listePositions.contains(new Position(3,3)));
	     
			 
	 }
	 
	 @Test
	 public void testEstPlacable() { 
		 int width = 10;
	     int length = 10;
	     PlateauPuzzle plateau = new PlateauPuzzle(width, length);
		 //creation d'une piece dont le debut du cadre renvoie est une position negative
		 Piece pieceNeg = new PieceT(3, 3);
		 assertFalse("une piece avec des positions negatives ne doit pas être plaçable",plateau.estPlacable(pieceNeg, new Position(0, 0)));
		 //creation de piece en collusion
		 Piece piece1 = new PieceT(3,3);
		 Piece piece2 = new PieceT(3,3);
		
		 piece1.setPosition(new Position(2,2));
		 ArrayList<Piece> listes = new ArrayList<Piece>();
		 listes.add(piece1);
		 PlateauPuzzle plateau2 = new PlateauPuzzle(listes,width, length);
		 assertFalse("les deux pieces sont en collusion dans (1,2)",plateau2.estPlacable(piece2,new Position(3,3)));
		 
		 
	 }
	 
	 @Test
	 public void testGetOccupationPiece() {
		 //creation du plateau
		 int width = 10;
	     int length = 10;
	     PlateauPuzzle plateau = new PlateauPuzzle(width, length);
	     Piece piece1 = new PieceT(3,3);
	     piece1.setPosition(new Position(2,2));
	     assertNotNull("la piece doit être dans le plateau",plateau.getOccupationPiece(piece1));
	     assertTrue("la position (1,2) doit etre occupée par la piece",plateau.getOccupationPiece(piece1).contains(new Position(2,1)));
	     assertTrue("la position (1,1) doit etre occupée par la piece",plateau.getOccupationPiece(piece1).contains(new Position(1,1)));
	     assertTrue("la position (2,2) doit etre occupée par la piece",plateau.getOccupationPiece(piece1).contains(new Position(2,2)));
	     assertTrue("la position (3,2) doit etre occupée par la piece",plateau.getOccupationPiece(piece1).contains(new Position(2,3)));
		 
	 }
	 
	 @Test
	 public void testAddPiece() {
		 int width = 10;
	     int length = 10;
	     PlateauPuzzle plateau = new PlateauPuzzle(width, length);
	     Piece piece1 = new PieceT(3,3);
	     plateau.addPiece(piece1, new Position(2,2)); //premier ajout
	     Piece piece2 = new PieceT(5,5);
	     plateau.addPiece(piece2, new Position(6, 6)); // doit marcher
	     assertEquals("la taille de la liste des pieces doit etre eglae à deux",2,plateau.getPieces().size());
	     //verifier que la piece2 occupe bien une position dans la grille
	     assertTrue("la position (4,4) doit etre occupée par la deuxieme piece",plateau.getOccupationPiece(piece2).contains(new Position(4,4)));
	     assertTrue("la position (4,6) doit etre occupée par la deuxieme piece",plateau.getOccupationPiece(piece2).contains(new Position(6,4)));
	     assertTrue("la position (5,6) doit etre occupée par la deuxieme piece",plateau.getOccupationPiece(piece2).contains(new Position(6,5)));
	     assertTrue("la position (6,6) doit etre occupée par la deuxieme piece",plateau.getOccupationPiece(piece2).contains(new Position(6,6)));
	     assertTrue("la position (7,6) doit etre occupée par la deuxieme piece",plateau.getOccupationPiece(piece2).contains(new Position(6,7)));
	     assertTrue("la position (8,6) doit etre occupée par la deuxieme piece",plateau.getOccupationPiece(piece2).contains(new Position(6,8)));
		 
	     
		 
	 }
	 
	 @Test
	 public void testGetOccupationVolume() {
		 int width = 10;
	     int length = 10;
	     PlateauPuzzle plateau = new PlateauPuzzle(width, length);
	     Piece piece1 = new PieceT(3,3);
	     plateau.addPiece(piece1, new Position(2,2)); 
	     Piece piece2 = new PieceT(5,5);
	     System.out.println("positon "+ piece2.getPosition());
	     System.out.println("position centre : "+ piece2.getCentrePiece());
	     plateau.addPiece(piece2, new Position(6, 6)); 
	     assertEquals("l'air de la surface occupe par les deux pièces doit etre égale à 64",64,plateau.getOccupationVolume());
		 
	 }
	 
	 

}
