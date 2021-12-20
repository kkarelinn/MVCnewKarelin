package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

/**
 * Клас ControllerForDetail призначений для реалізації функції додатку,
 * що полягає виведенні на консоль детальної інформації про вибрану задачу,
 * та можливість її змінити.
 * Взаємодіє з відповідною ViewForDetail
 */
public class ControllerForDetail extends Controller {
    public ControllerForDetail(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int action(AbstractTaskList taskList) {
        int index = view.printInfo(taskList);
        Task task = taskList.getTask(index - 1);
        view.getInfo(task);
        if (view.getAnswer()) {
            ControllerForChange.task = task;
            return CHANGE_ACTION;
        }
        return CURRENT_LIST;
    }
}
