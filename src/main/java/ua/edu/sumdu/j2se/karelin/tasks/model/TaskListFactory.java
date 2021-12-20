package ua.edu.sumdu.j2se.karelin.tasks.model;

/**
 * Клас реалізує функціонал створення обьєктів конкретної реалізації списку задач(ArrayTaskList/LinkedTaskList)
 */
public class TaskListFactory {
    public static AbstractTaskList createTaskList(ListTypes.types type){
    switch (type){
        case ARRAY:
            return new ArrayTaskList();

        case LINKED:
            return new LinkedTaskList();
    }
    return null;
    }
}
