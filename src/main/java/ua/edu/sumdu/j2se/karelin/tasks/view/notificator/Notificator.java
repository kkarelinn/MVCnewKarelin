package ua.edu.sumdu.j2se.karelin.tasks.view.notificator;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.LinkedHashMap;
import java.util.Map;

public class Notificator extends Thread {

    private AbstractTaskList list;
    private int timeInMinutes = -1;
    private Map<Task, Boolean> wasInfo = new LinkedHashMap<>();

    public void setWasInfoTask(Task task) {
        this.wasInfo.put(task,false);
    }



    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public Notificator(AbstractTaskList list) {
        this.list = list;
        list.getStream().forEach((t)->this.wasInfo.put(t,false));
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
                    if (next.isAfter(curTime) && deltaTime < timeInMinutes && !wasIn) {
                        System.out.println("\n<<<<<Attention!>>>>    " + deltaTime + " minutes before run " + t.getTitle());
                        if (wasInfo.containsKey(t)) { wasInfo.replace(t,true);} else
                            wasInfo.put(t,true);

                    }
                }
            }

            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

