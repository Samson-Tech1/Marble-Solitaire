package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class MarbleSolitaireTextView implements MarbleSolitaireView {
  MarbleSolitaireModelState modelState;
  Appendable ap;

  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState) {
    this(modelState, System.out);

  }

  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable ap) {
    if (modelState == null) {
      throw new IllegalArgumentException("modelState cannot be null");
    }
    if (ap == null) {
      throw new IllegalArgumentException("appendable cannot be null");
    }

    this.ap = ap;


    this.modelState = modelState;

  }

  /**
   * Return a string that represents the current state of the board. The
   * string should have one line per row of the game board. Each slot on the
   * game board is a single character (O, _ or space for a marble, empty and
   * invalid position respectively). Slots in a row should be separated by a
   * space. Each row has no space before the first slot and after the last slot.
   *
   * @return the game state as a string
   */

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    int breakLine = 0;
    for (int row = 0; row < modelState.getBoardSize(); row++) {
      for (int col = 0; col < modelState.getBoardSize(); col++) {
        breakLine++;
        if (modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Empty) {
          sb.append("_ ");
        }
        if (modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Marble) {
          sb.append("0 ");
        }
        if (modelState.getSlotAt(row, col) == MarbleSolitaireModelState.SlotState.Invalid) {
          sb.append("  ");
        }
        if (breakLine == modelState.getBoardSize()) {
          sb.append('\n');
          breakLine = 0;
        }
      }
    }
    return sb.toString();
  }

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
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
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
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

