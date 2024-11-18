import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        // Чтение строки из файла input.txt
        String input = new String(Files.readAllBytes(Paths.get("input.txt")));

        // Вызов методов для выполнения заданий
        String result1 = removeParenthesesContent(input);
        String result2 = removeDigitsAfterSecond(result1);
        String result3 = removeLeadingZeros(result2);

        // Запись результатов в выходной файл output.txt
        Files.write(Paths.get("output.txt"), result3.getBytes());
    }

    // 1. Метод, исключающий символы внутри круглых скобок
    public static String removeParenthesesContent(String input) {
        return input.replaceAll("\\([^)]*\\)", "");
    }

    // 2. Метод, удаляющий из каждой группы цифр все, начиная с третьей
    public static String removeDigitsAfterSecond(String input) {
        return input.replaceAll("(\\d{2})\\d+", "$1");
    }

    // 3. Метод, удаляющий незначащие нули из каждой группы цифр
    public static String removeLeadingZeros(String input) {
        // Регулярное выражение для удаления незначащих нулей
        Pattern pattern = Pattern.compile("\\b0+(\\d+)\\b");
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("$1");
    }
}
