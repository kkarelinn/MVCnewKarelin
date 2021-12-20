package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;
import java.time.temporal.ChronoUnit;

/**
 * Клас ViewForDetail реалізує додаток і виводить на консоль
 * детальну інформацію про вибрану задачу зі списку.
 * Взаємодіє із відповідним контролером ControllerForDetail
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public class ViewForDetail implements View {

    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.print("Please, choose task number to receive detail information: ");
        int index = ParseData.getActionFromLine(list.size());
       return index;
         }

    @Override
    public Task getInfo(Task task) {
        System.out.println("---------------------------");
        System.out.println("Detail information about task " + task.getTitle());
        System.out.println("Title: " + task.getTitle());
        System.out.println("TIME to start: " + ((task.isRepeated())? " not set" : task.getTime().toLocalDate()
                +" "+task.getTime().toLocalTime().truncatedTo(ChronoUnit.MINUTES)));

        System.out.println("START time: " + ((task.isRepeated())? task.getStartTime().toLocalDate()
                +" "+task.getStartTime().toLocalTime().truncatedTo(ChronoUnit.MINUTES) :" not set"));

        System.out.println("END time: " + ((task.isRepeated())? task.getEndTime().toLocalDate()
                +" "+task.getEndTime().toLocalTime().truncatedTo(ChronoUnit.MINUTES) :" not set"));

        System.out.println("time INTERVAL: " + ((task.isRepeated())? task.getRepeatInterval()/60+"min" :" not set"));
        System.out.println((task.isActive())? "task is ACTIVE" : "task is NOT ACTIVE");
        System.out.println("---------------------------");

        return null;
    }

    @Override
    public boolean getAnswer() {
        System.out.print("Do you want to change current task? (y/n): ");
        return ParseData.getBooFromLine();
    }

    @Override
    public void printMessage(String message) {

    }


}
