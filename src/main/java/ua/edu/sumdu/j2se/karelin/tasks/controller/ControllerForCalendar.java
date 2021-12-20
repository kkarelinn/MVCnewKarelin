package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;
import ua.edu.sumdu.j2se.karelin.tasks.model.Tasks;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;
import ua.edu.sumdu.j2se.karelin.tasks.view.ViewForCalendar;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.Set;

/**
 * Клас ControllerForCalendar призначений для реалізації функції додатку,
 * що полягає формуванні переліку задач, що будуть виконані до кінця поточного тижня.
 * Перелік у вигляді ЧАС ВИКОНАННЯ - СПИСОК ЗАДАЧ, що будуть виконанні
 * Взаємодіє з відповідною ViewForCalendar
 */
public class ControllerForCalendar extends Controller {
    public ControllerForCalendar(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int action(AbstractTaskList taskList) {
        final LocalDateTime toEndWeek = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).with(LocalTime.MIN);

        try {
            Map<LocalDateTime, Set<Task>> mapWeek = Tasks.calendar(taskList, LocalDateTime.now(), toEndWeek );
           ((ViewForCalendar)view).printCalendar(mapWeek);
        } catch (CloneNotSupportedException e) {
            log.error("Mistake to print calendar", e);
        }

        return MAIN_MENU_ACTION;

    }


}
