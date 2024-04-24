import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Aufgabe3 {
  public static BigDecimal[] parseCSV(String selection) {
    ArrayList<BigDecimal> age = new ArrayList<BigDecimal>();
    ArrayList<BigDecimal> sex = new ArrayList<BigDecimal>();
    ArrayList<BigDecimal> bmi = new ArrayList<BigDecimal>();
    ArrayList<BigDecimal> children = new ArrayList<BigDecimal>();
    ArrayList<BigDecimal> smoker = new ArrayList<BigDecimal>();
    ArrayList<BigDecimal> charges = new ArrayList<BigDecimal>();
    File file = new File(Aufgabe3.class.getClassLoader().getResource("insurance.csv").getFile());
    try {
      Scanner inputStream = new Scanner(file);
      inputStream.nextLine();
      String line;
      String[] values;
      while (inputStream.hasNextLine()) {
        line = inputStream.nextLine();
        values = line.split(",");
        age.add(BigDecimal.valueOf(Double.parseDouble(values[0])));
        children.add(BigDecimal.valueOf(Double.parseDouble(values[3])));
        bmi.add(BigDecimal.valueOf(Double.parseDouble(values[2])));
        charges.add(BigDecimal.valueOf(Double.parseDouble(values[6])));
        if (values[1].equals("male")) {
          sex.add(BigDecimal.valueOf(1));
        } else {
          sex.add(BigDecimal.valueOf(0));
        }
        if (values[4].equals("yes")) {
          smoker.add(BigDecimal.valueOf(1));
        } else {
          smoker.add(BigDecimal.valueOf(0));
        }
      }
      inputStream.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    BigDecimal[] result = new BigDecimal[age.size()];
    switch (selection) {
      case "age":
        result = age.toArray(new BigDecimal[0]);
        break;
      case "sex":
        result = sex.toArray(new BigDecimal[0]);
        break;
      case "bmi":
        result = bmi.toArray(new BigDecimal[0]);
        break;
      case "children":
        result = children.toArray(new BigDecimal[0]);
        break;
      case "smoker":
        result = smoker.toArray(new BigDecimal[0]);
        break;
      case "charges":
        result = charges.toArray(new BigDecimal[0]);
        break;
      default:
        break;
    }
    return result;
  }
}
