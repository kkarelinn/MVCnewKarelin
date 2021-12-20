package ua.edu.sumdu.j2se.karelin.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.karelin.tasks.controller.observer.ObserverForChange;
import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Абстрактний клас списку задач, що акумуляє загальні властивості споріднених,
 * але різних за реалізацією списків задач
 *
 * @author Andrii Karelin
 *  * @version 1.0
 */

public abstract class AbstractTaskList implements Iterable<Task>,
        Cloneable, Serializable {
    /**
     * Логування подій
     */
    static final Logger log = Logger.getLogger(AbstractTaskList.class);

    /**
     * Змінна, що визначає "Наглядача" за основними подіями (додавання, видалення, зміна) в списках
     */
    private ObserverForChange observer = null;

    /**
     * Встановлення (додавання) "Наглядача"
     * @param observer
     */
    public void setObserver(ObserverForChange observer) {
        this.observer = observer;
    }
    public AbstractTaskList clone() throws CloneNotSupportedException {
        return (AbstractTaskList) super.clone();
    }

    abstract public void add(Task task) throws IllegalArgumentException;

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    public abstract Stream<Task> getStream();

    abstract ListTypes.types getType();

    /**
     * Перевизначений метод визначає формат виводу на консоль списку задач
     * @return
     */
    public String toString() {
        Iterator<Task> it = iterator();
        if (!it.hasNext())
            return "[]";
        int index = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" [");
        while (it.hasNext()) {
            sb.append("\n").append("Task #" + index + ". ");
            Task t = it.next();
            sb.append(t.toString());
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(", ");
            index++;
        }
        return sb.toString();
    }


    /**
     * Метод "нотифікації"(підтвердження) для можливих змін списку задач
     * @param message - String "повідомлення" у відповідності до дії
     */
    public void notifyChange(String message) {
        if (observer != null)
            observer.update(message);
    }

}

