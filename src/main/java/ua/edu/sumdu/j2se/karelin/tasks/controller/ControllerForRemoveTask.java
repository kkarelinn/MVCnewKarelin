package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

/**
 * Клас ControllerForRemoveTask призначений для реалізації функції додатку,
 * що полягає видаленні обраної задачі зі списку.
 * Взаємодіє з відповідною ViewForRemoveTask
 */
public class ControllerForRemoveTask extends Controller {
    public ControllerForRemoveTask(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int action(AbstractTaskList taskList) {

        while (true) {
            if (taskList.size() == 0) {
                view.printMessage("Task list is empty now. Exit to MAIN MENU...");
                return MAIN_MENU_ACTION;
            }

            int index = view.printInfo(taskList) - 1;
            taskList.remove(taskList.getTask(index));
            if (!view.getAnswer()) break;
        }
        return MAIN_MENU_ACTION;
    }

}
