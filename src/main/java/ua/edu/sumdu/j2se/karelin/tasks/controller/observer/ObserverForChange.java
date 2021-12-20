package ua.edu.sumdu.j2se.karelin.tasks.controller.observer;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.util.TaskIO;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

import java.io.File;

/**
 * Клас ObserverForChange призначений для реалізації спостереження за змінами у списку задач.
 * ПРи виявленні зміни в задачі, видаленні або додаванні, попереджає користувача
 * і оновлює файл на диску
 * Взаємодіє з відповідною ViewObserverMessage
 */
public class ObserverForChange {
    private AbstractTaskList list;
    private View viewObserver;

    public ObserverForChange(AbstractTaskList list, View view) {
        this.list = list;
        this.viewObserver = view;
        this.list.setObserver(this);
    }

    public void update(String message) {

      TaskIO.writeBinary(list, new File("tasks.bin"));
      viewObserver.printMessage(message);
    }

}
