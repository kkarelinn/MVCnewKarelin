package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

/**
 * Клас ViewForRemove реалізує додаток і виводить на консоль
 * інформацію, що необхідна для видалення конкретної задачі зі списку.
 * Взаємодіє із відповідним контролером ControllerForRemove
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public class ViewForRemove implements View {

    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.print("What task would you like to remove from list? Please, enter the number: ");
        return ParseData.getActionFromLine(list.size());
    }

    @Override
    public Task getInfo(Task task) {
        return null;
    }

    @Override
    public boolean getAnswer() {
        System.out.print("Would you like to remove one more task? (y/n): ");
        return ParseData.getBooFromLine();
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

}
