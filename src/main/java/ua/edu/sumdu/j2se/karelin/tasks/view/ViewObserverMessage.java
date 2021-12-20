package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

/**
 * Клас ViewObserverMessage реалізує додаток і виводить на консоль
 * інформацію, що підтверджує відповідну зміну списку (додавання/зміна/видалення).
 * Взаємодіє із відповідним контролером ControllerForRemove
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public class ViewObserverMessage implements View{
    @Override
    public int printInfo(AbstractTaskList list) {
        return -1;
    }

    @Override
    public Task getInfo(Task task) {
        return null;
    }

    @Override
    public boolean getAnswer() {
        return false;
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }
}
