import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Task2 {
    public static ArrayList<Double> quickSort(List<Double> arrayList) {
        if (arrayList.size() <= 1) {
            return new ArrayList<>(arrayList);
        }

        Double pivot = arrayList.getFirst();

        List<Double> left = arrayList.stream().filter(x -> x < pivot).toList();
        List<Double> middle = arrayList.stream().filter(x -> x.equals(pivot)).toList();
        List<Double> right = arrayList.stream().filter(x -> x > pivot).toList();

        ArrayList<Double> result = new ArrayList<>(quickSort(left));
        result.addAll(middle);
        result.addAll(quickSort(right));

        return result;
    }

    public static void solveTask2() {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            n = scanner.nextInt();
        }
        while (n < 0);
        ArrayList<Double> arrayList = new ArrayList<>(n);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arrayList.add(random.nextDouble(-100, 100));
        }
        ArrayList<Double> result = quickSort(arrayList);
        System.out.println(arrayList.toString());
        System.out.println(result.toString());
    }
}
