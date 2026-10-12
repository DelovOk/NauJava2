import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Task1 {
    public static void solveTask1() {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            n = scanner.nextInt();
        }
        while (n < 0);
        int[] array = new Random().ints(n, -100, 100).toArray();
        System.out.println(Arrays.toString(array));
        if (n == 0) return;
        int min = Math.abs(array[0]);
        for (int i = 0; i < n; i++) {
            min = Math.min(min, Math.abs(array[i]));
        }
        System.out.println(min);
    }
}
