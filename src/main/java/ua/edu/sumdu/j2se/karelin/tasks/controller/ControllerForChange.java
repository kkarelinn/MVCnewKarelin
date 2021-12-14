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
            Task currTask = null;
            try {
                currTask = task.clone();
            } catch (CloneNotSupportedException e) {
                log.error("Mistake in change process with clone", e);
            }
            view.getInfo(task);
            if (task.equals(currTask)) {
                view.printMessage("..you made now change to current task..");
            } else {
             if (ControllerForAllert.getNotificator()!= null)ControllerForAllert.getNotificator().setWasInfoTask(task);
                taskList.notifyChange("..change was successful..");
            }

        }
        return CURRENT_LIST;
    }
}
