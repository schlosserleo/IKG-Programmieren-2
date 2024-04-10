import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

public class Aufgabe2 {
    public static void mainLoop() {
        boolean wantToExit = false;
        int index;
        BigDecimal value;
        BigDecimal[] userArray = createArrayFromUser();
        while (true && !wantToExit) {
            System.out.println(
                    "Ihr Array : "
                            + Arrays.toString(userArray)
                            + "\n\n"
                            + "Sie haben nun folgende optionen:\n"
                            + "1: Statistische Informationen erhalten\n"
                            + "2: Index von bestimmtem Wert bestimmen\n"
                            + "3: Entferne ein Element\n"
                            + "4: Füge ein Element hinzu\n"
                            + "5: Beenden\n");
            int choice = UserInput.getLineInt();
            switch (choice) {
                case 1:
                    System.out.println(
                            "Maximalwert: "
                                    + max(userArray)
                                    + "\n"
                                    + "Minimalwert: "
                                    + min(userArray)
                                    + "\n"
                                    + "Durchschnitt: "
                                    + average(userArray)
                                    + "\n"
                                    + "Standardabweichung: "
                                    + stdDeviation(userArray));
                    break;
                case 2:
                    System.out.println("Von welchem Wert wollen sie den Index wissen (first occurance)");
                    value = UserInput.getLineBigDecimal();
                    System.out.println(
                            "Der Wert " + value + " ist an Stelle " + getIndex(userArray, value) + " zu finden");
                    break;
                case 3:
                    System.out.println("Geben sie den Index des Elementes an das sie entfernen wollen");
                    index = UserInput.getLineInt();
                    userArray = createArrayWithElementRemoved(userArray, index);
                    break;
                case 4:
                    System.out.println("Geben sie den Index an an dem sie ihr Element einfügen wollen");
                    index = UserInput.getLineInt();
                    System.out.println("Geben sie den Wert des Elements an");
                    value = UserInput.getLineBigDecimal();
                    userArray = createArrayWithElementAdded(userArray, index, value);
                    break;
                case 5:
                    wantToExit = true;
                    break;
            }
        }
    }

    public static int getIndex(BigDecimal[] input, BigDecimal value) {
        int i = 0;
        for (BigDecimal x : input) {
            if (x.equals(value)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static BigDecimal[] createArrayWithElementRemoved(BigDecimal[] input, int index) {
        int oldArrayLen = input.length;
        BigDecimal[] result = new BigDecimal[oldArrayLen - 1];
        for (int i = 0, k = 0; i < oldArrayLen; i++) {
            if (i == index) {
                continue;
            }
            result[k++] = input[i];
        }
        return result;
    }

    public static BigDecimal[] createArrayWithElementAdded(
            BigDecimal[] input, int index, BigDecimal value) {
        int oldArrayLen = input.length;
        BigDecimal[] result = new BigDecimal[oldArrayLen + 1];
        for (int i = 0, k = 0; i < oldArrayLen; i++) {
            if (i == index) {
                result[i] = value;
                k = k + 2;
                result[i + 1] = input[i];
                continue;
            }
            result[k] = input[i];
            k++;
        }
        if (index == oldArrayLen) {
            result[index] = value;
        }

        return result;
    }

    public static BigDecimal[] createArrayFromUser() {
        BigDecimal[] result;
        System.out.println("Wie groß Soll ihr Array werden?");
        int arraySize = UserInput.getLineInt();
        result = new BigDecimal[arraySize];
        System.out.println("Füllen sie nun das Array. Drücken sie nach jeder Zahl enter");
        for (int i = 0; i < arraySize; i++) {
            result[i] = UserInput.getLineBigDecimal();
        }
        return result;
    }

    public static BigDecimal average(BigDecimal[] input) {
        BigDecimal array_len = BigDecimal.valueOf(input.length);
        BigDecimal sum = BigDecimal.valueOf(0);

        for (BigDecimal x : input) {
            sum = sum.add(x);
        }

        return sum.divide(array_len, MathContext.DECIMAL128);
    }

    public static BigDecimal stdDeviation(BigDecimal[] input) {
        BigDecimal average = average(input);
        BigDecimal numerator = BigDecimal.valueOf(0);

        for (BigDecimal x : input) {
            numerator = numerator.add((x.subtract(average).pow(2)));
        }

        BigDecimal sqrtContent = numerator.divide(BigDecimal.valueOf(input.length), MathContext.DECIMAL128);
        return sqrtContent.sqrt(MathContext.DECIMAL128);
    }

    public static BigDecimal max(BigDecimal[] input) {
        return Arrays.stream(input).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }

    public static BigDecimal min(BigDecimal[] input) {
        return Arrays.stream(input).min(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
    }
}
