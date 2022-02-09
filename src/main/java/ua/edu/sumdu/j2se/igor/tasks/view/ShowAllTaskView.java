package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.Task;
import ua.edu.sumdu.j2se.igor.tasks.model.Tasks;

public class ShowAllTaskView implements View {

       /**
       * For print info about all tasks from list
       * */
       @Override
       public int printInfo(AbstractTaskList list) {

              if (list.size() == 0) {
                  System.out.println("\nTask List is empty, please create or load saving tasks");
                  return 0;
              }

              System.out.println("\nAll Tasks : \n");
              Task arr[] = Tasks.getSortedTasksFromList(list);

              int i = 0;
              for (var el : arr) {
                   System.out.println("#" + (++i) + " "+ el);
              }
              return 0;
       }
}
