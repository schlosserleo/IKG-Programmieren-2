package crm;

import java.math.BigDecimal;

public class Customer {
    public int id;
    public int age;
    public int children;
    public String sex;
    public String smoker;
    public BigDecimal bmi;
    public BigDecimal charges;
    public String region;

    public Customer() {
    }

    public Customer(
            int id,
            int age,
            int children,
            String sex,
            String smoker,
            BigDecimal bmi,
            BigDecimal charges,
            String region) {
        this.id = id;
        this.age = age;
        this.children = children;
        this.sex = sex;
        this.smoker = smoker;
        this.bmi = bmi;
        this.charges = charges;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getChildren() {
        return children;
    }

    public String getSex() {
        return sex;
    }

    public String getSmoker() {
        return smoker;
    }

    public BigDecimal getBmi() {
        return bmi;
    }

    public BigDecimal getCharges() {
        return charges;
    }

    public String getRegion() {
        return region;
    }

    public void setAgeInteractive() {
        age = InputHandler.getBlankOrInt(age);
    }

    public void setChildrenInteractive() {
        children = InputHandler.getBlankOrInt(children);
    }

    public void setSexInteractive() {
        String userInput = InputHandler.getLine();
        if (userInput.isBlank()) {
            sex = "any";
            return;
        }
        if (userInput.matches("^[mf]$")) {
            sex = (userInput.equals("m") ? "male" : "female");
        }
    }

    public void setSmokerInteractive() {

        String userInput = InputHandler.getLine();
        if (userInput.isBlank()) {
            smoker = "any";
            return;
        }
        if (userInput.matches("^[yn]$")) {
            smoker = (userInput.equals("y") ? "yes" : "no");
        }
    }

    public void setBmiInteractive() {
        bmi = InputHandler.getBlankOrBigDecimal(bmi);
    }

    public void setChargesInteractive() {
        charges = InputHandler.getBlankOrBigDecimal(charges);
    }

    public void setRegionInteractive() {
        String userInput = InputHandler.getLine();
        if (!userInput.isBlank()) {
            region = userInput;
        }
    }

    public void updateCustomer(String attribute) {
        switch (attribute) {
            case "age":
                setAgeInteractive();
                break;
            case "children":
                setChildrenInteractive();
                break;
            case "sex":
                setSexInteractive();
                break;
            case "smoker":
                setSmokerInteractive();
                break;
            case "bmi":
                setBmiInteractive();
                break;
            case "charges":
                setChargesInteractive();
                break;
            case "region":
                setRegionInteractive();
                break;
        }
    }

    @Override
    public String toString() {
        return new StringBuilder(id)
                .append(", ")
                .append(age)
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
                .append(String.format("%-4s", id))
                .append(" | ")
                .append(String.format("%-2s", age))
                .append(" | ")
                .append(String.format("%-6s", sex))
                .append(" | ")
                .append(String.format("%-6s", bmi))
                .append(" | ")
                .append(String.format("%-6s", children))
                .append(" | ")
                .append(String.format("%-4s", smoker))
                .append(" | ")
                .append(String.format("%-9s", region))
                .append(" | ")
                .append(String.format("%-11s", charges))
                .append(" |")
                .toString();
    }

    public static String tableHeader() {
        return "\033[37;40m|id    | age |sex     |bmi     |children|smoker|region     |charges      |\033[0m";
    }
}
