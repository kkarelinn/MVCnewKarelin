package ua.edu.sumdu.j2se.karelin.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

/**
 * Інтерфейс View - що визначає базові методі комунікації View конкретних функцій додатку із своїми контролерами
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public interface View {
    /**
     * Логування додатку
     */
   Logger log = Logger.getLogger(View.class);

    /**
     * Виведення основної інформації на консоль
     * @param list - AbstractTaskList - список задач
     * @return - int параметр (за необхідності), що відповідає вибору наступної дії корсистувачем
     */
    int printInfo(AbstractTaskList list);

    /**
     * Виведення/отримання нової інформації про конкретну задачу на консоль
     * @param task - Task - конкретна задача (або пуста при необхіднсті додавання нової задачі)
     * @return - Task - нова/відкорегована задача
     */
    Task getInfo(Task task);

    /**
     * Отримання відповіді користувача з консолі
     * @return - boolean - true (yes) або false (no)
     */
    boolean getAnswer();

    /**
     * Виведення додаткового повідомлення на консоль (за необхідності)
     * @param message
     */
    void printMessage(String message);
}