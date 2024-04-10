import java.util.Scanner;

public class TaskRunner {
  public static void main(String[] args) {
    runTask(getTaskSelection());
  }

  public static int getTaskSelection() {
    Scanner userInput = new Scanner(System.in);
    System.out.println("Welche Aufgabe willst du Ausführen? (1/2/3)");
    return userInput.nextInt();
  }

  public static String getSubtaskSelection() {
    Scanner userInput = new Scanner(System.in);
    return userInput.next();
  }

  public static void runTask(int taskNumber) {
    switch (taskNumber) {
      case 1:
        System.out.println(
            "Wähle eine Unteraufgabe:\n"
                + "a) Begrüßung\n"
                + "b) Positiv oder Negativ\n"
                + "c) Mathematische Grundoperationen\n"
                + "d) Größte Zahl finden\n"
                + "e) Quadratische Gleichung lösen\n");
        String subtask = getSubtaskSelection();
        switch (subtask) {
          case "a":
            Aufgabe1.greeting();
            break;
          case "b":
            Aufgabe1.positiveNegativeZero();
            break;
          case "c":
            Aufgabe1.basicMath();
            break;
          case "d":
            Aufgabe1.getMax();
            break;
          case "e":
            Aufgabe1.quadraticEquation();
            break;
        }
        break;
      case 2:
        Aufgabe2.mainLoop();
        break;
      case 3:
        Aufgabe3.aufgabe3();
    }
  }
}
