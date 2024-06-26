package crm;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Tasks {
  private static ArrayList<Customer> changedCustomers = new ArrayList<Customer>();
  private static boolean firstRun = false;

  public static void showCustomers(ArrayList<Customer> customers) {
    System.out.println("Do you want to filter the Clients? [y/n]");
    if (!InputHandler.getLine().equals("y")) {
      if (firstRun) {
        Printing.printCustomers(changedCustomers);
      } else {
        Printing.printCustomers(customers);
      }
      return;
    }
    Customer[] filterValues = FilterCustomers.getFilterValues();
    Customer minValues = filterValues[0];
    Customer maxValues = filterValues[1];
    if (firstRun) {
      changedCustomers = FilterCustomers.getfilteredCustomers(changedCustomers, minValues, maxValues);
    } else {
      changedCustomers = FilterCustomers.getfilteredCustomers(customers, minValues, maxValues);
    }
    firstRun = true;
    Printing.printCustomers(changedCustomers);
  }

  public static void resetList() {
    changedCustomers = CSVParser.parseCSV("insurance.csv");
    firstRun = true;
    System.out.println("done");
  }

  public static void addCustomer(ArrayList<Customer> customers) {
    if (firstRun) {
      customers = changedCustomers;
    }
    int customerSize = customers.size();
    int maxId = customers.get(customerSize - 1).getId() + 1;
    Customer newCustomer = createCustomerInteractive(maxId);
    customers.add(newCustomer);
    changedCustomers = customers;
  }

  private static Customer createCustomerInteractive(int id) {
    Customer newCustomer = new Customer(id, 0, 0, "", "", null, null, "");
    String[] attributes = new String[] { "age", "sex", "bmi", "children", "smoker", "region", "charges" };
    for (String attribute : attributes) {
      System.out.println(attribute + ":");
      newCustomer.updateCustomer(attribute);
    }
    return newCustomer;
  }

  public static void deleteCustomer(ArrayList<Customer> customers) {
    boolean foundCustomer = false;
    System.out.println("Type the customer id:");
    int idOfCustomerToBeDeleted = InputHandler.getLineInt();
    if (firstRun) {
      customers = changedCustomers;
    }
    for (Customer x : customers) {
      if (x.id == idOfCustomerToBeDeleted) {
        customers.remove(x);
        foundCustomer = true;
        break;
      }
    }
    if (foundCustomer) {
      System.out.println("done");
      changedCustomers = customers;
      return;
    }
    System.out.println("Customer does not exist");
  }

  public static void showSortedCustomers(ArrayList<Customer> customers) {
    if (firstRun) {
      changedCustomers = sortCustomers(changedCustomers);
    } else {
      changedCustomers = sortCustomers(customers);
      firstRun = true;
    }
    Printing.printCustomers(changedCustomers);
  }

  public static void searchCustomer(ArrayList<Customer> customers) {
    System.out.println("Type the customer id:");
    int idToBeSearched = InputHandler.getLineInt();
    if (firstRun) {
      customers = changedCustomers;
    }
    for (Customer x : customers) {
      if (idToBeSearched == x.id) {
        Printing.printTableHeader();
        System.out.println(x.prettyToString());
        return;
      }
    }
    System.out.println("Customer does not exist");
  }

  public static void showStatistics(ArrayList<Customer> customers) {
    System.out.println(
        "Which Attribute you want to show statistics for ?\n"
            + "age, sex, bmi, children, smoker, charges, region all");

    String attributeToCreateStatistics = InputHandler.getLine();
    while (!attributeToCreateStatistics.matches("^(age|sex|bmi|children|smoker|charges|region|all)$")) {
      System.out.println("That's not a valid attribute!");
      attributeToCreateStatistics = InputHandler.getLine();
    }
    if (attributeToCreateStatistics.equals("all")) {
      for (String attribute : new String[] { "age", "sex", "bmi", "children", "smoker", "charges", "region" }) {
        createStatistics(customers, attribute);
        System.out.println();
      }
      return;
    }
    createStatistics(customers, attributeToCreateStatistics);
  }

  public static void createStatistics(ArrayList<Customer> customers, String attributeToCreateStatistics) {

    BigDecimal[] dataToBeAnalysed;
    if (firstRun) {
      dataToBeAnalysed = createDataArrays(changedCustomers, attributeToCreateStatistics);
    } else {
      dataToBeAnalysed = createDataArrays(customers, attributeToCreateStatistics);
    }

    BigDecimal average = Statistics.average(dataToBeAnalysed);
    BigDecimal stdDeviaton = Statistics.stdDeviation(dataToBeAnalysed);
    BigDecimal max = Statistics.max(dataToBeAnalysed);
    BigDecimal min = Statistics.min(dataToBeAnalysed);

    System.out.println("Statistics for " + attributeToCreateStatistics + ":");

    if (attributeToCreateStatistics.equals("region")) {
      BigDecimal[] regionStaistics = Statistics.regionStatistics(dataToBeAnalysed);
      BigDecimal northwestPercentage = regionStaistics[0].setScale(3, RoundingMode.CEILING);
      BigDecimal northeastPercentage = regionStaistics[1].setScale(3, RoundingMode.CEILING);
      BigDecimal southwestPercentage = regionStaistics[2].setScale(3, RoundingMode.CEILING);
      BigDecimal southeastPercentage = regionStaistics[3].setScale(3, RoundingMode.CEILING);
      System.out.println("Northeast: " + northeastPercentage + "%");
      System.out.println("Northwest: " + northwestPercentage + "%");
      System.out.println("Southeast: " + southeastPercentage + "%");
      System.out.println("Southwest: " + southwestPercentage + "%");
      return;
    }

    if (attributeToCreateStatistics.equals("smoker") || attributeToCreateStatistics.equals("sex")) {
      BigDecimal maleOrSmokerPercentage = average.add(BigDecimal.valueOf(1)).multiply(BigDecimal.valueOf(50));
      BigDecimal femaleOrNoSmokerPercentage = BigDecimal.valueOf(100).subtract(maleOrSmokerPercentage);
      maleOrSmokerPercentage = maleOrSmokerPercentage.setScale(3, RoundingMode.CEILING);
      femaleOrNoSmokerPercentage = femaleOrNoSmokerPercentage.setScale(3, RoundingMode.CEILING);

      System.out.println(
          new StringBuilder("Average: ")
              .append(String.format("%-8s", maleOrSmokerPercentage + "%"))
              .append(attributeToCreateStatistics.equals("sex") ? "male" : "smoker")
              .append("\n         ")
              .append(String.format("%-8s", femaleOrNoSmokerPercentage + "%"))
              .append(attributeToCreateStatistics.equals("sex") ? "female" : "not smoker"));
      return;
    }
    System.out.println(
        new StringBuilder("Average: ")
            .append(average.setScale(3, RoundingMode.CEILING))
            .append("\nStandard deviation:")
            .append(stdDeviaton.setScale(3, RoundingMode.CEILING))
            .append("\nMin: ")
            .append(min)
            .append("\nMax: ")
            .append(max));
  }

  public static BigDecimal[] createDataArrays(
      ArrayList<Customer> customers, String attributeToCreateStatistics) {
    int listLength = customers.size();
    int i;
    BigDecimal[] result = new BigDecimal[listLength];
    if (attributeToCreateStatistics.equals("region")) {
      result = new BigDecimal[4];
    }
    switch (attributeToCreateStatistics) {
      case "id":
        i = 0;
        for (Customer x : customers) {
          result[i] = BigDecimal.valueOf(x.id);
          i++;
        }
      case "age":
        i = 0;
        for (Customer x : customers) {
          result[i] = BigDecimal.valueOf(x.age);
          i++;
        }
        break;
      case "bmi":
        i = 0;
        for (Customer x : customers) {
          result[i] = x.bmi;
          i++;
        }
        break;
      case "children":
        i = 0;
        for (Customer x : customers) {
          result[i] = BigDecimal.valueOf(x.children);
          i++;
        }
        break;
      case "charges":
        i = 0;
        for (Customer x : customers) {
          result[i] = x.charges;
          i++;
        }
        break;
      case "sex":
        i = 0;
        for (Customer x : customers) {
          result[i] = (x.sex.equals("male") ? BigDecimal.valueOf(1) : BigDecimal.valueOf(-1));
          i++;
        }
        break;
      case "smoker":
        i = 0;
        for (Customer x : customers) {
          result[i] = (x.smoker.equals("yes") ? BigDecimal.valueOf(1) : BigDecimal.valueOf(-1));
          i++;
        }
        break;
      case "region":
        int[] temp = new int[] { 0, 0, 0, 0 };
        for (Customer x : customers) {
          // 0 = northwest
          // 1 = northeast
          // 2 = southwest
          // 3 = southeast
          switch (x.region) {
            case "northwest":
              temp[0]++;
              break;
            case "northeast":
              temp[1]++;
              break;
            case "southwest":
              temp[2]++;
              break;
            case "southeast":
              temp[3]++;
              break;
          }
        }
        for (int j = 0; j < 4; j++) {
          result[j] = BigDecimal.valueOf(temp[j]);
        }
        break;
      default:
        System.out.println("That's not a valid option");
    }
    return result;

  }

  private static ArrayList<Customer> sortCustomers(ArrayList<Customer> customers) {
    System.out.println(
        "Which Attribute you want to sort after?\n"
            + "id, age, sex, bmi, children, smoker, region, charges");
    String attributeToBeFiltered = InputHandler.getLine();
    while (!attributeToBeFiltered.matches("^(id|age|sex|bmi|children|smoker|region|charges)$")) {
      System.out.println("That's not a valid attribute!");
      attributeToBeFiltered = InputHandler.getLine();
    }
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
      default:
        System.out.println("That's not a valid option");
    }
    return customers;
  }
}
