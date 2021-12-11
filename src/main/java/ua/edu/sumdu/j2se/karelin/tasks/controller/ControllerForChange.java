package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

public class ControllerForChange extends Controller {
    public static Task task;

    public ControllerForChange(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int action(AbstractTaskList taskList) {
        view.printInfo(taskList);
        if (task != null) {
            task = view.getInfo(task);
        }
        return CURRENT_LIST;
    }

}
