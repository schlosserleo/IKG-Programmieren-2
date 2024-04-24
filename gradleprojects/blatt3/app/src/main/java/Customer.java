import java.math.BigDecimal;

public class Customer {
  public int age;
  public boolean sex;
  public BigDecimal bmi;
  public int children;
  public boolean smoker;
  public String region;
  public BigDecimal charges;

  @Override
  public String toString() {
    return age + ", " + sex + ", " + bmi + ", " + children + ", " + smoker + ", " + region + ", "
        + charges;
  }

  public String prettyToString() {
    String ageString = String.format("%-2s", age);
    String bmiString = String.format("%-6s", bmi);
    String childrenString = String.format("%-6s", children);
    String regionString = String.format("%-9s", region);
    String chargesString = String.format("%-11s", charges);
    String sexString;
    String smokerString;
    if (sex) {
      sexString = "Male  ";
    } else {
      sexString = "Female";
    }
    if (smoker) {
      smokerString = "yes ";
    } else {
      smokerString = "no  ";
    }
    return "| "
        + ageString
        + " | "
        + sexString
        + " | "
        + bmiString
        + " | "
        + childrenString
        + " | "
        + smokerString
        + " | "
        + regionString
        + " | "
        + chargesString
        + " |";
  }

  public static String tableHeader() {
    return "\033[37;40m|age |sex     |bmi     |children|smoker|region     |charges      |\033[0m";
  }
}
