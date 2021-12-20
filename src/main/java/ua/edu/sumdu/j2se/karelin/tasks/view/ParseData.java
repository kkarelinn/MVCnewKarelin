package ua.edu.sumdu.j2se.karelin.tasks.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static ua.edu.sumdu.j2se.karelin.tasks.view.View.log;

/**
 * Класс ParseDate призначений для обробки типових функцій додатку по взаємодії с користувачем:
 * - введення даних часу, назви, цифрових даних та їх парсингу у відповідний формат
 * - відповіді на питання так/ні
 */
public class ParseData {

    /**
     * Змінна типу DateTimeFormatter, що визначає паттерн парсингу введеної користувачем дати
     */
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Відкриття потоку вводу даних з консолі BufferedReader для всіх методів
     */
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Метод для париснгу дати, введеної користувачем
     * @return LocalDateTime
     */
    public static LocalDateTime getTimeFromLine(){
        while (true) {
            try {
                return LocalDateTime.parse(br.readLine(), formatter);
            } catch (Exception e) {
                System.out.print("Wrong date time. Enter again: ");
            }
        }

    }

    /**
     * Метод для парсингу із консолі інтервалу, що повинен відповідати часу почату та кінця періоду виконання
     * @param start - LocalDateTime - початок періоду
     * @param end - LocalDateTime - кінець періоду
     * @return - int - час у хвилинах
     */
    public static int getIntervalFromLine(LocalDateTime start, LocalDateTime end){
        while (true) {
            try {
                System.out.print("Enter time INTERVAL in min : ");
                int interval = Integer.parseInt(br.readLine())*60;
                if (start.isBefore(end)
                        && interval <= (end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC))){
                    return interval;
                } else System.out.print("Wrong interval. Enter again: ");

            } catch (Exception e) {
                System.out.print("Wrong interval. Enter again: ");
            }
        }
    }

    /**
     * Метод для парсингу із консолі інтервалу НОТИФІКАЦІЇ у хвилинах
     * @return - int - час у хвилинах
     */
    public static int getIntervalFromLine(){
        while (true) {
            try {
                int interval = Integer.parseInt(br.readLine());
                if (interval > 0){
                    return interval;
                } else System.out.print("Wrong time in minutes. Enter again: ");

            } catch (Exception e) {
                System.out.print("Wrong time in minutes. Enter again: ");
            }
        }
    }

    /**
     * Метод для парсингу із консолі boolean відповіді так/ні
     * @return - boolean false/true
     */
    public static boolean getBooFromLine(){
        while (true) {
            try {
                String temp = br.readLine();
                if (temp.matches("[yn]{1}")) {
                    return temp.equals("y");
                }
            } catch (IOException e) {
                System.out.println("Some mistake");
                log.error("Mistake in reedLine in getBoolean", e);
            }
            System.out.print("Wrong parameter. Enter again: ");
        }
    }

    /**
     * Метод для парсингу із консолі назви
     * @return - String
     */
    public static String getNameFromLine(){
        while (true) {
            try {
                String str = br.readLine();
                if (!str.isEmpty()){
                    return str;
                }
                System.out.print("Line is empty. Enter again: ");
            } catch (IOException e) {
                System.out.print("Wrong parameter. Enter again: ");
            }
        }
    }

    /**
     * Метод для парсингу із консолі вибору користувача (цифра)
     * у заданому в параметрах проміжку
     * @param size - int  межа інтервалу для вибору
     * @return - int  вибір користувача
     */
       public static int getActionFromLine(int size) {
        while (true) {
            try {
                int index = Integer.parseInt(br.readLine());
                if (index > 0 && index <= size) {
                   return index;
                } else {
                    System.out.print("Wrong choice. Please, try again: ");

                }
            } catch (Exception e) {
                System.out.print("Wrong choice. Please, try again: ");
            }
        }
    }
}
