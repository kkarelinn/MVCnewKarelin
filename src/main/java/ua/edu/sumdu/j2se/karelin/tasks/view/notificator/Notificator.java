package ua.edu.sumdu.j2se.karelin.tasks.view.notificator;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.Map;

import static ua.edu.sumdu.j2se.karelin.tasks.view.View.log;

/**
 * Клас Notificator реалізує додаток для роботи із запланованими задачами.
 * Метою роботи є завчасне повідомлення користувача про скоре виконання задачі.
 * Інтервал повідомлення за замовчуванням - 5 хвилин, може бути виставлений на потрібне користувачу значення.
 * Запускається окремим потоком.
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public class Notificator extends Thread {

    private AbstractTaskList list;
    private int timeInMinutes = 5;
    private Map<Task, Boolean> wasInfo = new LinkedHashMap<>();

    /**
     * Метод "обнулення" нотифікації для задачі, що змінилася
     * @param newTask - змінена задача
     * @param oldTask - задача, що була у списку до змін
     */
    public void setWasInfoTask(Task newTask, Task oldTask) {
        if (wasInfo.containsKey(oldTask))this.wasInfo.remove(oldTask);
        this.wasInfo.put(newTask,false);
    }


    /**
     * Метод вставновлення інтервалу (у хвилинах)
     * @param timeInMinutes
     */
    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    /**
     * Конструктор, що створяє МАРу із відмітками, що була вже нотифікація по конкретній задачі
     * @param list - AbstractTaskList - список задач
     */
    public Notificator(AbstractTaskList list) {
        this.list = list;
        list.getStream().forEach((t)->this.wasInfo.put(t,false));
    }

    /**
     * Основна логіка методу періодичної перевірки часу найближчого виконання запланованих задач
     */
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            log.error("Mistake in Notificator in sleep", e);
        }

        LocalDateTime curTime;
        System.out.println();

        while (true) {
            curTime = LocalDateTime.now();

            for (Task t : list) {
                LocalDateTime next = t.nextTimeAfter(LocalDateTime.now());
                if (next != null) {
                    boolean wasIn = wasInfo.getOrDefault(t, false);
                    int deltaTime = (int) (next.toEpochSecond(ZoneOffset.UTC) - curTime.toEpochSecond(ZoneOffset.UTC)) / 60;
                    if (next.isAfter(curTime) && deltaTime <= timeInMinutes && !wasIn) {
                        System.out.println("\n<<<<<Attention!>>>>    " + deltaTime + " minutes before run " + t.getTitle());
                        if (wasInfo.containsKey(t)) { wasInfo.replace(t,true);} else
                            wasInfo.put(t,true);

                    }
                }
            }

            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                log.error("Mistake in Notificator in sleep", e);
            }

        }
    }
}

