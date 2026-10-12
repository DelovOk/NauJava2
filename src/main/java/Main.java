public class Main {
    public static void main(String[] args) {
        System.out.println("Задание 1");
        Task1.solveTask1();

        System.out.println("Задание 2");
        Task2.solveTask2();

        System.out.println("Задание 3");
        Task3.solveTask3();

        System.out.println("Задание 4");
        Task4.solveTask4();

        System.out.println("Задание 5");
        Task5.DownloadTask task5 = new Task5.DownloadTask(
                "https://media1.tenor.com/m/88RhOSeBMosAAAAd/cat-funny.gif",
                "cat_drill.gif");
        task5.start();
    }
}
