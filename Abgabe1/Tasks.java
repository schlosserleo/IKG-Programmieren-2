package Abgabe1;

import java.math.BigDecimal;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;

public class Tasks {
  public static void showCustomers() {
    ArrayList<Customer> allCustomers = CSVParser.parseCSV("insurance.csv");
    Printing.printRefineOutputQuestion();
    if (!UserInput.getLine().equals("y")) {
      Printing.printCustomers(allCustomers);
      return;
    }
    Printing.printRefinementOptions();
  }

  private static ArrayList<Customer> createFilterCustomer() {
    // Creating 2 Users, one with Min, one with Max values.
    // For Filter Purposes
    Customer minValues = new Customer(
        -1,
        -1,
        false,
        false,
        BigDecimal.valueOf(-1),
        BigDecimal.valueOf(-1),
        "");
    Customer maxValues = new Customer(
        1000,
        1000,
        true,
        true,
        BigDecimal.valueOf(1000),
        BigDecimal.valueOf(100000000),
        "northwest,northeast,southwest,southeast");
    String[] values = new String[] { "age", "bmi", "children", "charges" };
    String[] minMax = new String[] { "min", "max" };
    Customer[] filterCustomers = new Customer[] { minValues, maxValues };
    int j;
    for (int i = 0; i < 4; i++) {
      switch (values[i]) {
        case "age":
          j = 0;
          for (String prefix : minMax) {
            System.out.println(prefix + "age:");
            filterCustomers[j].setAgeInteractive();
            j++;
          }
          break;
        case "age":
          j = 0;
          for (String prefix : minMax) {
            System.out.println(prefix + "age:");
            filterCustomers[j].setAgeInteractive();
            j++;
          }
          break;
      }
      i++;
    }
  }
}
