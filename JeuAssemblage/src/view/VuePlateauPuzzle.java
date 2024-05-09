package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutFocusTraversalPolicy;

import model.Piece;
import model.PlateauPuzzle;
import model.Position;
import observer.ModelListener;

public class VuePlateauPuzzle extends JPanel implements ModelListener {

	private  int largeur;
	private  int hauteur;
	private JPanel grille;
	private PlateauPuzzle plateauPuzzle;
	private Case[][] cases;
	private Piece currentPiece;

	public VuePlateauPuzzle(PlateauPuzzle plateau) {
		this.plateauPuzzle = plateau;
		this.plateauPuzzle.addModelListener(this);
		this.setLayout(new BorderLayout());
		this.grille = new JPanel();
		this.largeur = plateauPuzzle.getWidth();
		this.hauteur = plateauPuzzle.getLength();
		this.cases = new Case[hauteur][largeur];
		this.grille.setLayout(new GridLayout(largeur,hauteur));
        this.currentPiece = plateau.getPieces().get(0);
		for (int i = 0; i < largeur; i++) {
			for (int j = 0; j < hauteur; j++) {
				this.cases[i][j] = new Case(i, j);
				this.grille.add(this.cases[i][j]);
				this.setFocusable(true);
				this.cases[i][j].addActionListener(new ActionBouton(this.cases[i][j]));
			}
		}
		this.add(this.grille, BorderLayout.CENTER);
		paintVue();
	}

	public void paintVue() {
		this.repaint();
		for (Piece p : plateauPuzzle.getPieces()) {
			for (Position pos : plateauPuzzle.getOccupationPiece(p)) {
				System.out.println(pos);
				this.cases[pos.getY()][pos.getX()].setPiece(p);
				this.cases[pos.getY()][pos.getX()].repaint();
				this.cases[pos.getY()][pos.getX()].setBackground(Color.BLUE);
			}
		}
	}
  
	@Override
	public void somethingHasChanged(Object source) {
		for (int i = 0; i < largeur; i++) {
			for (int j = 0; j < hauteur; j++) {
				this.cases[i][j].setPiece(null);
				this.cases[i][j].setBackground(Color.WHITE);
			}
		}
		paintVue();
	}
    
	
	public Piece getCurrentPiece() {
		return this.currentPiece;
	}
	
	class ActionBouton implements ActionListener {
        
		Case c;

		public ActionBouton(Case c ) {
		  this.c =c;	
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("vous avez cliquez sur un bouton !!! + avec Piece  = "+c.getPiece());
			if(c.getPiece() != null )
				currentPiece = c.getPiece();
		}
	}
	
	/*
	 *  Une methode permettant de calculer le score  du joueur 
	 *  en faisant multipliant le nombre de pièce par la difference entre l'aire  globale et l'aire 
	 *  du plus petit rectangle
	 */
	public int  getScore() {
		return ((plateauPuzzle.getLength() * plateauPuzzle.getLength()) - plateauPuzzle.getOccupationVolume())*plateauPuzzle.getNbPieces();
	}
}
