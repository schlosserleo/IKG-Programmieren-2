package crm;

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
    return new ArrayList<Customer>();
  }
}
