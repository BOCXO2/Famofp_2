import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        // Чтение исходного Java-кода из файла input.java
        String code = new String(Files.readAllBytes(Paths.get("input.java")));

        // Удаление всех многострочных комментариев (/* ... */)
        String noMultilineComments = code.replaceAll("/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/", "");

        // Удаление всех однострочных комментариев (//...)
        String noComments = noMultilineComments.replaceAll("//.*", "");

        // Запись очищенного кода в выходной файл output.java
        Files.write(Paths.get("output.java"), noComments.getBytes());
    }
}
