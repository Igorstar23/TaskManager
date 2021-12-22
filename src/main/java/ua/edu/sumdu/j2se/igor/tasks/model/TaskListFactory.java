package ua.edu.sumdu.j2se.igor.tasks.model;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.ListTypes;

public class TaskListFactory {
       /**
        * @return need type of Task List*/
       public static AbstractTaskList createTaskList(ListTypes.types type)  {
              return  (type == ListTypes.types.ARRAY)? new ArrayTaskList() : new LinkedTaskList();
       }
}
