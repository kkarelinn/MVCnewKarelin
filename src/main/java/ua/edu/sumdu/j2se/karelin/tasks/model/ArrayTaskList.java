package ua.edu.sumdu.j2se.karelin.tasks.model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * Клас ArrayTaskList  є реалізацією списку запланованих задач на певний проміжок часу
 * Має функції додавання задач, видалення, редагування, створення стріму і т.п.
 *
 * @author Andrii Karelin
 * @version 1.0
 */

public class ArrayTaskList extends AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {

    private Task[] taskMass = new Task[10]; //розмір масиву задач "за замовчуванням".
    private int sizeList;

    public ArrayTaskList(Task[] taskMass) {
        this.taskMass = taskMass;
        setActSize();
    }

    public ArrayTaskList() {}

    /**
     * Метод глибокого клонування списка задача
     * @return ArrayTaskList глибоке клонування конкретного листа
     * @throws CloneNotSupportedException
     */
    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList listClone = (ArrayTaskList) super.clone();
        listClone.taskMass = new Task[10];
        listClone.setActSize();

        for (Task t : this) {
            listClone.add(t.clone());
        }
        return listClone;
    }

    /**
     * Додавання нової не пустої задачі в масив задач
     *
     * @param task - нова задача
     * @throws IllegalArgumentException - виключення, якщо задачу не передали в метод
     */
    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            log.error("Wrong task name parameter");
            throw new IllegalArgumentException();
        }
        int size = taskMass.length;
        for (int i = 0; i < size; i++) {       //шукаємо вільне місце для нової задачі
            if (taskMass[i] == null) {
                taskMass[i] = task;
                if ((size - i) < 2) {           //якщо скінчилось місце, збільшуємо масив у 2 рази
                    Task[] newMass = new Task[size * 2];
                    System.arraycopy(taskMass, 0, newMass, 0, size);
                    taskMass = newMass.clone();
                }
                break;
            }
        }
        sizeList++;
      notifyChange("..task was added successfully..");
      log.info("Task "+  task.getTitle()+ " was adding successful");
           }

    /**
     * Метод видаляє з масиву передану задачу
     *
     * @param task - передана задача для видалення
     * @return - true (успішне видалення), інакше false.
     */
    @Override
    public boolean remove(Task task) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (taskMass[i].equals(task)) {
                taskMass[i] = null;
                if (size - (i + 1) >= 0) System.arraycopy(taskMass, i + 1, taskMass, i + 1 - 1, size - (i + 1));
                taskMass[size - 1] = null;
                sizeList--;
                notifyChange("..task was removed successfully..");
                log.info("Remove task "+ task.getTitle()+ "successful");
                return true;
            }
        }
        return false;
    }

    /**
     * Метод актуалізує розмір спису
     */
    public void setActSize() {
        int s = 0;
        for (Task temp : taskMass) {
            if (temp != null) {
                s++;
            }
        }
        sizeList = s;
    }

    /**
     * Метод повертає кількість задач із масиву задач
     *
     * @return - int
     */
    @Override
    public int size() {
        return sizeList;
    }

    /**
     * Метод повертає задачу з переданим порядковим номером, якщо він в межах масиву задач
     *
     * @param index - переданий номер задачі
     * @return - Task задача потрібного номеру
     * @throws IndexOutOfBoundsException - номер поза можливим інтервалом
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException("невірний номер задачі");
        }
        return taskMass[index];
    }

    /**
     * Метод повертає e-num параметр, що визначаэ тип об'єкту листа
     * @return e-num
     */
    @Override
    ListTypes.types getType() {
        return ListTypes.types.ARRAY;
    }

    /**
     * Створення ітератору з колекції, що дозволяє пройтись по всім елементам (задачам) зі списку
     * @return ітератор
     */
    @Override
    public Iterator<Task> iterator() {

        return new Iterator<>() {
            private int currId = 0;   //индекс элемента для next()
            private int lastId = -1;    //Индекс элемента, предыдущего next().

            @Override
            public boolean hasNext() {
                return currId < size();
            }

            @Override
            public Task next() {
                lastId = currId++;
                return getTask(lastId);
            }

            @Override
            public void remove() {
                if (lastId == -1) throw new IllegalStateException("Не был вызван метод next()");
                ArrayTaskList.this.remove(getTask(lastId));
                currId--;
                lastId = -1;
            }
        };
    }


    /**
     * Перевизначення equals методу для вірного порівняння списків задач
     * @param o - вхідний параметр (інший обьєкт)
     * @return - true - списки однакові, інакше - false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayTaskList tasks = (ArrayTaskList) o;

        int length = tasks.size();
        if (size() != length) return false;

        for (int i = 0; i < length; i++) {
            if (!taskMass[i].equals(tasks.taskMass[i]))
                return false;
        }
        return true;
    }

    /**
     * Перевизнаяення hashcode методу
     * @return - хешкод
     */
    @Override
    public int hashCode() {
        if (taskMass == null)
            return 0;
        int result = 1;
        for (Task t : taskMass)
            result = 31 * result + (t == null ? 0 : t.hashCode());
        return result;
    }

    /**
     * Створення СТРІМа із списку задач
     * @return СТРІМ із обьєктів задач
     */
    @Override
    public Stream<Task> getStream() {
        return Stream.of(taskMass).limit(size());

    }


}

