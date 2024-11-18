import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Company {
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
    private String link;

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Company(String csvLine, String delimiter) {
        String[] fields = csvLine.split(delimiter, -1);
        name = fields[0];
        shortTitle = fields[1];

        try {
            dateUpdate = fields[2].isEmpty() ? null : LocalDate.parse(fields[2], DATE_FORMAT);
        } catch (DateTimeParseException e) {
            dateUpdate = null;
        }

        address = fields[3];

        try {
            dateFoundation = fields[4].isEmpty() ? null : LocalDate.parse(fields[4], DATE_FORMAT);
        } catch (DateTimeParseException e) {
            dateFoundation = null;
        }

        countEmployees = fields[5].isEmpty() ? 0 : Integer.parseInt(fields[5]);
        auditor = fields[6];
        phone = fields[7];
        email = fields[8];
        branch = fields[9];
        activity = fields[10];
        link = fields[11];
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public LocalDate getDateFoundation() {
        return dateFoundation;
    }

    public int getCountEmployees() {
        return countEmployees;
    }

    public String getBranch() {
        return branch;
    }

    public String getActivity() {
        return activity;
    }

    @Override
    public String toString() {
        return name + "\t" + shortTitle + "\t" + dateUpdate + "\t" + address + "\t" + dateFoundation + "\t" + countEmployees + "\t" + auditor + "\t" + phone + "\t" + email + "\t" + branch + "\t" + activity + "\t" + link;
    }

    public String toXml() {
        return "    <Company>\n" +
                "        <name>" + name + "</name>\n" +
                "        <shortTitle>" + shortTitle + "</shortTitle>\n" +
                "        <dateUpdate>" + (dateUpdate != null ? dateUpdate.toString() : "") + "</dateUpdate>\n" +
                "        <address>" + address + "</address>\n" +
                "        <dateFoundation>" + (dateFoundation != null ? dateFoundation.toString() : "") + "</dateFoundation>\n" +
                "        <countEmployees>" + countEmployees + "</countEmployees>\n" +
                "        <auditor>" + auditor + "</auditor>\n" +
                "        <phone>" + phone + "</phone>\n" +
                "        <email>" + email + "</email>\n" +
                "        <branch>" + branch + "</branch>\n" +
                "        <activity>" + activity + "</activity>\n" +
                "        <link>" + link + "</link>\n" +
                "    </Company>\n";
    }
}
