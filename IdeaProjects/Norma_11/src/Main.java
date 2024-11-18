// 11. Определить норму строк заданной матрицы.
// Работу выполнил Опришко Станислав 5 группа 2 курс
// Матрица создается через рандомайзер, а не при вводе определенной матрицы.

import java.util.Arrays;
import java.util.Random;

public class Main {
    final static Random rand = new Random();

    public static void main(String[] args) {
        int[][] arr = createArray(7, 8, -100, 100);
        toConsole(arr);
        System.out.println("Norm ||A||m = " + maxCols(sumRows(arr)));
    }

    public static int[][] createArray(int N, int M, int a, int b) {
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = rand.nextInt((b - a) + 1) + a;
            }
        }
        return arr;
    }

    public static int[] sumRows(int[][] arr) {
        int[] array = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                array[i] += Math.abs(arr[i][j]);
            }
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

    public static void toConsole(int[][] arr) {
        for (int[] ints : arr) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static int maxCols(int[] arr) {
        int max = 0;
        for (int val : arr) {
            if (val > max) max = val;
        }
        return max;
    }
}