import com.google.gson.Gson;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Company {
    private String name;
    private String shortTitle;
    private LocalDate dateUpdate;
    private String address;
    private LocalDate dateFoundation;
    private int countEmployees;
    private String auditor;
    private String phone;
    private String email;
    private String branch;
    private String activity;
    private String internetAddress;

    public Company(String name, String shortTitle, LocalDate dateUpdate, String address, LocalDate dateFoundation,
                   int countEmployees, String auditor, String phone, String email, String branch,
                   String activity, String internetAddress) {
        this.name = name;
        this.shortTitle = shortTitle;
        this.dateUpdate = dateUpdate;
        this.address = address;
        this.dateFoundation = dateFoundation;
        this.countEmployees = countEmployees;
        this.auditor = auditor;
        this.phone = phone;
        this.email = email;
        this.branch = branch;
        this.activity = activity;
        this.internetAddress = internetAddress;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public String getBranch() {
        return branch;
    }

    public String getActivity() {
        return activity;
    }

    public LocalDate getDateFoundation() {
        return dateFoundation;
    }

    public int getCountEmployees() {
        return countEmployees;
    }
}

public class Main {
    private List<Company> companies = new ArrayList<>();

    // Метод для чтения CSV-файла
    public void loadCompanies(String filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");
                Company company = parseCompany(fields);
                companies.add(company);
            }
        }
    }

    // Парсинг одной записи
    private Company parseCompany(String[] fields) {
        return new Company(
                fields[0],
                fields[1],
                LocalDate.parse(fields[2]),
                fields[3],
                LocalDate.parse(fields[4]),
                Integer.parseInt(fields[5]),
                fields[6],
                fields[7],
                fields[8],
                fields[9],
                fields[10],
                fields[11]
        );
    }

    // Методы для выполнения запросов
    public List<Company> findByShortTitle(String shortTitle) {
        return companies.stream()
                .filter(c -> c.getShortTitle().equalsIgnoreCase(shortTitle))
                .collect(Collectors.toList());
    }

    public List<Company> findByBranch(String branch) {
        return companies.stream()
                .filter(c -> c.getBranch().equalsIgnoreCase(branch))
                .collect(Collectors.toList());
    }

    public List<Company> findByActivity(String activity) {
        return companies.stream()
                .filter(c -> c.getActivity().equalsIgnoreCase(activity))
                .collect(Collectors.toList());
    }

    public List<Company> findByDateFoundationRange(LocalDate start, LocalDate end) {
        return companies.stream()
                .filter(c -> (c.getDateFoundation().isAfter(start) || c.getDateFoundation().isEqual(start)) &&
                        (c.getDateFoundation().isBefore(end) || c.getDateFoundation().isEqual(end)))
                .collect(Collectors.toList());
    }

    public List<Company> findByEmployeeCountRange(int min, int max) {
        return companies.stream()
                .filter(c -> c.getCountEmployees() >= min && c.getCountEmployees() <= max)
                .collect(Collectors.toList());
    }

    // Запись в лог
    public void log(String query, int resultCount) throws IOException {
        try (FileWriter logWriter = new FileWriter("logfile.txt", true)) {
            logWriter.write(String.format("%s; Запрос: %s; Найдено: %d%n", LocalDate.now(), query, resultCount));
        }
    }

    // Запись данных в JSON
    public void writeToJson(List<Company> companies, String filePath) throws IOException {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(companies, writer);
        }
    }

    // Запись данных в XML
    public void writeToXml(List<Company> companies, String filePath) throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element rootElement = doc.createElement("Companies");
        doc.appendChild(rootElement);

        for (Company company : companies) {
            Element companyElement = doc.createElement("Company");
            rootElement.appendChild(companyElement);

            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(company.getShortTitle())); // Добавляем другие поля аналогично
            companyElement.appendChild(name);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(new File(filePath)));
    }

    public static void main(String[] args) {
        try {
            Main manager = new Main();
            manager.loadCompanies("input.csv");

            // Пример запроса
            List<Company> result = manager.findByShortTitle("ShortNameExample");
            manager.writeToJson(result, "output.json");
            manager.writeToXml(result, "output.xml");
            manager.log("Поиск по краткому наименованию: ShortNameExample", result.size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
