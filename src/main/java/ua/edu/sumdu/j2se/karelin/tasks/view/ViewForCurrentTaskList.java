package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

/**
 * Клас ViewForCurrentTaskList реалізує додаток і виводить на консоль
 * інформацію про перелік задач, що містіться на даний момент у списку.
 * Взаємодіє із відповідним контролером ControllerForCurrentTaskList
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public class ViewForCurrentTaskList implements View {

    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.println();
        System.out.println("Your current task list is looks like this:");
        System.out.println(list);
        return -1;

    }

    @Override
    public Task getInfo(Task task) {
        return null;
    }

    @Override
    public boolean getAnswer() {
        System.out.println();
        System.out.print("Would you like to make some change in tasks? (y/n): ");
        boolean action = ParseData.getBooFromLine();
        return action;
    }

    @Override
    public void printMessage(String message) {

    }


}
