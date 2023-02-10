package skypro.com.CoursWork2;
import skypro.com.CoursWork2.Model.*;
import skypro.com.CoursWork2.exception.IncorreckArgumentException;
import skypro.com.CoursWork2.exception.NotTaskException;
import skypro.com.CoursWork2.service.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final TaskService TASK_SERVICE = new TaskService();
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}\\:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");


    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.println("выберите пункт меню");
                if (scanner.hasNext()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
printTaskDay(scanner);
                            break;
                        case 0:
                            break label; // label
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        scanner.useDelimiter("\n");
        String title = inputTaskTittle(scanner);
        String description = inputTaskDescription(scanner);
        TaskType type = inputTaskType(scanner);
        LocalDateTime taskTime = inputTaskTime(scanner);
        int repatetabelely = inputRepeatability(scanner);
        creatTask(title, description, type, taskTime, repatetabelely);
    }

    private static String inputTaskTittle(Scanner scanner) {
        System.out.println("Ведите название задачи");
        String title = scanner.next();
        if (title.isBlank()) {
            System.out.println("Необходимо вести название задачи");
            scanner.close();
        }
        return title;
    }

    private static String inputTaskDescription(Scanner scanner) {
        System.out.println("Ведите описание задачи");
        String description = scanner.next();
        if (description.isBlank()) {
            System.out.println("Необходимо вести название задачи");
            scanner.close();
        }
        return description;
    }

    private static TaskType inputTaskType(Scanner scanner) {
        System.out.println("Ведите тип задачи 1-личная, 2 рабочая");
        TaskType type = null;
        int taskTypeNumber = scanner.nextInt();
        switch (taskTypeNumber) {
            case 1:
                type = TaskType.PERSONAL;
                break;
            case 2:
                type = TaskType.WORK;
                break;
            default:
                System.out.println("такого типа задач нет");
                scanner.close();
        }
        return type;
    }

    public static LocalDateTime inputTaskTime(Scanner scanner) {
        System.out.println(" Выдите время задачи dd.mm.yyyy HH:mm");
        if (scanner.hasNext(DATE_TIME_PATTERN)) {
            String dataTime = scanner.next(DATE_TIME_PATTERN);
            return LocalDateTime.parse(dataTime, DATE_TIME_FORMATTER);
        } else {
            System.out.println("веберите дату и время в указанном формате");
            scanner.close();
            return null;
        }
    }

    private static int inputRepeatability(Scanner scanner) {
        System.out.println("ведите повторяемость задачи: 1-Одноразовая 2-Каждый день 3-Каждую неделю" +
                "4-Каждый месяц 5-каждый год");
        if (scanner.hasNext()) {
            return scanner.nextInt();
        } else {
            System.out.println("ведите число повторяемости задач");
            scanner.close();
        }
        return -1;
    }

    private static void creatTask(String title, String description, TaskType type,
                                  LocalDateTime taskTime, int repatetabelely) {
        Task task = null;
        try {
            switch (repatetabelely) {
                case 1:
                    task = new OneTask(title, description, type, taskTime);
                    break;
                case 2:
                    task = new DailyTask(title, description, type, taskTime);
                    break;
                case 3:
                    task = new WeekTask(title, description, type, taskTime);
                    break;
                case 4:
                    task = new MonthyTask(title, description, type, taskTime);
                    break;
                case 5:
                    task = new YearTask(title, description, type, taskTime);
                    break;
                default:
                    System.out.println("задача введена некорректно");
            }
        } catch (IncorreckArgumentException e) {
            System.out.println(e.getMessage());
        }
        if (task != null) {
            TASK_SERVICE.add(task);
            System.out.println("задача добавлена");
        } else {
            System.out.println("ведите задачу");
        }
    }

    private static void removeTask(Scanner scanner) {
        System.out.println("Введите id ");
        int id = scanner.nextInt();
        try {
            TASK_SERVICE.remove(id);
        } catch (NotTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void printTaskDay(Scanner scanner) {
        System.out.println("Выдите дату задачи dd.mm.yyyy");
        if (scanner.hasNext(DATE_PATTERN)) {
            String dataTime = scanner.next(DATE_PATTERN);
            LocalDate inputDate = LocalDate.parse(dataTime, DATE_FORMATTER);
            Collection<Task> tasksDay = TASK_SERVICE.getAllByDate(inputDate);
            for (Task task: tasksDay) {
                System.out.println(task);
            }
        } else {
            System.out.println("веберите дату в указанном формате");
            scanner.close();
        }
    }

    private static void printMenu() {
        System.out.println("1. добавить задачу\n2. удалить задачу\n3. получает задачу на указанный день\n0. выход.");
    }
}
