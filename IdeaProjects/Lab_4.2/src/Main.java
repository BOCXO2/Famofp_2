import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // Чтение HTML из файла input1.html
        List<String> htmlLines = Files.readAllLines(Paths.get("input1.html"));

        // Чтение фрагментов из файла input2.in
        List<String> searchFragments = Files.readAllLines(Paths.get("input2.in"));
        List<String> fragmentsToSearch = new ArrayList<>();
        for (String line : searchFragments) {
            String[] fragments = line.split(";");
            fragmentsToSearch.addAll(Arrays.asList(fragments));
        }

        // Задание 1: Извлечение тегов и запись в output1.out
        List<String> tags = extractTags(htmlLines);
        writeTagsToFile(tags, "output1.out");

        // Задание 2: Поиск фрагментов и запись результатов в output2.out
        List<Integer> fragmentLines = findFragments(htmlLines, fragmentsToSearch);
        writeFragmentLinesToFile(fragmentLines, "output2.out");

        // Задание 3: Поиск фрагментов, которые не были найдены
        List<String> notFoundFragments = findNotFoundFragments(fragmentsToSearch, fragmentLines);
        writeNotFoundFragmentsToFile(notFoundFragments, "output3.out");
    }

    // Функция для извлечения тегов из HTML
    private static List<String> extractTags(List<String> htmlLines) {
        Set<String> tagSet = new HashSet<>();
        Pattern tagPattern = Pattern.compile("<[^>]+>");
        for (String line : htmlLines) {
            Matcher matcher = tagPattern.matcher(line);
            while (matcher.find()) {
                tagSet.add(matcher.group().toLowerCase());
            }
        }
        // Сортировка по длине тегов
        List<String> tags = new ArrayList<>(tagSet);
        tags.sort(Comparator.comparingInt(String::length));
        return tags;
    }

    // Запись тегов в файл
    private static void writeTagsToFile(List<String> tags, String fileName) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (String tag : tags) {
                writer.write(tag);
                writer.newLine();
            }
        }
    }

    // Поиск фрагментов текста в HTML
    private static List<Integer> findFragments(List<String> htmlLines, List<String> fragmentsToSearch) {
        List<Integer> resultLines = new ArrayList<>();
        StringBuilder htmlContent = new StringBuilder();
        for (String line : htmlLines) {
            htmlContent.append(line.toLowerCase()).append("\n");
        }
        String htmlText = htmlContent.toString().replaceAll("<[^>]+>", "");  // Удаляем все теги

        for (String fragment : fragmentsToSearch) {
            int lineFound = -1;
            String lowerFragment = fragment.toLowerCase();
            for (int i = 0; i < htmlLines.size(); i++) {
                String line = htmlLines.get(i).toLowerCase().replaceAll("<[^>]+>", "");
                if (line.contains(lowerFragment)) {
                    lineFound = i;
                    break;
                }
            }
            resultLines.add(lineFound);
        }
        return resultLines;
    }

    // Запись найденных строк в файл
    private static void writeFragmentLinesToFile(List<Integer> fragmentLines, String fileName) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (Integer line : fragmentLines) {
                writer.write(String.valueOf(line));
                writer.newLine();
            }
        }
    }

    // Поиск фрагментов, которые не были найдены
    private static List<String> findNotFoundFragments(List<String> fragments, List<Integer> fragmentLines) {
        List<String> notFoundFragments = new ArrayList<>();
        for (int i = 0; i < fragments.size(); i++) {
            if (fragmentLines.get(i) == -1) {
                notFoundFragments.add(fragments.get(i));
            }
        }
        return notFoundFragments;
    }

    // Запись ненайденных фрагментов в файл
    private static void writeNotFoundFragmentsToFile(List<String> notFoundFragments, String fileName) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName))) {
            for (String fragment : notFoundFragments) {
                writer.write(fragment);
                writer.newLine();
            }
        }
    }
}
