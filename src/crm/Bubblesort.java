package crm;

public class Bubblesort {
    public static int[] bubbleSort(int[] input) {
        int n = input.length;
        for (int i = 0; i < n - 1; i++) {
            for (int k = 0; k < n - 1; k++) {
                if (input[k] > input[k + 1]) {
                    int temp = input[k];
                    input[k] = input[k + 1];
                    input[k + 1] = temp;
                }
            }
        }
        return input;
    }
}
