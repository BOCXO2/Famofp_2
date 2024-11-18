// Работу выполнил Опришко Станислав 5 группа
/*Строки текстового файла input.txt состоят из слов, разделенных одним или несколькими пробелами.
Перед первым, а также после последнего слова строки пробелы могут отсутствовать.
Требуется найти слова максимальной и минимальной длины и поменять местами строки, содержащие эти слова.
Если таких слов несколько – брать первые.*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            String maxWord = "";
            String minWord = "";
            int maxLength = 0;
            int minLength = Integer.MAX_VALUE;
            int maxLineNumber = 0;
            int minLineNumber = 0;

            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String[] words = line.split("\\s+");
                for (String word : words) {
                    int length = word.length();
                    if (length > maxLength) {
                        maxLength = length;
                        maxWord = word;
                        maxLineNumber = lineNumber;
                    }
                    if (length < minLength) {
                        minLength = length;
                        minWord = word;
                        minLineNumber = lineNumber;
                    }
                }
            }

            // Чтение и запись строк из файла
            BufferedReader file = new BufferedReader(new FileReader("input.txt"));
            String[] lines = new String[lineNumber];
            for (int i = 0; i < lineNumber; i++) {
                lines[i] = file.readLine();
            }
            file.close();

            // Меняем местами строки с минимальным и максимальным словами
            String temp = lines[minLineNumber - 1];
            lines[minLineNumber - 1] = lines[maxLineNumber - 1];
            lines[maxLineNumber - 1] = temp;

            BufferedWriter outputFile = new BufferedWriter(new FileWriter("output.txt"));
            for (String updatedLine : lines) {
                outputFile.write(updatedLine + "\n");
            }

            System.out.println("Строки с минимальным и максимальным словами поменялись местами в файле output.txt");

            outputFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}