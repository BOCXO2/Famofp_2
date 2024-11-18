// 25. Определить, становится ли симметричной
// (относительно главной диагонали) заданная матрица после замены на число 0 каждого локального максимума.
//* Работу выполнил Опришко Станислав 5 группа 2 курс.(в данной задаче, уже поработал с вводом данных через консоль
// а не через рандомайзер.)
import java.util.Scanner; // Использовал библиотеку Scanner, для считывания ввода с клавиатуры данных.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размерность матрицы N: ");
        int N = scanner.nextInt();
        int[][] a = new int[N][N];

        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = scanner.nextInt();
            }
        }

        // Заменяем на число 0 каждый локальный максимум
        int j_max;
        for (int i = 0; i < N; i++) {
            j_max = 0;
            for (int j = 1; j < N; j++) {
                if (a[i][j_max] < a[i][j]) {
                    j_max = j;
                }
            }
            a[i][j_max] = 0;
        }

        // Проверяем симметричность (относительно главной диагонали)
        boolean fl = true;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i][j] != a[j][i]) {
                    fl = false;
                }
            }
        }

        if (fl) {
            System.out.println("Матрица симметрична относительно главной диагонали");
        } else {
            System.out.println("Матрица не симметрична относительно главной диагонали");
        }

        scanner.close();
    }
}