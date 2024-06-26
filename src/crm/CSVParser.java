package crm;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVParser {
  public static ArrayList<Customer> parseCSV(String filename) {
    ArrayList<Customer> customerArrayList = new ArrayList<>();
    File file = new File(CSVParser.class.getResource(filename).getPath());
    try {
      Scanner inputStream = new Scanner(file);
      inputStream.nextLine();
      String line;
      String[] values;
      int id = 1;
      while (inputStream.hasNextLine()) {
        line = inputStream.nextLine();
        values = line.split(",");
        Customer currentCustomer = new Customer(
            id,
            Integer.parseInt(values[0]),
            Integer.parseInt(values[3]),
            values[1],
            values[4],
            BigDecimal.valueOf(Double.parseDouble(values[2])),
            BigDecimal.valueOf(Double.parseDouble(values[6])),
            values[5]);
        customerArrayList.add(currentCustomer);
        id++;
      }
      inputStream.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return customerArrayList;
  }
}
