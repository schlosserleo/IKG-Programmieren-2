package crm;

import java.util.ArrayList;

public class Printing {
  public static void printMainMenu() {
    System.out.println(
        new StringBuilder("Plese choose the operation:\n")
            .append("1 - Show/Filter clients\n")
            .append("2 - Automated report\n")
            .append("3 - Search a customer\n")
            .append("4 - Delete a customer\n")
            .append("5 - Add a customer\n")
            .append("6 - Sort List\n")
            .append("7 - Reset List\n")
            .append("8 - Quit")
            .toString());
  }

  public static void printCustomers(ArrayList<Customer> customers) {
    int i = 0;
    printTableHeader();
    for (Customer currentCustomer : customers) {
      System.out.println(
          new StringBuilder(i % 2 == 0 ? "\033[100m" : "\033[40m")
              .append(currentCustomer.prettyToString())
              .append(i % 2 == 0 ? "\033[0m" : "\033[0m"));
      i++;
    }
  }

  public static void printTableHeader() {
    System.out.println(
        "\033[37;40m|id    | age|sex     |bmi     |children|smoker|region     |charges      |\033[0m");
  }
}
