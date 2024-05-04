import java.math.BigDecimal;
import java.util.ArrayList;

public class ConsoleInterface {
  public static void mainLoop() {
    ArrayList<Customer> allCustomers = CSVParser.parseCSV();
    boolean wantToExit = false;
    while (true && !wantToExit) {
      System.out.println(InterfaceStrings.mainMenu());
      int taskSelection = UserInput.getLineInt();
      switch (taskSelection) {
        case 1:
          System.out.println(InterfaceStrings.refineOutputQuestion());
          if (UserInput.getLine().equals("n")) {
            break;
          }
          ArrayList<Customer> filteredCustomerList = getfilteredCustomerList(allCustomers, getFilterValues());
          int i = 0;
          System.out.println(Customer.tableHeader());
          for (Customer x : filteredCustomerList) {
            if ((i % 2) == 0) {
              System.out.println("\033[40m" + x.prettyToString() + "\033[0m");
            }
          }
      }
    }
  }

  public static Customer[] getFilterValues() {
    String currentUserInput;
    Customer[] result = new Customer[] { new Customer(), new Customer() };

    result[0].age = -1;
    result[1].age = 1000;

    result[0].sex = true;
    result[1].sex = false;

    result[0].bmi = BigDecimal.valueOf(-1);
    result[1].bmi = BigDecimal.valueOf(1000);

    result[0].children = -1;
    result[1].children = 1000;

    result[0].smoker = true;
    result[1].smoker = false;

    result[0].region = "northwest,northeast,southwest,southeast";

    result[0].charges = BigDecimal.valueOf(-1);
    result[1].charges = BigDecimal.valueOf(1000000000);

    System.out.println("Leave empty open end");

    System.out.println("min age:");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      result[0].age = Integer.parseInt(currentUserInput);
    }
    System.out.println("max age:");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      result[1].age = Integer.parseInt(currentUserInput);
    }

    System.out.println("sex (m/f):");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      if (currentUserInput.equals("m")) {
        result[0].sex = true;
        result[1].sex = true;
      } else {
        result[0].sex = false;
        result[1].sex = false;
      }
    }

    System.out.println("min bmi:");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      result[0].bmi = BigDecimal.valueOf(Double.parseDouble(currentUserInput));
    }
    System.out.println("max bmi:");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      result[1].bmi = BigDecimal.valueOf(Double.parseDouble(currentUserInput));
    }

    System.out.println("min children");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      result[0].children = Integer.parseInt(currentUserInput);
    }
    System.out.println("max children");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      result[1].children = Integer.parseInt(currentUserInput);
    }

    System.out.println("smoker (y/n):");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      if (currentUserInput == "y") {
        result[0].smoker = true;
        result[1].smoker = true;
      } else {
        result[0].smoker = false;
        result[1].smoker = false;
      }
    }
    System.out.println("region(split region with \",\")");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      result[0].region = currentUserInput;
    }

    System.out.println("charges min:");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      result[0].charges = BigDecimal.valueOf(Double.parseDouble(currentUserInput));
    }
    System.out.println("charges max:");
    currentUserInput = UserInput.getLine();
    if (!currentUserInput.isBlank()) {
      result[1].charges = BigDecimal.valueOf(Double.parseDouble(currentUserInput));
    }
    return result;
  }

  public static ArrayList<Customer> getfilteredCustomerList(
      ArrayList<Customer> input, Customer[] filterValues) {
    ArrayList<Customer> result = new ArrayList<Customer>();
    boolean age, sex, bmi, children, smoker, region, charges;
    for (Customer x : input) {
      age = sex = bmi = children = smoker = region = charges = false;
      age = x.age >= filterValues[0].age && x.age <= filterValues[1].age;
      sex = x.sex == filterValues[0].sex || x.sex == filterValues[1].sex;
      bmi = x.bmi.compareTo(filterValues[0].bmi) >= 0 && x.bmi.compareTo(filterValues[1].bmi) <= 0;
      children = x.children >= filterValues[0].children && x.children <= filterValues[1].children;
      smoker = x.smoker == filterValues[0].sex || x.smoker == filterValues[1].smoker;
      for (String s : filterValues[0].region.split(",")) {
        if (s.equals(x.region)) {
          region = true;
        }
      }
      charges = x.charges.compareTo(filterValues[0].charges) >= 0
          && x.charges.compareTo(filterValues[1].charges) <= 0;
      if (age && sex && bmi && children && smoker && region && charges) {
        result.add(x);
      }
    }
    return result;
  }
}
