package ua.edu.sumdu.j2se.karelin.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

/**
 * Абстрактний клас Controller реалує додаток і об'єднує
 * базові основні методи керування для контролерів відповідних функцій додатку
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public abstract class Controller {
    /**
     * Логування подій
     */
    static final Logger log = Logger.getLogger(Controller.class);

    /**
     * Константи, що визначають пінкти меню додатку
     */
    public static final int MAIN_MENU_ACTION = 0;
    public static final int CURRENT_LIST = 1;
    public static final int CALENDAR_ACTION = 2;
    public static final int ADD_TASK_ACTION = 3;
    public static final int REMOVE_TASK_ACTION = 4;
    public static final int SET_ALERT_ACTION = 5;
    public static final int FINISH_ACTION = 6;
    public static final int DETAIL_INFO = 7;
    public static final int CHANGE_ACTION = 8;

    protected View view;
    protected int actionToPerform = 0;

    public Controller(View view, int actionToPerform) {
        this.view = view;
        this.actionToPerform = actionToPerform;
    }

    /**
     * Метод визначає можливість обробки відповідної події контролером
     * @param action - int - тип події (функції) додатку
     * @return - boolean
     */
    public boolean canProcess(int action) {
        return action == actionToPerform;
    }

    /**
     * Метод за замовчуванням для обробки конкретноим контролером необхідного функціоналу
     * @param taskList - AbstractTaskList - список задач
     * @return - int - визначає подію, що відбуваєтсья після роботи відповідного контролера
     */
    public int action(AbstractTaskList taskList) {
        return view.printInfo(taskList);
    }

}


