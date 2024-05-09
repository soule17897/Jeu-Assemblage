package model;

public class Joueur {
    private String nom;
    private Strategie strategie;


    public Joueur(String nom, Strategie strategie) {
        this.nom = nom;
        this.strategie = strategie;
    }

    public void jouer(PlateauPuzzle plateau) {
        strategie.jouer(plateau);
    }

    public String getNom() {
        return nom;
    }

}
