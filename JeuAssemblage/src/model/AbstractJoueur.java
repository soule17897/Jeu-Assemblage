package model;

public abstract class AbstractJoueur {
	
	protected String nom;
    
    public AbstractJoueur(String nom) {
        this.nom = nom;
    }
    
    
    /**
     * Obtient le nom du joueur humain.
     *
     * @return Le nom du joueur.
     */
    public String getNom() {
        return nom;
    }
    
    
    public abstract void jouer(PlateauPuzzle plateau);

    

}
