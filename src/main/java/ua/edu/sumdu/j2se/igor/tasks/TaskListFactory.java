package ua.edu.sumdu.j2se.igor.tasks;

public class TaskListFactory {
       /**
        * @return need type of Task List*/
       public static AbstractTaskList createTaskList(ListTypes.types type) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
              return (AbstractTaskList) Class.forName(type.getName()).newInstance();
       }
}
