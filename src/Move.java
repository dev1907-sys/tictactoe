import java.util.Objects;

public class Move {

  private int row, col;

  public Move(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Move move)) {
      return false;
    }
    return row == move.row && col == move.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }
}
