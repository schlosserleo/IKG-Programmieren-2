package crm;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;

public class Statistics {
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
        int arraySize = InputHandler.getLineInt();
        result = new BigDecimal[arraySize];
        System.out.println("Füllen sie nun das Array. Drücken sie nach jeder Zahl enter");
        for (int i = 0; i < arraySize; i++) {
            result[i] = InputHandler.getLineBigDecimal();
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

    public static BigDecimal[] regionStatistics(BigDecimal[] regionCounts) {
        BigDecimal[] result = new BigDecimal[4];
        // 0 = northwest
        // 1 = northeast
        // 2 = southwest
        // 3 = southeast
        BigDecimal allRegionCount = BigDecimal.valueOf(0);
        for (BigDecimal x : regionCounts) {
            allRegionCount = allRegionCount.add(x);
        }
        for (int i = 0; i < 4; i++) {
            result[i] = regionCounts[i].divide(allRegionCount, MathContext.DECIMAL128)
                    .multiply(BigDecimal.valueOf(100));
        }
        return result;
    }
}
