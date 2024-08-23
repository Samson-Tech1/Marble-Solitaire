package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.io.InputStreamReader;

import cs3500.marblesolitaire.model.hw02.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

public class MarbleSolitaireProgram {
  public static void main(String[] args) throws IOException {
    /*EnglishSolitaireModel model = new EnglishSolitaireModel();
    Readable readable =  new InputStreamReader(System.in);
    MarbleSolitaireView view = new MarbleSolitaireTextView(model);
    MarbleSolitaireControllerImpl program = new MarbleSolitaireControllerImpl(model, view, readable);
    program.playGame();*/

    TriangleSolitaireModel triModel = new TriangleSolitaireModel();
    Readable readable = new InputStreamReader(System.in);
    MarbleSolitaireView view = new TriangleSolitaireTextView(triModel);

    System.out.print(view);
//    MarbleSolitaireTextView view = new MarbleSolitaireTextView(new TriangleSolitaireModel());
//    System.out.print(view.toString());
  }
}
