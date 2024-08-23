package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * This class represents the textual view of the Triangle Solitaire game.
 * It implements the MarbleSolitaireView interface and provides methods to render the board
 * and display messages.
 */
public class TriangleSolitaireTextView implements MarbleSolitaireView {
  private final MarbleSolitaireModelState modelState;
  private final Appendable ap;

  /**
   * Constructs a TriangleSolitaireTextView with the given model state and a default appendable (System.out).
   *
   * @param modelState the state of the model to be used for rendering the view.
   * @throws IllegalArgumentException if the modelState is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState modelState) {
    this(modelState, System.out);
  }

  /**
   * Constructs a TriangleSolitaireTextView with the given model state and appendable.
   *
   * @param modelState the state of the model to be used for rendering the view.
   * @param ap         the appendable to be used for output.
   * @throws IllegalArgumentException if the modelState or appendable is null.
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable ap) {
    if (modelState == null) {
      throw new IllegalArgumentException("modelState cannot be null");
    }
    if (ap == null) {
      throw new IllegalArgumentException("appendable cannot be null");
    }
    this.modelState = modelState;
    this.ap = ap;
  }

  /**
   * Returns the string representation of the board state.
   * Each row of the board is represented by a line of text with leading spaces to align the triangle shape.
   *
   * @return the string representation of the board state.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int boardSize = modelState.getBoardSize();
    for (int row = 0; row < boardSize; row++) {
      // Add leading spaces for the current row
      for (int space = 0; space < boardSize - row - 1; space++) {
        sb.append(" ");
      }
      // Append slot states for the current row
      for (int col = 0; col <= row; col++) {
        if (modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty) {
          sb.append("_");
        } else if (modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
          sb.append("0");
        } else {
          sb.append(" ");
        }
        if (col < row) {
          sb.append(" ");
        }
      }
      sb.append('\n');
    }
    return sb.toString();
  }

  /**
   * Renders the board by appending its string representation to the appendable.
   *
   * @throws IOException if the appendable cannot be written to.
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      this.ap.append(this.toString());
    } catch (Exception e) {
      throw new IOException(e);
    }
  }

  /**
   * Renders a given message by appending it to the appendable.
   *
   * @param message the message to be transmitted.
   * @throws IOException if the appendable cannot be written to.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.ap.append(message);
    } catch (Exception e) {
      throw new IOException(e);
    }
  }
}
