import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            String line = reader.readLine();

            if (line != null) {
                StringBuffer output = new StringBuffer();
                StringTokenizer tokenizer = new StringTokenizer(line, "[^a-zA-Z0-9]+");

                while (tokenizer.hasMoreTokens()) {
                    String word = tokenizer.nextToken();
                    if (word.length() < 5 && word.matches("\\d+")) {
                        word = String.format("%05d", Integer.parseInt(word));
                    }
                    output.append(word).append(" ");
                }

                writer.write(output.toString().trim());
            }

            reader.close();
            writer.close();

            System.out.println("Замены выполнены и записаны в файл output.txt.");
        } catch (IOException e) {
            System.err.println("Произошла ошибка при чтении/записи файлов.");
            e.printStackTrace();
        }
    }
}