import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    do {
      Game game = new Game(scanner, 3);
      game.play();

      System.out.println("Do you want to play again? ('y' to continue)");
    } while (scanner.nextLine().trim().equalsIgnoreCase("y"));

    System.out.println("Thanks for playing! :-)");
  }
}
