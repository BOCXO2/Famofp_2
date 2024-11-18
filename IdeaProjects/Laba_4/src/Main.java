// Работа Опришко Станислава 5 группа 2 курс
import java.util.*;

// Класс Contact, представляющий запись в электронной книге
class Contact implements Comparable<Contact> {
    // Обязательные поля
    private String name;  // Наименование (имя человека или организации)
    private String mobileNumber;  // Номер мобильного телефона

    // Необязательные поля
    private String workNumber;  // Номер рабочего телефона
    private String homeNumber;  // Номер домашнего телефона
    private String email;  // Адрес электронной почты
    private String webAddress;  // Адрес веб-страницы
    private String address;  // Почтовый адрес

    // Конструктор для обязательных полей
    public Contact(String name, String mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    // Конструктор для всех полей
    public Contact(String name, String mobileNumber, String workNumber, String homeNumber, String email, String webAddress, String address) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
        this.email = email;
        this.webAddress = webAddress;
        this.address = address;
    }

    // Метод для преобразования объекта в строку
    @Override
    public String toString() {
        return "Name: " + name + ", Mobile: " + mobileNumber +
                (workNumber != null ? ", Work: " + workNumber : "") +
                (homeNumber != null ? ", Home: " + homeNumber : "") +
                (email != null ? ", Email: " + email : "") +
                (webAddress != null ? ", Web: " + webAddress : "") +
                (address != null ? ", Address: " + address : "");
    }

    // Метод для сравнения объектов по имени (по умолчанию)
    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    // Сравнение по различным полям
    public static Comparator<Contact> compareByName = new Comparator<Contact>() {
        @Override
        public int compare(Contact c1, Contact c2) {
            return c1.name.compareTo(c2.name);
        }
    };

    public static Comparator<Contact> compareByMobile = new Comparator<Contact>() {
        @Override
        public int compare(Contact c1, Contact c2) {
            return c1.mobileNumber.compareTo(c2.mobileNumber);
        }
    };

    public static Comparator<Contact> compareByEmail = new Comparator<Contact>() {
        @Override
        public int compare(Contact c1, Contact c2) {
            if (c1.email == null || c2.email == null) return 0;
            return c1.email.compareTo(c2.email);
        }
    };

    // Метод для создания объекта из строки
    public static Contact fromString(String contactStr) {
        String[] fields = contactStr.split(",");
        return new Contact(fields[0], fields[1],
                fields.length > 2 ? fields[2] : null,
                fields.length > 3 ? fields[3] : null,
                fields.length > 4 ? fields[4] : null,
                fields.length > 5 ? fields[5] : null,
                fields.length > 6 ? fields[6] : null);
    }
}

// Главный класс для тестирования
public class Main {
    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Выбор: вводить свои данные или использовать тестовые
        System.out.println("Выберите режим:");
        System.out.println("1 - Использовать готовые тесты");
        System.out.println("2 - Ввести свои данные");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Пропустить переход на следующую строку

        if (choice == 1) {
            // Готовые тесты
            contacts.add(new Contact("Alice", "123456789", "111222333", null, "alice@mail.com", "alice.com", "Address 1"));
            contacts.add(new Contact("Bob", "987654321", "444555666", "777888999", null, "bob.com", "Address 2"));
            contacts.add(new Contact("Charlie", "456123789", null, "333444555", "charlie@mail.com", null, null));
            System.out.println("Использованы готовые тестовые данные.");
        } else if (choice == 2) {
            // Ввод данных с консоли
            System.out.println("Сколько контактов вы хотите добавить?");
            int numContacts = scanner.nextInt();
            scanner.nextLine(); // Пропустить переход на следующую строку

            for (int i = 0; i < numContacts; i++) {
                System.out.println("Введите данные для контакта " + (i + 1) + ":");
                System.out.print("Имя: ");
                String name = scanner.nextLine();
                System.out.print("Мобильный номер: ");
                String mobileNumber = scanner.nextLine();
                System.out.print("Рабочий номер (если есть, иначе пропустите): ");
                String workNumber = scanner.nextLine();
                System.out.print("Домашний номер (если есть, иначе пропустите): ");
                String homeNumber = scanner.nextLine();
                System.out.print("Email (если есть, иначе пропустите): ");
                String email = scanner.nextLine();
                System.out.print("Веб-страница (если есть, иначе пропустите): ");
                String webAddress = scanner.nextLine();
                System.out.print("Адрес (если есть, иначе пропустите): ");
                String address = scanner.nextLine();

                contacts.add(new Contact(name, mobileNumber,
                        workNumber.isEmpty() ? null : workNumber,
                        homeNumber.isEmpty() ? null : homeNumber,
                        email.isEmpty() ? null : email,
                        webAddress.isEmpty() ? null : webAddress,
                        address.isEmpty() ? null : address));
            }
        } else {
            System.out.println("Неверный выбор. Программа завершена.");
            return;
        }

        // Сортировка по выбранному полю (в данном примере сортируем по имени)
        System.out.println("Выберите поле для сортировки:");
        System.out.println("1 - По имени");
        System.out.println("2 - По мобильному номеру");
        System.out.println("3 - По email");

        int sortChoice = scanner.nextInt();
        scanner.nextLine(); // Пропустить переход на следующую строку

        switch (sortChoice) {
            case 1:
                Collections.sort(contacts, Contact.compareByName);
                break;
            case 2:
                Collections.sort(contacts, Contact.compareByMobile);
                break;
            case 3:
                Collections.sort(contacts, Contact.compareByEmail);
                break;
            default:
                System.out.println("Неверный выбор. Программа завершена.");
                return;
        }

        // Вывод отсортированных контактов
        System.out.println("Отсортированные контакты:");
        for (Contact contact : contacts) {
            System.out.println(contact);
        }

        scanner.close();
    }
}
