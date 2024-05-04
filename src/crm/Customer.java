package crm;

import java.math.BigDecimal;

public class Customer {
    public int age;
    public int children;
    public boolean sex;
    public boolean smoker;
    public BigDecimal bmi;
    public BigDecimal charges;
    public String region;

    public Customer() {
    }

    public Customer(
            int age,
            int children,
            boolean sex,
            boolean smoker,
            BigDecimal bmi,
            BigDecimal charges,
            String region) {
        this.age = age;
        this.children = children;
        this.sex = sex;
        this.smoker = smoker;
        this.bmi = bmi;
        this.charges = charges;
        this.region = region;
    }

    public void setAgeInteractive() {
        age = UserInput.getLineInt();
    }

    public void setChildrenInteractive() {
        children = UserInput.getLineInt();
    }

    public void setSexInteractive() {
        sex = (UserInput.getLine().equals("m") ? true : false);
    }

    public void setSmokerInteractive() {
        sex = (UserInput.getLine().equals("y") ? true : false);
    }

    public void setBmiInteractive() {
        bmi = UserInput.getLineBigDecimal();
    }

    public void setChargesInteractive() {
        charges = UserInput.getLineBigDecimal();
    }

    public void setRegionInteractive() {
        region = UserInput.getLine();
    }

    @Override
    public String toString() {
        return new StringBuilder(age)
                .append(", ")
                .append(sex)
                .append(", ")
                .append(bmi)
                .append(", ")
                .append(children)
                .append(", ")
                .append(smoker)
                .append(", ")
                .append(region)
                .append(", ")
                .append(charges)
                .toString();
    }

    public String prettyToString() {
        return new StringBuilder("| ")
                .append(String.format("%-2s", age))
                .append(" | ")
                .append(sex ? "Male  " : "Female")
                .append(" | ")
                .append(String.format("%-6s", bmi))
                .append(" | ")
                .append(String.format("%-6s", children))
                .append(" | ")
                .append(smoker ? "yes " : "no  ")
                .append(" | ")
                .append(String.format("%-9s", region))
                .append(" | ")
                .append(String.format("%-11s", charges))
                .append(" |")
                .toString();
    }

    public static String tableHeader() {
        return "\033[37;40m|age |sex     |bmi     |children|smoker|region     |charges      |\033[0m";
    }
}
