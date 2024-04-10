import java.text.MessageFormat;
import java.util.Random;
import java.util.Scanner;

public class Aufgabe3 {
  public static void aufgabe3() {
    gameLoop();
  }

  public static int generateRandom() {
    Random rnd = new Random(System.currentTimeMillis());
    return 1 + rnd.nextInt(10);
  }

  public static void gameLoop() {
    Scanner userIn = new Scanner(System.in);
    Scanner userIn2 = new Scanner(System.in);
    boolean wantToExit = false;
    boolean hasGuessed = false;
    int userGuess;
    String goodByeMessage = "Bye! Bis zum nächsten mal :)";

    while (true && !wantToExit) {
      int randomNumber = generateRandom();

      for (int i = 0; i < 10; i++) {
        System.out.println(
            MessageFormat.format("Rate eine Zahl von 1 to 10. Du hast noch {0} Versuche", 10 - i));
        userGuess = userIn.nextInt();

        if (userGuess == randomNumber) {
          System.out.println(
              MessageFormat.format("Yippiie du hast die Zahl in {0} Versuchen erraten!", i + 1));
          hasGuessed = true;
          break;

        } else if (userGuess == 99) {
          System.out.println(goodByeMessage);
          wantToExit = true;
          return;

        } else {
          System.out.println("Das war falsch\n");
        }
      }

      if (!hasGuessed) {
        System.out.println(
            MessageFormat.format("Schade, nicht erraten, die Zufallszahl war {0}", randomNumber));
      }

      System.out.println("Willst du noch einmal Spielen?(j/n)");

      if (userIn2.next().charAt(0) == 'j') {
        continue;
      } else {
        System.out.println(goodByeMessage);
        return;
      }
    }
  }
}
