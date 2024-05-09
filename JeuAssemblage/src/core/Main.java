//package core;
//
//import java.util.Scanner;
//
//import model.*;
//
//public class Main {
//    public static void main(String[] args) {
//
//        PlateauPuzzle plateau = new PlateauPuzzle(20, 20);
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Quel est votre nom? ");
//        String nom = scanner.nextLine();
//
//        StrategieCreation strategieConfigInitiale = new StrategieConfigInitial1();
//
//        Strategie humanStrategy = new StrategieJoueurHumain(nom, scanner);
//        Strategie randomStrategie = new RandomStrategy();
//
//        Joueur joueurHumain = new Joueur(nom, humanStrategy);
//        Joueur joueurRandom = new Joueur("Random", randomStrategie);
//
//        Game game = new Game(plateau, joueurRandom, strategieConfigInitiale);
//        game.play();
//
//    }
//}