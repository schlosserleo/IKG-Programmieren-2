public class Runner {
  public static void main(String[] args) {
    System.out.println("Welche Aufgabe willst du ausführen(2/3)");
    int userSelection = UserInput.getLineInt();
    ConsoleInterface.mainLoop(userSelection);
  }
}
