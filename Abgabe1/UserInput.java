package Abgabe1;

import java.math.BigDecimal;
import java.util.Scanner;
import java.io.Console;

public class UserInput {
  public static int getLineInt() {
    Console con = System.console();
    if (con == null) {
      throw new Error("No console interface!");
    }
    int result;
    Scanner userInput = new Scanner(con.reader());
    while (!userInput.hasNextInt()) {
      System.out.println("That's not a valid number!");
      userInput.next();
    }
    result = userInput.nextInt();
    userInput.close();
    return result;
  }

  public static String getLine() {
    Console con = System.console();
    if (con == null) {
      throw new Error("No console interface!");
    }
    String result;
    Scanner userInput = new Scanner(con.reader());
    while (!userInput.hasNext()) {
      System.out.println("That's not a valid input!");
      userInput.next();
    }
    result = userInput.next();
    userInput.close();
    return result;
  }

  public static BigDecimal getLineBigDecimal() {
    Console con = System.console();
    if (con == null) {
      throw new Error("No console interface!");
    }
    BigDecimal result;
    Scanner userInput = new Scanner(con.reader());
    while (!userInput.hasNext()) {
      System.out.println("That's not a number!");
      userInput.next();
    }
    result = userInput.nextBigDecimal();
    userInput.close();
    return result;
  }
}
