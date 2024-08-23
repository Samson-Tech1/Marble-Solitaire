package cs3500.marblesolitaire.model.hw02;

//class to create the marble solitaire game
public class EnglishSolitaireModel implements MarbleSolitaireModel {
  protected final int armThickness; //the amount of marbles at the top of the cross
  protected final int boardSize; // the length of the gameBoard
  protected final SlotState[][] board; // the game board
  protected final boolean gameOver; // game status checker
  protected int score; // the score accumulated

  // initial constructor for the board
  public EnglishSolitaireModel() {
    this.armThickness = 3; // initialize the arm thickness to 3
    this.boardSize = armThickness * 3 - 2; //compute the correct board size
    this.board = new SlotState[boardSize][boardSize]; // initialize a 2d array of slot states
    this.initializeBoard(); // initialize the board given the conditions
    this.score = getScore();
    this.gameOver = isGameOver();

    //initializing the empty slot at the center
    this.board[armThickness][armThickness] = SlotState.Empty;
  }

  //constructor to place the empty slot anywhere on the board
  public EnglishSolitaireModel(int sRow, int sCol) {
    this.armThickness = 3;
    this.boardSize = armThickness * 3 - 2;
    this.board = new SlotState[boardSize][boardSize];
    this.initializeBoard();
    this.score = getScore();
    this.gameOver = isGameOver();

    //exception thrown if given an invalid position on the board
    if ((sRow > boardSize - 1) || (sCol > boardSize - 1) || (sCol < 0) || sRow < 0 ||
            !(board[sRow][sCol] == SlotState.Marble)) { //valid checker maybe check board is a specific character
      throw new IllegalArgumentException("Invalid empty cell position (" +
              sRow + "," + sCol + ")");
    }

    this.setSlot(sRow, sCol, SlotState.Empty);


  }

  //initialize the empty cell in the middle and throws exception if arm thickness is not a positive odd number
  public EnglishSolitaireModel(int armThickness) {
    this.armThickness = armThickness;
    this.boardSize = armThickness * 3 - 2;
    this.board = new SlotState[boardSize][boardSize];
    this.initializeBoard();
    this.score = getScore();
    this.gameOver = isGameOver();

    //throws exception if armThickness is even or
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be odd");
    }

    //initializing the empty slot at the center
    this.board[armThickness][armThickness] = SlotState.Empty;
  }

  //constructor that throws an exception if arm thickness is invalid or the empty space coordinates are inactive
  public EnglishSolitaireModel(int armThickness, int blkRow, int blkCol) {
    this.armThickness = armThickness;
    this.boardSize = armThickness * 3 - 2;
    this.board = new SlotState[boardSize][boardSize];
    this.gameOver = false;
    this.initializeBoard();
    this.score = getScore(); // initialize score to 0

    //throws exception if armThickness is even
    if (armThickness % 2 == 0) {
      throw new IllegalArgumentException("Arm thickness must be odd");
    }

    if (((blkRow > boardSize - 1) || (blkCol > boardSize - 1) || (blkCol < 0) || (blkRow < 0) ||
            !(board[blkRow][blkCol] == SlotState.Marble))) { //valid checker
      throw new IllegalArgumentException("Invalid empty cell position (" +
              blkRow + "," + blkCol + ")");
    }
    this.setSlot(blkRow, blkCol, SlotState.Empty);

  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    if ((fromRow < 0) || (fromRow > boardSize - 1) || (fromCol < 0) || (fromCol > boardSize - 1)
            || (toRow < 0) || (toCol > boardSize - 1) || (toRow > boardSize - 1) || (toCol < 0)) {
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


    if (((Math.abs(toRow - fromRow)) != 2 && (Math.abs(toCol - fromCol)) != 2) ||
            ((Math.abs(toCol - fromCol)) == 2 && toRow - fromRow != 0) ||
            ((Math.abs(toRow - fromRow)) == 2 && toCol - fromCol != 0)) {
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

  }


  /**
   * sets the slot state at the given row, column
   *
   * @param row the row of the board
   *            (starts at 0)
   * @param col the column of the board
   *            (starts at 0)
   */
  protected void setSlot(int row, int col, SlotState slotState) {//sets the empty slot state at the given row, column
    if (row > boardSize - 1 || col > boardSize - 1) {
      throw new IllegalArgumentException("Invalid row or column");
    }
    this.board[row][col] = slotState;
  }


  /**
   * creates the initial starting board for the marble solitaire game based off
   * the armThickness and the boardSize
   */
  protected void initializeBoard() {
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (((row < armThickness - 1) && (col < armThickness - 1)) ||
                ((row < armThickness - 1) && (col > boardSize - armThickness)) ||
                ((col < armThickness - 1) && (row > boardSize - armThickness)) ||
                ((col > boardSize - armThickness) && (row > boardSize - armThickness))) {
          this.board[row][col] = SlotState.Invalid;
        } else {
          this.board[row][col] = SlotState.Marble;
        }
      }
    }
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
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
   * isValidMove returns false if an invalid move is being attempted
   * otherwise it returns true if there is a valid move
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   */
  private boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
    if (toRow < 0 || toRow > boardSize - 1 || toCol < 0 || toCol > boardSize - 1) {
      return false;
    }

    if (board[toRow][toCol] != SlotState.Empty) {
      return false;
    }

    if (board[fromRow][fromCol] != SlotState.Marble) {
      return false;
    }

    if (Math.abs(toRow - fromRow) == 2 && toCol == fromCol) {
      int betweenRow = (fromRow + toRow) / 2;
      if (board[betweenRow][fromCol] == SlotState.Marble) {
        return true;
      }
    }

    if (Math.abs(toCol - fromCol) == 2 && toRow == fromRow) {
      int betweenCol = (fromCol + toCol) / 2;
      return board[fromRow][betweenCol] == SlotState.Marble;
    }

    return false;
  }

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */

  @Override
  public int getBoardSize() {
    return this.boardSize;
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row > boardSize - 1 || col > boardSize - 1) {
      throw new IllegalArgumentException("Invalid row or column");
    }
    return this.board[row][col];
  }


  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */

  @Override
  public int getScore() {
    int score = 0;
    for (int row = 0; row < boardSize; row++) {
      for (int col = 0; col < boardSize; col++) {
        if (this.board[row][col] == SlotState.Marble) {
          score++;
        }
      }
    }
    return score;
  }


}