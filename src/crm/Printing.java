package crm;

import java.util.ArrayList;

public class Printing {
    public static void printMainMenu() {
        System.out.println(new StringBuilder("Plese choose the operation:\n")
                .append("1 - List clients\n")
                .append("2 - Automated report\n")
                .append("3 - Search a customer\n")
                .append("4 - Delete a customer\n")
                .append("5 - Add a customer\n")
                .append("6 - Sort List\n")
                .append("7 - Quit")
                .toString());
    }

    public static void printRefineOutputQuestion() {
        System.out.println("Do you want to refine the selection of Clients? [y/n]");
    }

    public static void printRefinementOptions() {
        System.out.println(
                new StringBuilder(
                        "Please give all properties you wan to filert for.\n")
                        .append("Available properties: age, sex, bmi, children, smoker, region, charges\n")
                        .append("please press <Return> after every property.\n")
                        .append("When you are done with your selection just press <Return> on an empty line")
                        .toString());
    }

    public static void printCustomers(ArrayList<Customer> customers) {
        System.out.println(Customer.tableHeader());
        int i = 0;
        for (Customer currentCustomer : customers) {
            System.out.println(
                    new StringBuilder(i % 2 == 0 ? "\033[100m" : "\033[40m")
                            .append(currentCustomer.prettyToString())
                            .append(i % 2 == 0 ? "\033[0m" : "\033[0m"));
            i++;
        }
    }
}
