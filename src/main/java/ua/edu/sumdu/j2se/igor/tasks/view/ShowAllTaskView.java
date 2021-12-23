package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.Task;
import ua.edu.sumdu.j2se.igor.tasks.model.Tasks;

import java.time.LocalDateTime;


public class ShowAllTaskView implements View {

       @Override
       public int printInfo(AbstractTaskList list) {
              System.out.println("\nAll Tasks : \n");
              Task arr[] = Tasks.getSortedTasksFromList(list);

              int i = 0;
              for (var el : arr) {
                   System.out.println("#" + (++i) + " "+ el);
              }
              return 0;
       }
}
