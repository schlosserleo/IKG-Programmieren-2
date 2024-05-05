package crm;

import java.util.ArrayList;

public class Tasks {
  private static ArrayList<Customer> changedCustomers = new ArrayList<Customer>();
  private static boolean isChanged = false;

  public static void showCustomers(ArrayList<Customer> customers) {
    Printing.printRefineOutputQuestion();
    if (!InputHandler.getLine().equals("y")) {
      if (isChanged) {
        Printing.printCustomers(changedCustomers);
      } else {
        Printing.printCustomers(customers);
      }
      return;
    }
    Customer[] filterValues = FilterCustomers.getFilterValues();
    Customer minValues = filterValues[0];
    Customer maxValues = filterValues[1];
    if (isChanged) {
      changedCustomers = FilterCustomers.getfilteredCustomers(changedCustomers, minValues, maxValues);
    } else {
      changedCustomers = FilterCustomers.getfilteredCustomers(customers, minValues, maxValues);
    }
    isChanged = true;
    Printing.printCustomers(changedCustomers);
  }

  public static void showSortedCustomers(ArrayList<Customer> customers) {
    if (isChanged) {
      changedCustomers = sortCustomers(changedCustomers);
    } else {
      changedCustomers = sortCustomers(customers);
    }
    isChanged = true;
    Printing.printCustomers(changedCustomers);
  }

  public static

  private static ArrayList<Customer> sortCustomers(ArrayList<Customer> customers) {
    System.out.println("Which Attribute you want to sort after?\n" +
        "id, age, sex, bmi, children, smoker, region, charges");
    String attributeToBeFiltered = InputHandler.getLine();
    switch (attributeToBeFiltered) {
      case "id":
        customers.sort((c1, c2) -> c1.getId() - c2.getId());
        return customers;
      case "age":
        customers.sort((c1, c2) -> c1.getAge() - c2.getAge());
        return customers;
      case "bmi":
        customers.sort((c1, c2) -> c1.getBmi().compareTo(c2.getBmi()));
        return customers;
      case "children":
        customers.sort((c1, c2) -> c1.getChildren() - c2.getChildren());
        return customers;
      case "charges":
        customers.sort((c1, c2) -> c1.getCharges().compareTo(c2.getCharges()));
        return customers;
      case "sex":
        customers.sort((c1, c2) -> c1.getSex().compareTo(c2.getSex()));
        return customers;
      case "smoker":
        customers.sort((c1, c2) -> c1.getSmoker().compareTo(c2.getSmoker()));
        return customers;
      case "region":
        customers.sort((c1, c2) -> c1.getRegion().compareTo(c2.getRegion()));
        return customers;
    }
    return customers;
  }
}
