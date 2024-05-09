package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import model.Piece;
import model.PlateauPuzzle;
import model.Position;

/**
 * Interface graphique pour le jeu Tetris.
 * Cette classe étend JFrame et représente la fenêtre principale du jeu.
 * Elle gère l'affichage du plateau de puzzle et les interactions avec l'utilisateur.
 * Elle utilise des actions pour les mouvements et rotations des pièces.
 * @author thiam221
 */
public class JeuGUI extends JFrame {

	private VuePlateauPuzzle vue;
	private PlateauPuzzle plateau;
	private JPanel vueScore;
	private JButton endButton;
	private Action moveUp;
	private Action moveDown;
	private Action moveRight;
	private Action moveLeft;
	private Action rotate;
	private Action choixPiece;

	public JeuGUI(PlateauPuzzle plateau) {
		this.setTitle("JEU TETRIS ");
		this.setSize(900, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.plateau = plateau;
		this.vue = new VuePlateauPuzzle(this.plateau);
		this.moveUp = new UpAction();
		this.moveDown = new DownAction();
		this.moveLeft = new LeftAction();
		this.moveRight = new RightAction();
		this.rotate = new RotateAction();
		
		this.vue.getInputMap().put(KeyStroke.getKeyStroke("R"), "Rotate");
		this.vue.getActionMap().put("Rotate", rotate);

		this.vue.getInputMap().put(KeyStroke.getKeyStroke("UP"), "moveUp");
		this.vue.getActionMap().put("moveUp", moveUp);

		this.vue.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
		this.vue.getActionMap().put("moveDown", moveDown);

		this.vue.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
		this.vue.getActionMap().put("moveRight", moveRight);

		this.vue.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
		this.vue.getActionMap().put("moveLeft", moveLeft);

		this.vue.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "choixPiece");
		this.vue.getActionMap().put("choixPiece", choixPiece);
		this.endButton = new JButton("Terminer");
		this.vueScore = new JPanel();
		this.vueScore.setPreferredSize(new Dimension(200, 900));
		
		this.vueScore.setBackground(new Color(102, 102, 255));
		this.add(endButton, BorderLayout.SOUTH);
		
		this.endButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Votre Score : "+vue.getScore() , "Game Over", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});

		this.add(vue, BorderLayout.CENTER);
	}
	
	
	/**
	 * Action pour effectuer une rotation sur la pièce actuellement sélectionnée dans la vue.
	 */
	public class RotateAction extends AbstractAction {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // Obtient la pièce actuelle depuis la vue
	        Piece p = vue.getCurrentPiece();
	        // Effectue la rotation de la pièce selon la prochaine orientation
	        plateau.rotatePiece(p, p.getOrientationNext());
	        // Affiche les informations de la pièce dans la console (peut être utile pour le débogage)
	        System.out.println(p.toString());
	    }
	}

	/**
	 * Action pour déplacer la pièce actuellement sélectionnée vers le haut dans la vue.
	 */
	public class UpAction extends AbstractAction {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // Obtient la pièce actuelle depuis la vue
	        Piece p = vue.getCurrentPiece();
	        // Déplace la pièce vers le haut en mettant à jour la position
	        plateau.deplacerPiece(p, new Position(p.getPosition().getX(), p.getPosition().getY() - 1));
	    }
	}

	/**
	 * Action pour déplacer la pièce actuellement sélectionnée vers le bas dans la vue.
	 */
	public class DownAction extends AbstractAction {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // Obtient la pièce actuelle depuis la vue
	        Piece p = vue.getCurrentPiece();
	        // Déplace la pièce vers le bas en mettant à jour la position
	        plateau.deplacerPiece(p, new Position(p.getPosition().getX(), p.getPosition().getY() + 1));
	    }
	}

	/**
	 * Action pour déplacer la pièce actuellement sélectionnée vers la droite dans la vue.
	 */
	public class RightAction extends AbstractAction {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // Obtient la pièce actuelle depuis la vue
	        Piece p = vue.getCurrentPiece();
	        // Déplace la pièce vers la droite en mettant à jour la position
	        plateau.deplacerPiece(p, new Position(p.getPosition().getX() + 1, p.getPosition().getY()));
	    }
	}

	/**
	 * Action pour déplacer la pièce actuellement sélectionnée vers la gauche dans la vue.
	 */
	public class LeftAction extends AbstractAction {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        // Obtient la pièce actuelle depuis la vue
	        Piece p = vue.getCurrentPiece();
	        // Déplace la pièce vers la gauche en mettant à jour la position
	        plateau.deplacerPiece(p, new Position(p.getPosition().getX() - 1, p.getPosition().getY()));
	    }
	}


}
