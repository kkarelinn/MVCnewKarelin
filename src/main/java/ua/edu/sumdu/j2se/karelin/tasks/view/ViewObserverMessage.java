package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class ViewObserverMessage implements View{
    @Override
    public int printInfo(AbstractTaskList list) {
        return -1;
    }

    @Override
    public void printCalendar(Map<LocalDateTime, Set<Task>> map) {

    }

    @Override
    public Task getInfo(Task task) {
        return null;
    }

    @Override
    public int getInfo() {
        return 0;
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
