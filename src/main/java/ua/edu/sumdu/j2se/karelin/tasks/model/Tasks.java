package ua.edu.sumdu.j2se.karelin.tasks.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Клас Tasks реалізує додаток для роботи із запланованими задачами.
 * Має статичні методи для створення календаря (плану задача, виходячи із часу виконання/повторення)
 *
 * @author Andrii Karelin
 *  * @version 1.0
 */
public class Tasks {

    /**
     * Метод створення списку задач, що будут виконуватися у певний проміжок часу
     * @param tasks - список задач типу Task, що реалізує Iterable
     * @param start - LocalDateTime - час ПОЧАТКУ інтервалу виконання
     * @param end - LocalDateTime - час кінця інтервалу виконання
     * @return - Iterable список
     * @throws IllegalArgumentException - можливе виключення
     * @throws CloneNotSupportedException - можливе виключення
     */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws IllegalArgumentException, CloneNotSupportedException {
        if (tasks == null || start == null || start.isAfter(end)) {
            throw new IllegalArgumentException("Wrong arguments");
        }

       return StreamSupport.stream(tasks.spliterator(), false).filter((t) -> t.nextTimeAfter(start) != null &&
               (t.nextTimeAfter(start).compareTo(end)) <= 0).collect(Collectors.toList());
    }

    /**
     * Метод створення сортованоъ Map колекції, виходячі із заплонованих моментів виконання задач
     *  @param tasks - список задач типу Task, що реалізує Iterable
     *  @param start - LocalDateTime - час ПОЧАТКУ інтервалу виконання
     *  @param end - LocalDateTime - час кінця інтервалу виконання
     *  @return - SortedMap колеція
     * @throws CloneNotSupportedException
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start,
                                                      LocalDateTime end) throws CloneNotSupportedException {
        if (tasks == null || start == null || start.isAfter(end)) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        SortedMap<LocalDateTime, Set<Task>> map = new TreeMap<>();

        /* Фильтруем задачи, выполняющиеся хоть раз в заданный период;
        *   перебираем моменты выполнения и затем заносим в МАР (момент выполнения + задача).
        *   Если map.key (время) уже есть - добавляем к Set(map.value),
        *   если нет - создаем keq/value (момент/задача)
        * */
        incoming(tasks, start, end).forEach(t->{
            LocalDateTime nextTaskDate = t.nextTimeAfter(start);
            while (nextTaskDate != null && nextTaskDate.compareTo(end) <= 0) {
                if ((map.containsKey(nextTaskDate)))  map.get(nextTaskDate).add(t);
                else map.put(nextTaskDate, new HashSet<>(List.of(t)));
                nextTaskDate = t.nextTimeAfter(nextTaskDate);
            }
        });

       return map;
    }


}
