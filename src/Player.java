public class Player {

  private char symbol;
  private boolean isHuman;

  public char getSymbol() {
    return symbol;
  }

  public boolean isHuman() {
    return isHuman;
  }

  public void setSymbol(char symbol) {
    this.symbol = symbol;
  }

  public void setHuman(boolean human) {
    isHuman = human;
  }
}
