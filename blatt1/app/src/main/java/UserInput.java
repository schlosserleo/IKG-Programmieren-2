import java.math.BigDecimal;
import java.util.Scanner;

/** UserInput */
public class UserInput {

  public static int getLineInt() {
    Scanner userInput = new Scanner(System.in);
    return userInput.nextInt();
  }

  public static String getLine() {
    Scanner userInput = new Scanner(System.in);
    return userInput.next();
  }

  public static BigDecimal getLineBigDecimal() {
    Scanner userInput = new Scanner(System.in);
    return userInput.nextBigDecimal();
  }
}
