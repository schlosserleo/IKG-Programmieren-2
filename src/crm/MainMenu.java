package crm;

import java.util.ArrayList;

public class MainMenu {
  private static ArrayList<Customer> customers;

  public static void mainLoop() {
    customers = CSVParser.parseCSV("insurance.csv");
    boolean wantToExit = false;
    int userTaskSelection;
    while (true && !wantToExit) {
      Printing.printMainMenu();
      userTaskSelection = InputHandler.getLineInt();
      if (!validateNumberRange(userTaskSelection)) {
        continue;
      }
      wantToExit = launchTask(userTaskSelection);
    }
  }

  private static boolean validateNumberRange(int input) {
    if (input < 1 || input > 8) {
      System.out.println("That's not a valid Task!");
      return false;
    }
    return true;
  }

  private static boolean launchTask(int taskSelection) {
    switch (taskSelection) {
      case 1:
        Tasks.showCustomers(customers);
        break;
      case 2:
        Tasks.showStatistics(customers);
        break;
      case 3:
        Tasks.searchCustomer(customers);
        break;
      case 4:
        Tasks.deleteCustomer(customers);
        break;
      case 5:
        Tasks.addCustomer(customers);
        break;
      case 6:
        Tasks.showSortedCustomers(customers);
        break;
      case 7:
        Tasks.resetList();
        break;
      case 8:
        return true;
    }
    return false;
  }
}
