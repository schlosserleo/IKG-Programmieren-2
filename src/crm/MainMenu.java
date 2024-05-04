package crm;

import java.util.ArrayList;

public class MainMenu {
  public static void mainLoop() {
    ArrayList<Customer> allCustomers = CSVParser.parseCSV("insurance.csv");
    boolean wantToExit = false;
    int userTaskSelection;
    while (true && !wantToExit) {
      Printing.printMainMenu();
      userTaskSelection = InputHandler.getLineInt();
      if (!validateNumberRange(userTaskSelection)) {
        continue;
      }
      wantToExit = launchTask(userTaskSelection, allCustomers);
    }
  }

  private static boolean validateNumberRange(int input) {
    if (input < 1 || input > 7) {
      System.out.println("That's not a valid Task!");
      return false;
    }
    return true;
  }

  private static boolean launchTask(int taskSelection, ArrayList<Customer> allCustomers) {
    switch (taskSelection) {
      case 7:
        return true;
      case 1:
        Tasks.showCustomers(allCustomers);
    }
    return false;
  }
}
