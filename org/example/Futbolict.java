package org.example;
import model.Info;
import model.User;
import java.io.*;
import java.util.Scanner;

public class Futbolict {
    private static Info[] infoArray;
    private static final String DATA_FILE = "user_data.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println('\n' + "Введите логин:");
            String username = scanner.nextLine();
            System.out.println("Введите пароль:");
            String password = scanner.nextLine();

            User[] users = {
                    new User("admin", "admin123", "admin"),
                    new User("user", "user123", "user")
            };

            User currentUser = null;

            for (User user : users) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    currentUser = user;
                    break;
                }
            }

            if (currentUser != null) {
                System.out.printf("Добро пожаловать, %s!%n", currentUser.getUsername());
                selectRole(currentUser);
            } else {
                System.out.println("Неверные логин или пароль");
            }

            System.out.println('\n'+"Хотите ли вы продолжить? (yes/no)");
            String continueOption = scanner.nextLine();
            if (!continueOption.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }

    private static void selectRole(User user) {
        switch (user.getRole()) {
            case "admin":
                adminActions(user);
                break;
            case "user":
                userActions();
                break;
            default:
                System.out.println("Неопределенная роль");
        }
    }

    private static void adminActions(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Действия администратора:");
            System.out.println("1. Добавить футболистов");
            System.out.println("2. Удалить футболиста");
            System.out.println("3. Сменить пользователя");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    infoArray = fillAndPrintInfo();
                    break;
                case 2:
                    deletePlayer();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Неправильный выбор");
            }
        }
    }

    private static Info[] fillAndPrintInfo() {
        Info[] infoArray = new Info[2];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < infoArray.length; i++) {
            infoArray[i] = new Info();
            System.out.println("Введите имя футболиста:");
            infoArray[i].setName(scanner.nextLine());
            System.out.println("Введите клуб футболиста:");
            infoArray[i].setClub(scanner.nextLine());
            System.out.println("Введите возраст футболиста:");
            infoArray[i].setAge(scanner.nextInt());
            scanner.nextLine();
        }
        System.out.println("Iнформация о футболистах:");
        for (Info info : infoArray) {
            info.Information();
        }
        return infoArray;
    }

    private static void deletePlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя футболиста для удаления:");
        String playerName = scanner.nextLine();

        // Check if infoArray is not null and has elements
        if (infoArray != null && infoArray.length > 0) {
            for (int i = 0; i < infoArray.length; i++) {
                if (infoArray[i].getName().equals(playerName)) {
                    Info[] newArray = new Info[infoArray.length - 1];
                    System.arraycopy(infoArray, 0, newArray, 0, i);
                    System.arraycopy(infoArray, i + 1, newArray, i, infoArray.length - i - 1);
                    infoArray = newArray;
                    System.out.println("Футболист удален успешно.");
                    return;
                }
            }
            System.out.println("Футболист с таким именем не найден.");
        } else {
            System.out.println("Iнформация о футболистах отсутствует.");
        }
    }


    private static void userActions() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println('\n'+"Действия пользователя:");
            System.out.println("1. Просмотреть информацию о футболистах");
            System.out.println("2. Сохранить данные");
            System.out.println("3. Сменить пользователя");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    printInfoArray();
                    break;
                case 2:
                    saveData();
                case 3:
                    return;
                default:
                    System.out.println("Неправильный выбор");
            }
        }
    }

    private static void printInfoArray() {
        if (infoArray != null) {
            System.out.println("Iнформация о футболистах:");
            for (Info info : infoArray) {
                info.Information();
            }
        } else {
            System.out.println("Iнформация о футболистах отсутствует");
        }
    }

    private static void saveData() {
        try (PrintWriter writer = new PrintWriter(DATA_FILE)) {
            if (infoArray != null) {
                for (Info info : infoArray) {
                    writer.println("Название футболиста:"+info.getName() + "," +"Клуб футболиста:"+ info.getClub() + ","+"Возраст футболиста:" + info.getAge());
                }
                System.out.println("Данные успешно сохранены.");
            } else {
                System.out.println("Нет данных для сохранения.");
            }
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении данных: " + e.getMessage());
        }
    }


}
