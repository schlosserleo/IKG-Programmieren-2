import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {
  public static ArrayList<Customer> parseCSV() {
    ArrayList<Customer> customerArrayList = new ArrayList<>();

    File file = new File(CSVParser.class.getClassLoader().getResource("insurance.csv").getFile());
    try {
      Scanner inputStream = new Scanner(file);
      inputStream.nextLine();
      String line;
      String[] values;
      while (inputStream.hasNextLine()) {
        line = inputStream.nextLine();
        values = line.split(",");
        Customer currentCustomer = new Customer();
        currentCustomer.age = Integer.parseInt(values[0]);
        currentCustomer.children = Integer.parseInt(values[3]);
        currentCustomer.bmi = BigDecimal.valueOf(Double.parseDouble(values[2]));
        currentCustomer.charges = BigDecimal.valueOf(Double.parseDouble(values[6]));
        currentCustomer.region = values[5];
        if (values[1].equals("male")) {
          currentCustomer.sex = true;
        } else {
          currentCustomer.sex = false;
        }
        if (values[4].equals("yes")) {
          currentCustomer.smoker = true;
        } else {
          currentCustomer.smoker = false;
        }
        customerArrayList.add(currentCustomer);
      }
      inputStream.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return customerArrayList;
  }
}
