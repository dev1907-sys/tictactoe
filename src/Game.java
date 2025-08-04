import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Game {

  private final Scanner scanner;
  private final int boardSize;
  private final Board board;
  private final Player human;
  private final Player computer;
  private final Set<Move> possibleMoves;

  public Game(Scanner scanner, int boardSize) {
    this.scanner = scanner;
    this.boardSize = boardSize;
    this.board = new Board(boardSize);
    this.possibleMoves = fillSet(boardSize);
    Player[] players = startGame();
    this.human = players[0];
    this.computer = players[1];
  }

  private Player[] startGame() {
    System.out.println("Welcome to my Tic Tac Toe game!");
    System.out.println("Start by choosing your sign: type 'X' or 'O'");

    char playerSign;
    while (true) {
      String input = scanner.nextLine().trim().toUpperCase();
      if (input.length() == 1 && (input.charAt(0) == 'X' || input.charAt(0) == 'O')) {
        playerSign = input.charAt(0);
        break;
      }
      System.out.println("Invalid input. Please try again.");
    }

    Player human = new Player();
    Player computer = new Player();
    human.setSymbol(playerSign);
    human.setHuman(true);
    computer.setSymbol(playerSign == 'X' ? 'O' : 'X');
    computer.setHuman(false);
    System.out.println("You are " + human.getSymbol() + ", computer is " + computer.getSymbol());
    return new Player[]{human, computer};
  }

  private Set<Move> fillSet(int size) {
    Set<Move> moves = new HashSet<>();
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        moves.add(new Move(i, j));
      }
    }
    return moves;
  }

  public void play() {
    Player currentPlayer = human.getSymbol() == 'X' ? human : computer;
    boolean gameEnded = false;

    while (!gameEnded) {
      board.print();
      Move move;

      if (currentPlayer.isHuman()) {
        move = askMove();
      } else {
        move = getRandomMove();
        System.out.println("Computer plays: " + (move.getRow() + 1) + "," + (move.getCol() + 1));
      }

      board.applyMove(move, currentPlayer.getSymbol());
      possibleMoves.remove(move);

      if (board.isWinning(currentPlayer.getSymbol())) {
        board.print();
        System.out.println((currentPlayer.isHuman() ? "You" : "Computer") + " win!");
        gameEnded = true;
      } else if (possibleMoves.isEmpty()) {
        board.print();
        System.out.println("It's a draw.");
        gameEnded = true;
      } else {
        currentPlayer = currentPlayer == human ? computer : human;
      }
    }
  }

  private Move askMove() {
    while (true) {
      try {
        System.out.println("Enter your move (row and column 1-" + boardSize + "):");
        System.out.print("Row: ");
        int row = scanner.nextInt() - 1;
        System.out.print("Col: ");
        int col = scanner.nextInt() - 1;
        scanner.nextLine();

        Move move = new Move(row, col);
        if (!possibleMoves.contains(move)) {
          System.out.println("Invalid or already played. Try again.");
        } else {
          return move;
        }
      } catch (Exception e) {
        System.out.println("Invalid input. Try again.");
        scanner.nextLine();
      }
    }
  }

  private Move getRandomMove() {
    int index = new Random().nextInt(possibleMoves.size());
    return new ArrayList<>(possibleMoves).get(index);
  }
}

