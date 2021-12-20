package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;


/**
 * Клас ViewForAllert реалізує додаток і виводить на консоль меню
 * необхідне для встановлення періоду нотифікаці(попередження) про виконання задач зі списку.
 * Взаємодіє із відповідним контролером ControllerForAllert
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public class ViewForAllert implements View {

    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.print("Please, enter notification time in minutes: ");
        int min = ParseData.getIntervalFromLine();
        return min;
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

    }


}

