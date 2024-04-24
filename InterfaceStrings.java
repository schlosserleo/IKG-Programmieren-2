public class InterfaceStrings {
    public static String mainMenu() {
        return "Please choose the operation:\n"
                + "1 - List clients\n"
                + "2 - Automated report\n"
                + "3 - Search a customer\n"
                + "4 - Delete a customer\n"
                + "5 - Add a customer\n"
                + "6 - Sort by ID\n"
                + "7 - Quit";
    }

    public static String refineOutputQuestion() {
        return "Do you want to refine the selection of Clients? [y/n]";
    }

    public static String clientListRefinementOptions() {
        // return "Please give all properties you wan to filert for.\n"
        // + "Available properties: age, sex, bmi, children, smoker, region, charges\n"
        // + "Please press <Return> after every property.\n"
        // + "When you are done with your selection just press <Return> on an empty
        // line";
        return "";
    }
}
