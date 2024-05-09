package model;

public class JoueurMachine extends AbstractJoueur{

	private Strategie strategie;
	
	public JoueurMachine(String nom, Strategie strategie ) {
		super(nom);
		this.strategie = strategie;
	}
    
	public JoueurMachine(Strategie strategie ) {
		super("Machine");
		this.strategie = strategie;
	}
	@Override
	public void jouer(PlateauPuzzle plateau) {
		strategie.jouer(plateau);	
	}

}
