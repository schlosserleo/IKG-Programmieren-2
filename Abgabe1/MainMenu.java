package Abgabe1;

public class MainMenu {
  public static void mainLoop() {
    boolean wantToExit = false;
    int userTaskSelection;
    while (true && !wantToExit) {
      Printing.printMainMenu();
      userTaskSelection = UserInput.getLineInt();
      if (!validateNumberRange(userTaskSelection)) {
        continue;
      }
      wantToExit = launchTask(userTaskSelection);
    }
  }

  private static boolean validateNumberRange(int input) {
    if (input < 1 || input > 7) {
      System.out.println("That's not a valid Task!");
      return false;
    }
    return true;
  }

  private static boolean launchTask(int taskSelection) {
    switch (taskSelection) {
      case 7:
        return true;
      case 1:
        Tasks.showCustomers();
    }
    return false;
  }
}
