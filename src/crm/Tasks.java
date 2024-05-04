package crm;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Tasks {
  public static void showCustomers(ArrayList<Customer> customers) {
    Printing.printRefineOutputQuestion();
    if (!InputHandler.getLine().equals("y")) {
      Printing.printCustomers(customers);
      return;
    }
    Customer[] filterValues = getFilterValues();
    Customer minValues = filterValues[0];
    Customer maxValues = filterValues[1];
    customers = getfilteredCustomers(customers, minValues, maxValues);
    Printing.printCustomers(customers);
  }

  private static Customer[] getFilterValues() {
    Customer minValues = new Customer(
        -1,
        -1,
        "any",
        "any",
        BigDecimal.valueOf(-1),
        BigDecimal.valueOf(-1),
        "northwest,northeast,southwest,southeast");

    Customer maxValues = new Customer(
        1000,
        1000,
        "",
        "",
        BigDecimal.valueOf(1000),
        BigDecimal.valueOf(100000000),
        "");

    String[] minMaxAttributes = new String[] { "age", "bmi", "children", "charges" };
    for (String attribute : minMaxAttributes) {
      System.out.println("min " + attribute + ":");
      minValues.updateCustomer(attribute);
      System.out.println("max " + attribute + ":");
      maxValues.updateCustomer(attribute);
    }
    System.out.println("Sex(m/f):");
    minValues.setSexInteractive();
    System.out.println("Smoker(y/n):");
    minValues.setSmokerInteractive();
    System.out.println("region(if including multiple, please seperate by \",\"):");
    minValues.setRegionInteractive();
    return new Customer[] { minValues, maxValues };
  }

  public static ArrayList<Customer> getfilteredCustomers(ArrayList<Customer> allCustomers, Customer minValues,
      Customer maxValues) {
    ArrayList<Customer> result = new ArrayList<Customer>();
    boolean matchesAge, matchesSex, matchesBmi, matchesChildren, matchesSmoker, matchesRegion, matchesCharges;
    for (Customer x : allCustomers) {
      matchesAge = matchesSex = matchesBmi = matchesChildren = matchesSmoker = matchesRegion = matchesCharges = false;

      matchesAge = x.age >= minValues.age && x.age <= maxValues.age;

      matchesChildren = x.children >= minValues.children && x.children <= maxValues.children;

      matchesSex = (minValues.sex.equals("any") ? true : x.sex.equals(minValues.sex));

      matchesSmoker = (minValues.smoker.equals("any") ? true : x.smoker.equals(minValues.smoker));

      matchesBmi = x.bmi.compareTo(minValues.bmi) >= 0 && x.bmi.compareTo(maxValues.bmi) <= 0;

      matchesCharges = x.charges.compareTo(minValues.charges) >= 0 && x.charges.compareTo(maxValues.charges) <= 0;

      for (String s : minValues.region.split(",")) {
        if (s.equals(x.region)) {
          matchesRegion = true;
          break;
        }
      }

      if (matchesAge && matchesSex && matchesBmi && matchesChildren && matchesSmoker && matchesRegion
          && matchesCharges) {
        result.add(x);
      }
    }
    return result;
  }
}
