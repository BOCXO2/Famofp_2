import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    // Формат для преобразования дат
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        BufferedReader fileIn = new BufferedReader(new FileReader("input.csv"));
        BufferedWriter loggerTxt = new BufferedWriter(new FileWriter("logfile.txt", true));
        BufferedWriter xmlWrite = new BufferedWriter(new FileWriter("out.xml"));

        List<Company> companies = fileIn.lines()
                .map(line -> new Company(line, ";"))
                .collect(Collectors.toList());
        fileIn.close();

        loggerTxt.write("Запуск: " + formatter.format(date));
        loggerTxt.newLine();

        // Обработка запросов
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите тип запроса: \n1 - Поиск по краткому наименованию\n2 - По отрасли\n3 - По виду деятельности\n4 - По дате основания\n5 - По численности сотрудников");
            int queryType = scanner.nextInt();
            scanner.nextLine();

            List<Company> results = new ArrayList<>();
            String queryLog = "";

            switch (queryType) {
                case 1:
                    System.out.println("Введите краткое наименование компании: ");
                    String shortTitle = scanner.nextLine().toLowerCase();
                    results = companies.stream()
                            .filter(c -> c.getShortTitle().equalsIgnoreCase(shortTitle))
                            .collect(Collectors.toList());
                    queryLog = "Поиск по краткому наименованию: " + shortTitle;
                    break;
                case 2:
                    System.out.println("Введите отрасль: ");
                    String branch = scanner.nextLine().toLowerCase();
                    results = companies.stream()
                            .filter(c -> c.getBranch().equalsIgnoreCase(branch))
                            .collect(Collectors.toList());
                    queryLog = "Поиск по отрасли: " + branch;
                    break;
                case 3:
                    System.out.println("Введите вид деятельности: ");
                    String activity = scanner.nextLine().toLowerCase();
                    results = companies.stream()
                            .filter(c -> c.getActivity().equalsIgnoreCase(activity))
                            .collect(Collectors.toList());
                    queryLog = "Поиск по виду деятельности: " + activity;
                    break;
                case 4:
                    System.out.println("Введите дату основания (от и до) в формате yyyy-MM-dd: ");
                    LocalDate dateFrom = LocalDate.parse(scanner.nextLine(), DATE_FORMAT);
                    LocalDate dateTo = LocalDate.parse(scanner.nextLine(), DATE_FORMAT);
                    results = companies.stream()
                            .filter(c -> c.getDateFoundation() != null && !c.getDateFoundation().isBefore(dateFrom) && !c.getDateFoundation().isAfter(dateTo))
                            .collect(Collectors.toList());
                    queryLog = "Поиск по дате основания: " + dateFrom + " - " + dateTo;
                    break;

                case 5:
                    System.out.println("Введите численность сотрудников (от и до): ");
                    int countFrom = scanner.nextInt();
                    int countTo = scanner.nextInt();
                    results = companies.stream()
                            .filter(c -> c.getCountEmployees() >= countFrom && c.getCountEmployees() <= countTo)
                            .collect(Collectors.toList());
                    queryLog = "Поиск по численности сотрудников: " + countFrom + " - " + countTo;
                    break;
            }

            loggerTxt.write(queryLog + ", найдено: " + results.size());
            loggerTxt.newLine();
            results.forEach(System.out::println);
            saveToXml(results, xmlWrite);
        }

        loggerTxt.close();
    }

    private static void saveToXml(List<Company> companies, BufferedWriter xmlWrite) throws IOException {
        xmlWrite.write("<?xml version=\"1.0\"?>\n<Companies>\n");
        for (Company company : companies) {
            xmlWrite.write(company.toXml());
        }
        xmlWrite.write("</Companies>");
        xmlWrite.close();
    }
}
