import java.text.MessageFormat;
import java.util.*;

public class Aufgabe1 {
  public static void greeting() {
    System.out.println("Wie heißt du?");
    String input = UserInput.getLine();
    System.out.println("Guten Tag, " + input + "!");
  }

  public static void positiveNegativeZero() {
    System.out.println("Geben sie eine Zahl ein");
    int input = UserInput.getLineInt();
    String result;
    if (input > 0) {
      result = "Sie ist positiv";
    } else if (input < 0) {
      result = "Sie ist negativ";
    } else {
      result = "Sie ist 0";
    }
    System.out.println(result);
  }

  public static void basicMath() {
    System.out.println(
        "Geben sie 2 zahlen an denen die 4 Grundrechenarten ausgeführt werden sollen");
    System.out.println("erste Zahl:");
    int a = UserInput.getLineInt();
    System.out.println("zweite Zahl:");
    int b = UserInput.getLineInt();
    int addition = a + b;
    int subtraction = a - b;
    int product = a * b;
    double quotient = (double) a / (double) b;

    System.out.println(
        MessageFormat.format(
            "{0} + {1} = {2}\n{0} - {1} = {3}\n{0} × {1} = {4}\n{0} ÷ {1} = {5}",
            a, b, addition, subtraction, product, quotient));
  }

  public static void getMax() {
    System.out.println("Geben sie 3 Zahlen und das Programm wird die Größte Zahl ermitteln");

    System.out.println("erste Zahl:");
    int a = UserInput.getLineInt();
    System.out.println("zweite Zahl:");
    int b = UserInput.getLineInt();
    System.out.println("dritte Zahl:");
    int c = UserInput.getLineInt();

    int[] numbers = new int[] { a, b, c };
    System.out.println(Arrays.stream(numbers).max().getAsInt());
  }

  private static ArrayList<Double> quadraticEquationCalculation(double a, double b, double c) {
    if (a == 0) {
      return new ArrayList<Double>();
    }
    ArrayList<Double> result = new ArrayList<Double>();
    double sqrtContent = Math.pow(b, 2) - (4 * a * c);
    if (sqrtContent < 0) {
      return new ArrayList<>();
    }
    double sqrt = Math.sqrt(sqrtContent);
    result.add((-b - sqrt) / 2 * a);
    result.add((-b + sqrt) / 2 * a);

    return result;
  }

  public static void quadraticEquation() {
    System.out.println(
        "Wir haben eine Quadratische Funktion f(x) = ax² + bx + c\n"
            + "Geben sie werte für a,b und c ein um die Nullstellen der Funktion zu ermittlen\n"
            + "a:");
    int a = UserInput.getLineInt();
    System.out.println("b:");
    int b = UserInput.getLineInt();
    System.out.println("c:");
    int c = UserInput.getLineInt();

    ArrayList<Double> quadRes = quadraticEquationCalculation(a, b, c);
    double nstOne = quadRes.get(0);
    double nstTwo = quadRes.get(1);
    if (quadRes.size() != 2) {
      System.out.println("Die Funktion hat keine Nullstellen oder ist keine quadratische Funktion");
      return;
    }
    if (nstTwo == nstOne) {
      System.out.println("Die Funktion hat eine doppelte Nullstelle bei " + nstOne);
      return;
    }
    System.out.println("Die Funktion hat eine Nullstelle bei " + nstOne + " und bei " + nstTwo);
    return;
  }
}
