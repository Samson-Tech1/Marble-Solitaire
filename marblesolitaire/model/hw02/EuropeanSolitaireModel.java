package cs3500.marblesolitaire.model.hw02;

/**
 * This class represents a model for the European Solitaire game.
 * The game is played on a board shaped like a cross with an additional middle section,
 * where each slot can hold a marble or be empty.
 * This class extends the EnglishSolitaireModel class.
 */
public class EuropeanSolitaireModel extends EnglishSolitaireModel {

  /**
   * Constructs a EuropeanSolitaireModel with the default arm thickness of 3 and the initial empty slot at the center.
   */
  public EuropeanSolitaireModel() {
    super();
  }

  /**
   * Constructs a EuropeanSolitaireModel with the specified arm thickness and the initial empty slot at the center.
   *
   * @param sideLength the arm thickness of the board.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public EuropeanSolitaireModel(int sideLength) {
    super(sideLength);
  }

  /**
   * Constructs a EuropeanSolitaireModel with the default arm thickness of 3 and the initial empty slot at the specified position.
   *
   * @param row the row of the initial empty slot.
   * @param col the column of the initial empty slot.
   * @throws IllegalArgumentException if the position is out of bounds.
   */
  public EuropeanSolitaireModel(int row, int col) {
    super(row, col);
  }

  /**
   * Constructs a EuropeanSolitaireModel with the specified arm thickness and the initial empty slot at the specified position.
   *
   * @param sideLength the arm thickness of the board.
   * @param row        the row of the initial empty slot.
   * @param col        the column of the initial empty slot.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number or if the position is out of bounds.
   */
  public EuropeanSolitaireModel(int sideLength, int row, int col) {
    super(sideLength, row, col);
  }

  /**
   * Initializes the board for the European Solitaire game.
   * This method sets the slots on the board as either Marble or Invalid based on their position.
   */
  @Override
  protected void initializeBoard() {
    {
      int sideLength = this.armThickness;
      int lower = sideLength - 1;
      int upper = boardSize - sideLength;

      for (int i = 0; i < this.boardSize; i++) {
        if (i > 0 && i < sideLength) {
          lower--;
          upper++;
        }
        if (i > sideLength * 2 - 2) {
          lower++;
          upper--;
        }
        for (int j = 0; j < this.boardSize; j++) {
          if ((i < lower && j < lower)
                  || (i < lower && j > upper)
                  || (i > upper && j < lower)
                  || (i > upper && j > upper)) {
            this.setSlot(i, j, SlotState.Invalid);
          } else {
            this.setSlot(i, j, SlotState.Marble);
          }
          if (j < lower || j > upper) {
            this.setSlot(i, j, SlotState.Invalid);
          } else {
            this.setSlot(i, j, SlotState.Marble);
          }
          if (j < lower || j > upper) {
            this.setSlot(i, j, SlotState.Invalid);
          } else {
            this.setSlot(i, j, SlotState.Marble);
          }
        }
      }
    }

  }


}

