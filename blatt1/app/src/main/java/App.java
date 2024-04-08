import java.lang.Math;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        int[] test = new int[] { 5, 2 };
        double average = average(test);
        double stdDeviation = stdDeviation(test);
        int max = max(test);
        int min = min(test);
        System.out.println(average);
        System.out.println(stdDeviation);
        System.out.println(max);
        System.out.println(min);
    }

    public static double average(int[] input) {
        double array_len = input.length;
        double sum = 0;

        for (int x : input) {
            sum += x;
        }

        return sum / array_len;
    }

    public static double stdDeviation(int[] input) {
        double average = average(input);
        double numerator = 0;

        for (int x : input) {
            numerator += Math.pow(x - average, 2);
        }

        return Math.sqrt(numerator / ((double) input.length - 1));
    }

    public static int max(int[] input) {
        return Arrays.stream(input).max().getAsInt();
    }

    public static int min(int[] input) {
        return Arrays.stream(input).min().getAsInt();
    }

}
