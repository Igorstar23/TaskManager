package ua.edu.sumdu.j2se.igor.tasks;

public class TaskListFactory {
       /**
        * @return need type of Task List*/
       public static AbstractTaskList createTaskList(ListTypes.types type) {
              return (type == ListTypes.types.ARRAY)? new ArrayTaskList() : new LinkedTaskList();
       }
}
