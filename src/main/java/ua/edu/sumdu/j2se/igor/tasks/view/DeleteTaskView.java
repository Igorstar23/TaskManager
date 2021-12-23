package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.controller.Inputer;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.Task;
import ua.edu.sumdu.j2se.igor.tasks.model.Tasks;

public class DeleteTaskView extends ShowAllTaskView{
       @Override
       public int printInfo(AbstractTaskList list) {
              super.printInfo(list);
              System.out.print("\nEnter number task for delete : ");
              int num = Inputer.readInt();

              while (num < 1 || num > list.size()) {
                     System.out.println("Wrong number!\nEnter number task again : ");
                     num = Inputer.readInt();
              }

              Task oldTask = Tasks.getSortedTasksFromList(list)[num - 1];
              list.remove(oldTask);
              System.out.println("\nTask '" + oldTask.getTitle() + "' was deleted from list!");
              return Controller.Actions.MENU.getInt();
       }
}
