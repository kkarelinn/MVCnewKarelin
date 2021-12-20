package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

/**
 * Клас ViewForCalendar реалізує додаток і виводить на консоль час
 * із відповідними даними про задачі, що будуть виконанні у цей момент.
 * Взаємодіє із відповідним контролером ControllerForCalendar
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public class ViewForCalendar implements View {
    @Override
    public int printInfo(AbstractTaskList list) {
        return -1;
    }

    /**
     * Метод, що призначений для виводу на консоль сформованої Мапи: час виконання-список задач
     * @param map - SortedMap
     */
    public void printCalendar(Map<LocalDateTime, Set<Task>> map) {
        System.out.println("Calendar to the end of the CURRENT week");
        if (map.size() == 0) {
            System.out.println("the Calendar is empty");
        }

        for (Map.Entry<LocalDateTime, Set<Task>> entry : map.entrySet()) {
            LocalDateTime date = entry.getKey();
            StringBuilder sb = new StringBuilder(new StringBuilder(date.toLocalDate()
                    + " " + date.toLocalTime().truncatedTo(ChronoUnit.SECONDS)).append(" - "));
            Set<Task> tasks = entry.getValue();
            for (Task t : tasks) {
                sb.append(t.getTitle()).append(", ");
            }
            System.out.println(sb.substring(0, sb.length() - 2));
        }
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


