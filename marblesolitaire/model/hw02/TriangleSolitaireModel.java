package cs3500.marblesolitaire.model.hw02;

/**
 * This class represents a model for a Triangle Solitaire game.
 * The game is played on a triangular board where each slot can hold a marble or be empty.
 */
public class TriangleSolitaireModel implements MarbleSolitaireModel {
  protected final SlotState[][] board;
  private final int dimensions;
  protected int score;
  private final int boardSize;

  /**
   * Constructs a TriangleSolitaireModel with the default board size of 5 and the initial empty slot at (0, 0).
   */
  public TriangleSolitaireModel() {
    this.dimensions = 5;
    this.boardSize = this.getBoardSize();
    this.board = new SlotState[boardSize][boardSize];
    this.initializeBoard();
    this.board[0][0] = SlotState.Empty;
  }

  /**
   * Constructs a TriangleSolitaireModel with a specified board size and the initial empty slot at (0, 0).
   *
   * @param dimensions the size of the board.
   * @throws IllegalArgumentException if the dimensions are non-positive.
   */
  public TriangleSolitaireModel(int dimensions) {
    if (dimensions <= 0) {
      throw new IllegalArgumentException("Board size must be positive.");
    }
    this.dimensions = dimensions;
    this.board = new SlotState[dimensions][dimensions];
    initializeBoard();
    this.board[0][0] = SlotState.Empty; // Set the initial empty slot
    this.score = getScore();
    this.boardSize = this.getBoardSize();
  }

  /**
   * Constructs a TriangleSolitaireModel with the default board size of 5 and the initial empty slot at the specified position.
   *
   * @param row the row of the initial empty slot.
   * @param col the column of the initial empty slot.
   * @throws IllegalArgumentException if the position is out of bounds.
   */
  public TriangleSolitaireModel(int row, int col) {
    this.dimensions = 5;
    this.boardSize = this.getBoardSize();
    this.board = new SlotState[boardSize][boardSize];
    this.initializeBoard();
    board[row][col] = SlotState.Empty;
    if (row < 0 || row > this.boardSize - 1 || col < 0 || col > this.boardSize - 1) {
      throw new IllegalArgumentException("Position out of bounds (" + row + ", " + col + ")");
    }
  }

  /**
   * Constructs a TriangleSolitaireModel with a specified board size and the initial empty slot at the specified position.
   *
   * @param dimensions the size of the board.
   * @param row        the row of the initial empty slot.
   * @param col        the column of the initial empty slot.
   * @throws IllegalArgumentException if the dimensions are non-positive or the position is out of bounds.
   */
  public TriangleSolitaireModel(int dimensions, int row, int col) {
    if (dimensions <= 0) {
      throw new IllegalArgumentException("Board size must be positive.");
    }
    if (row < 0 || col < 0 || row >= dimensions || col > row) {
      throw new IllegalArgumentException("Invalid initial empty slot position.");
    }
    this.dimensions = dimensions;
    this.boardSize = this.getBoardSize();
    this.board = new SlotState[boardSize][boardSize];
    initializeBoard();
    this.board[row][col] = SlotState.Empty; // Set the initial empty slot
  }

  /**
   * Initializes the board by setting all valid positions to contain marbles.
   */
  protected void initializeBoard() {
    for (int row = 0; row < dimensions; row++) {
      for (int col = 0; col <= row; col++) {
        this.board[row][col] = SlotState.Marble;
      }
    }
  }

  /**
   * Moves a marble from the specified position to another specified position.
   *
   * @param fromRow the row number of the position to be moved from.
   * @param fromCol the column number of the position to be moved from.
   * @param toRow   the row number of the position to be moved to.
   * @param toCol   the column number of the position to be moved to.
   * @throws IllegalArgumentException if the move is not valid.
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    if ((fromRow < 0) || (fromRow >= boardSize) || (fromCol < 0) || (fromCol > fromRow)
            || (toRow < 0) || (toRow >= boardSize) || (toCol < 0) || (toCol > toRow)) {
      throw new IllegalArgumentException("Coordinates out of bounds");
    }

    if (board[toRow][toCol] == SlotState.Marble) {
      throw new IllegalArgumentException("There is a marble at row " + toRow +
              ", col " + toCol);
    }

    if (board[fromRow][fromCol] != SlotState.Marble) {
      throw new IllegalArgumentException("There is no marble at row " + fromRow +
              ", col " + fromCol);
    }

    if (board[toRow][toCol] != SlotState.Empty) {
      throw new IllegalArgumentException("There is no empty space at row " + toRow +
              ", col " + toCol);
    }

    if (!(Math.abs((toRow - fromRow) + (toCol - fromCol)) == 2)) {
      throw new IllegalArgumentException("The marbles are too far");
    }

    if (Math.abs(toRow - fromRow) == 2 && toCol == fromCol) {
      int betweenRow = (fromRow + toRow) / 2;
      if (!(board[betweenRow][fromCol] == SlotState.Marble)) {
        throw new IllegalArgumentException("There is no marble in between");
      }
    }

    if (Math.abs(toCol - fromCol) == 2 && toRow == fromRow) {
      int betweenCol = (fromCol + toCol) / 2;
      if (!(board[fromRow][betweenCol] == SlotState.Marble)) {
        throw new IllegalArgumentException("There is no marble in between");
      }
    }

    if (toRow - fromRow == 2) { // to check if the move is to the down
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
      this.board[fromRow + 1][fromCol] = SlotState.Empty;
      this.score = score - 1;
    }

    if (toRow - fromRow == -2) {// to check if the move is to the up
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
      this.board[fromRow - 1][fromCol] = SlotState.Empty;
      this.score = score - 1;
    }

    if (toCol - fromCol == 2) {// to check if the move is right
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
      this.board[fromRow][fromCol + 1] = SlotState.Empty;
      this.score = score - 1;
    }

    if (toCol - fromCol == -2) {// to check if the move is to the left
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
      this.board[fromRow][fromCol - 1] = SlotState.Empty;
      this.score = score - 1;
    }

    if (toRow < fromRow && toCol < fromCol) {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[fromRow - 1][fromCol - 1] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    } else if (toRow > fromRow && toCol > fromCol) {
      this.board[fromRow][fromCol] = SlotState.Empty;
      this.board[fromRow + 1][fromCol + 1] = SlotState.Empty;
      this.board[toRow][toCol] = SlotState.Marble;
    }


  }

  /**
   * Checks if the game is over, i.e., there are no valid moves left.
   *
   * @return true if the game is over, false otherwise.
   */
  @Override
  public boolean isGameOver() {
    // Check if there is only one marble left on the board
    int marbleCount = 0;
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (board[row][col] == SlotState.Marble) {
          marbleCount++;
        }
      }
    }
    if (marbleCount == 1) {
      return true;
    }

    // Check if there are any valid moves left
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (board[row][col] == SlotState.Marble) {
          if (isValidMove(row, col, row + 2, col) ||
                  isValidMove(row, col, row - 2, col) ||
                  isValidMove(row, col, row, col + 2) ||
                  isValidMove(row, col, row, col - 2)) {
            return false;
          }
        }
      }
    }

    // No valid moves left
    return true;
  }

  /**
   * Checks if a move is valid.
   *
   * @param fromRow the row number of the position to be moved from.
   * @param fromCol the column number of the position to be moved from.
   * @param toRow   the row number of the position to be moved to.
   * @param toCol   the column number of the position to be moved to.
   * @return true if the move is valid, false otherwise.
   */
  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    // Check if the to position is within the bounds of the triangle board
    if (toRow < 0 || fromRow < 0 ||
            toRow > boardSize - 1 || fromRow > boardSize - 1 ||
            toCol < 0 || fromCol < 0 ||
            toCol > boardSize - 1 || fromCol > boardSize - 1) {
      return false;
    }

    // Check if the to position is empty and the from position has a marble
    if (board[toRow][toCol] != SlotState.Empty || board[fromRow][fromCol] != SlotState.Marble) {
      return false;
    }

    // Check for valid vertical move
    if (Math.abs(toRow - fromRow) == 2 && toCol == fromCol) {
      int betweenRow = (fromRow + toRow) / 2;
      return board[betweenRow][fromCol] == SlotState.Marble;
    }

    // Check for valid horizontal move
    if (Math.abs(toCol - fromCol) == 2 && toRow == fromRow) {
      int betweenCol = (fromCol + toCol) / 2;
      return board[fromRow][betweenCol] == SlotState.Marble;
    }

    if (Math.abs(toRow - fromRow) == 2 && Math.abs(toCol - fromCol) == 2) {
      int betweenRow = (fromRow + toRow) / 2;
      int betweenCol = (fromCol + toCol) / 2;
      return board[betweenRow][betweenCol] == SlotState.Marble;
    }

    return false;
  }

  /**
   * Returns the size of the board.
   *
   * @return the size of the board.
   */
  @Override
  public int getBoardSize() {
    return dimensions;
  }

  /**
   * Returns the slot state at a specified position.
   *
   * @param row the row of the position sought.
   * @param col the column of the position sought.
   * @return the SlotState of the specified position.
   * @throws IllegalArgumentException if the position is out of bounds.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= boardSize || col < 0 || col > row) {
      throw new IllegalArgumentException("Invalid row or column");
    }
    return board[row][col];
  }

  /**
   * Returns the current score, which is the number of marbles left on the board.
   *
   * @return the current score.
   */
  @Override
  public int getScore() {
    int score = 0;
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col <= row; col++) {
        if (board[row][col] == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }
}
