public class Board {

  private final char[][] grid;
  private final int size;

  public Board(int size) {
    this.size = size;
    this.grid = new char[size][size];

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        grid[i][j] = ' ';
      }
    }
  }

  public int getSize() {
    return size;
  }

  public void applyMove(Move move, char symbol) {
    grid[move.getRow()][move.getCol()] = symbol;
  }

  public boolean isWinning(char symbol) {
    for (int i = 0; i < size; i++) {
      boolean rowWin = true;
      boolean colWin = true;

      for (int j = 0; j < size; j++) {
        if (grid[i][j] != symbol) {
          rowWin = false;
        }
        if (grid[j][i] != symbol) {
          colWin = false;
        }
      }

      if (rowWin || colWin) {
        return true;
      }
    }

    boolean diag1 = true;
    boolean diag2 = true;

    for (int i = 0; i < size; i++) {
      if (grid[i][i] != symbol) {
        diag1 = false;
      }
      if (grid[i][size - 1 - i] != symbol) {
        diag2 = false;
      }
    }

    return diag1 || diag2;
  }

  public void print() {
    System.out.print("   ");
    for (int j = 1; j <= size; j++) {
      System.out.print(j + "   ");
    }
    System.out.println();

    for (int i = 0; i < size; i++) {
      System.out.print((i + 1) + " ");
      for (int j = 0; j < size; j++) {
        System.out.print(" " + grid[i][j] + " ");
        if (j < size - 1) {
          System.out.print("|");
        }
      }
      System.out.println();

      if (i < size - 1) {
        System.out.print("  ");
        for (int j = 0; j <= size; j++) {
          System.out.print("---");
        }
        System.out.println();
      }
    }
  }
}
