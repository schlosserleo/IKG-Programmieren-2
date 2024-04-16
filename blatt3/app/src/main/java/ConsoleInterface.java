public class ConsoleInterface {
  public static void mainLoop() {
    boolean wantToExit = false;
    while (true && !wantToExit) {
      System.out.println(
          "Please choose the operation:\n"
              + "1 - List clients\n"
              + "2 - Automated report\n"
              + "3 - Search a customer\n"
              + "4 - Delete a customer\n"
              + "5 - Add a customer\n"
              + "6 - Sort by ID");
    }
  }
}
