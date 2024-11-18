//39. Построить результат сглаживания заданной вещественной матрицы.
// Работу выполнил Опришко Станислав 5 группа 2 курс
// В данной работе поработал с рандомайзером и консолью с помощью библиотеки Scanner.
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();

        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        float[][] originalMatrix = new float[rows][cols]; // Исходная матрица
        float[][] smoothedMatrix = new float[rows][cols]; // Матрица после сглаживания
        int i, j, k, l; // Переменные для циклов
        Random random = new Random(); // Генератор случайных чисел

        // Заполнение исходной матрицы случайными числами
        System.out.println("Оригинальная матрица: ");
        for (i = 0; i < rows; i++) {
            for (j = 0; j < cols; j++) {
                originalMatrix[i][j] = random.nextInt(10);
                System.out.print(originalMatrix[i][j] + " ");
            }
            System.out.println();
        }

        // Сглаживание исходной матрицы
        for (i = 0; i < rows; i++) {
            for (j = 0; j < cols; j++) {
                smoothedMatrix[i][j] = 0; // Инициализация значения в сглаженной матрице
                int neighborCount = 0; // Счетчик соседей
                for (k = i - 1; k < i + 2; k++) {
                    if (k < 0 || k >= rows) continue;
                    for (l = j - 1; l < j + 2; l++) {
                        if ((l < 0 || l >= cols) || (k != i && l != j)) continue;
                        smoothedMatrix[i][j] += originalMatrix[k][l];
                        neighborCount++;
                    }
                }
                smoothedMatrix[i][j] /= neighborCount; // Вычисление среднего значения соседей
            }
        }
        // Вывод сглаженной матрицы
        System.out.println();
        System.out.println("Сглаженная матрица: ");
        for (i = 0; i < rows; i++) {
            for (j = 0; j < cols; j++) {
                System.out.print(smoothedMatrix[i][j] + " ");
            }
            System.out.println();
        }

        // Вычисление суммы элементов сглаженной матрицы ниже главной диагонали
        float sumBelowDiagonal = 0;
        for (i = 0; i < rows; i++) {
            for (j = 0; j < i; j++) {
                sumBelowDiagonal += smoothedMatrix[i][j];
            }
        }
        System.out.println("\nСумма элементов ниже главной диагонали: " + sumBelowDiagonal);

        scanner.close();
    }
}