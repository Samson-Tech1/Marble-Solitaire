package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * This class implements the MarbleSolitaireController interface.
 * It handles user inputs and controls the flow of the game.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private static final int MAX_ATTEMPTS = 3; // Maximum number of attempts for valid input
  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final Readable readable;
  private boolean gameQuit = false; // Flag to indicate if the game should quit

  /**
   * Constructs a MarbleSolitaireControllerImpl with the specified model, view, and readable.
   *
   * @param model    the model of the marble solitaire game
   * @param view     the view of the marble solitaire game
   * @param readable the readable input source
   * @throws IllegalArgumentException if any of the arguments are null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view, Readable readable) {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Arguments cannot be null");
    }
    this.model = model;
    this.view = view;
    this.readable = readable;
  }

  /**
   * Starts and plays the game.
   *
   * @throws IllegalStateException if the game cannot be started
   */
  @Override
  public void playGame() throws IllegalStateException {
    Scanner scanner = new Scanner(readable);
    try {
      while (!model.isGameOver() && !gameQuit) {
        view.renderBoard();
        view.renderMessage("Score: " + model.getScore() + "\n");
        view.renderMessage("Enter the from row, then column, then to row, then column you would like to move from!\n");

        handleUserInput(scanner);
      }
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
  }

  /**
   * Handles the user input for the game.
   *
   * @param scanner the scanner to read user input
   * @throws IOException if there is an error in input/output
   */
  private void handleUserInput(Scanner scanner) throws IOException {
    Integer fromRow = null;
    Integer fromCol = null;
    Integer toRow = null;
    Integer toCol = null;

    for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
      try {
        fromRow = getValidInt(scanner, "from row");
        fromCol = getValidInt(scanner, "from column");
        toRow = getValidInt(scanner, "to row");
        toCol = getValidInt(scanner, "to column");

        model.move(fromRow - 1, fromCol - 1, toRow - 1, toCol - 1);
        return;

      } catch (IllegalArgumentException e) {
        view.renderMessage("Invalid move: " + e.getMessage() + ". Please try again.\n");
      }
    }
    view.renderMessage("Exceeded maximum attempts. Ending game.\n");
    quitGame();
  }

  /**
   * Prompts the user for a valid integer input for the specified field.
   *
   * @param scanner the scanner to read user input
   * @param field   the name of the field being input
   * @return the valid integer input
   * @throws IOException if there is an error in input/output or the user quits the game
   */
  private int getValidInt(Scanner scanner, String field) throws IOException {
    for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
      view.renderMessage("Please enter the " + field + ":\n");
      if (!scanner.hasNext()) {
        throw new IOException("Input stream closed");
      }
      String input = scanner.next();
      if (input.equalsIgnoreCase("Q")) {
        quitGame();
        throw new IOException("Game quit by user");
      }
      try {
        return Integer.parseInt(input);
      } catch (NumberFormatException e) {
        view.renderMessage("Invalid input for " + field + ". Please enter a valid integer or 'Q' to quit.\n");
      }
    }
    view.renderMessage("Exceeded maximum attempts for " + field + ". Ending game.\n");
    quitGame();
    throw new IOException("Exceeded maximum attempts");
  }

  /**
   * Handles the game quitting process.
   *
   * @throws IOException if there is an error in input/output
   */
  private void quitGame() throws IOException {
    view.renderMessage("Game Quit!\n");
    view.renderMessage("The state of the game when quit:\n");
    view.renderBoard();
    view.renderMessage("Score: " + model.getScore());
    gameQuit = true;
  }
}

