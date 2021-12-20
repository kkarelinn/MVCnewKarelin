package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.view.notificator.Notificator;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

/**
 * Клас ControllerForAllert призначений для реалізації функції додатку,
 * що полягає у встановленні періоду нотифікації (попередження) для наявного списку задач.
 * Взаємодіє з відповідною ViewForAllert
 */
public class ControllerForAllert extends Controller {

   private static Notificator notificator;
       public static Notificator getNotificator() {
        return notificator;
    }

    public ControllerForAllert(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int action(AbstractTaskList taskList) {
        int time = view.printInfo(taskList);
        notifyLaunch(taskList, time);
        return MAIN_MENU_ACTION;
    }

    /**
     * Метод створює нотифікатор для списку, встановлю інтервал, параметр Демон, за запускає потік в роботу.
     * @param list - AbstractTaskList - список для відстеження задач
     * @param time - int  - час нотифікації у хвилинах
     */
    public static void notifyLaunch(AbstractTaskList list, int time) {
        notificator = new Notificator(list);
        notificator.setTimeInMinutes(time);
        notificator.setDaemon(true);
        notificator.start();
        list.notifyChange(" notificator was added with time " + time + "min");
    }

}
