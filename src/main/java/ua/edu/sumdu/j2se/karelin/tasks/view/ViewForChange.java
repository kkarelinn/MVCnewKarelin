package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;
import java.time.LocalDateTime;

/**
 * Клас ViewForChange реалізує додаток і виводить на консоль інформацію,
 * що дозволяє змінити параметри вибраної задачі.
 * Взаємодіє із відповідним контролером ControllerForChange
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public class ViewForChange implements View {
    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.println("Choose, what you want to do:");
        System.out.println("1. Set new TITLE.");
        System.out.println("2. Set new TIME to launch.");
        System.out.println("3. Set new START time, END time and INTERVAL.");
        System.out.println("4. Set ACTIVITY.");
        System.out.print("Enter your choice: ");
  return -1;
    }

    @Override
    public Task getInfo(Task t) {

        switch (ParseData.getActionFromLine(4)){
            case 1 : {
                try {
                    System.out.print("Enter new title: ");
                    String str = ParseData.getNameFromLine();
                    t.setTitle(str);
                    break;
                } catch (Exception e) {
                    log.error("Mistake in getTaskData in ViewForChange", e);
                }
            }
            case 2 : {
                System.out.print("Enter new TIME to launch in format YYYY-MM-DD HH:MM : ");
                t.setTime(ParseData.getTimeFromLine());
                break;
            }
            case 3 : {
                System.out.print("Enter new START time in format YYYY-MM-DD HH:MM : ");
                LocalDateTime start = ParseData.getTimeFromLine();
                System.out.print("Enter new END time in format YYYY-MM-DD HH:MM : ");
                LocalDateTime end = ParseData.getTimeFromLine();
                int interval = ParseData.getIntervalFromLine(start, end);
                t.setTime(start, end, interval);
                break;
            }
            case 4 : {
                System.out.print("Make task ACTIVE? (y/n) : ");
                t.setActive(ParseData.getBooFromLine());
                break;
            }
        }
       return t;
    }

    @Override
    public boolean getAnswer() {
        return false;
    }

    @Override
    public void printMessage(String message) {
        System.out.println("..you made now change to current task..");
    }
}
