package ua.edu.sumdu.j2se.karelin.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.karelin.tasks.controller.Controller;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public interface View {
   Logger log = Logger.getLogger(View.class);
    int printInfo(AbstractTaskList list);
    void printCalendar(Map<LocalDateTime, Set<Task>> map);
    Task getInfo(Task task);
    int getInfo();
    boolean getAnswer();
    void printMessage(String message);
}