//Работу выполнил Опришко Станислав 5 группа 1 курс
/*Задана строка, слова в которой состоят из букв латинского алфавита и десятичных цифр.
 Остальные символы – разделители между словами. Получить новую строку, выполняя в заданной строке замены по следующему правилу.
 Все слова, имеющие длину менее 5 символов и состоящие только из цифр, заменяются словами,
 получаемыми из исходных путём дописывания ведущих нулей до получения длины слова, равной 5.
 Слова в исходной строке разделяются некоторым множеством разделителей.
 Слова в новой строке должны разделяться ровно одним пробелом.*/

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку: ");
        String input = scanner.nextLine();
        String output = processString(input);
        System.out.println("Результат замены: ");
        System.out.println(output);
    }

    public static String processString(String input) {
        StringBuilder result = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(input, " \t\n\r\f,.:;");

        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            if (word.matches("\\d+") && word.length() < 5) {
                int leadingZeros = 5 - word.length();
                StringBuilder newWord = new StringBuilder();
                for (int i = 0; i < leadingZeros; i++) {
                    newWord.append("0");
                }
                newWord.append(word);
                result.append(newWord).append(" ");
            } else {
                result.append(word).append(" ");
            }
        }

        return result.toString().trim();
    }
}