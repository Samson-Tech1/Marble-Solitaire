package cs3500.marblesolitaire;

import java.io.InputStreamReader;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EuropeanSolitaireModel;
import cs3500.marblesolitaire.model.hw02.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

public final class MarbleSolitaire {


  public static void main(String[] args) {

    switch (args[0]) {
      case "english":
        if (args.length == 1) { // checking if only english was given
          EnglishSolitaireModel model = new EnglishSolitaireModel(); // parameters for the game
          Readable readable = new InputStreamReader(System.in);
          MarbleSolitaireView view = new MarbleSolitaireTextView(model);
          MarbleSolitaireControllerImpl program = new MarbleSolitaireControllerImpl(model, view, readable); // starting the game
          program.playGame();
        }
        if (args.length == 3) { //checking if it was -size N
          EnglishSolitaireModel model = new EnglishSolitaireModel(Integer.parseInt(args[2])); // parameters for the game
          Readable readable = new InputStreamReader(System.in);
          MarbleSolitaireView view = new MarbleSolitaireTextView(model);
          MarbleSolitaireControllerImpl program = new MarbleSolitaireControllerImpl(model, view, readable); // starting the game
          program.playGame();
        }
        if (args.length == 4) { // checking if it was -hole row col
          EnglishSolitaireModel model = new EnglishSolitaireModel(Integer.parseInt(args[2]), Integer.parseInt(args[3])); // parameters for the game
          Readable readable = new InputStreamReader(System.in);
          MarbleSolitaireView view = new MarbleSolitaireTextView(model);
          MarbleSolitaireControllerImpl program = new MarbleSolitaireControllerImpl(model, view, readable); // starting the game
          program.playGame();
        }
        break;
      case "european":
        if (args.length == 1) {
          EnglishSolitaireModel model = new EuropeanSolitaireModel(); // parameters for the game
          Readable readable = new InputStreamReader(System.in);
          MarbleSolitaireView view = new MarbleSolitaireTextView(model);
          MarbleSolitaireControllerImpl program = new MarbleSolitaireControllerImpl(model, view, readable); // starting the game
          program.playGame();
        }
        if (args.length == 3) {
          EnglishSolitaireModel model = new EuropeanSolitaireModel(Integer.parseInt(args[2])); // parameters for the game
          Readable readable = new InputStreamReader(System.in);
          MarbleSolitaireView view = new MarbleSolitaireTextView(model);
          MarbleSolitaireControllerImpl program = new MarbleSolitaireControllerImpl(model, view, readable); // starting the game
          program.playGame();
        }
        if (args.length == 4) {
          EnglishSolitaireModel model = new EuropeanSolitaireModel(Integer.parseInt(args[2]), Integer.parseInt(args[3])); // parameters for the game
          Readable readable = new InputStreamReader(System.in);
          MarbleSolitaireView view = new MarbleSolitaireTextView(model);
          MarbleSolitaireControllerImpl program = new MarbleSolitaireControllerImpl(model, view, readable); // starting the game
          program.playGame();
        }
        break;
      case "triangular":
        if (args.length == 1) {
          TriangleSolitaireModel model = new TriangleSolitaireModel(); // parameters for the game
          Readable readable = new InputStreamReader(System.in);
          MarbleSolitaireView view = new TriangleSolitaireTextView(model);
          MarbleSolitaireControllerImpl program = new MarbleSolitaireControllerImpl(model, view, readable); // starting the game
          program.playGame();
        }
        if (args.length == 3) {
          TriangleSolitaireModel model = new TriangleSolitaireModel(Integer.parseInt(args[2])); // parameters for the game
          Readable readable = new InputStreamReader(System.in);
          MarbleSolitaireView view = new TriangleSolitaireTextView(model);
          MarbleSolitaireControllerImpl program = new MarbleSolitaireControllerImpl(model, view, readable); // starting the game
          program.playGame();
        }
        if (args.length == 4) {
          TriangleSolitaireModel model = new TriangleSolitaireModel(Integer.parseInt(args[2]), Integer.parseInt(args[3])); // parameters for the game
          Readable readable = new InputStreamReader(System.in);
          MarbleSolitaireView view = new TriangleSolitaireTextView(model);
          MarbleSolitaireControllerImpl program = new MarbleSolitaireControllerImpl(model, view, readable); // starting the game
          program.playGame();
        }
        break;
    }
  }
}






