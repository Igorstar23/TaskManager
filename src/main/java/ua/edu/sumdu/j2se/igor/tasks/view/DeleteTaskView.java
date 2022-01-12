package ua.edu.sumdu.j2se.igor.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
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

              Task oldTask = null;
              try {
                     Logger.getLogger(DeleteTaskView.class).debug("Start getting sorted tasks for delete");
                     oldTask = Tasks.getSortedTasksFromList(list)[num - 1];
                     Logger.getLogger(DeleteTaskView.class).debug("start removing task");
                     list.remove(oldTask);
                     System.out.println("\nTask '" + oldTask.getTitle() + "' was deleted from list!");

              } catch (IllegalArgumentException e) {
                     Logger.getLogger(DeleteTaskView.class).error("Don't get sorted list of task for delete :", e);

              } catch (ArrayIndexOutOfBoundsException e) {
                     Logger.getLogger(this.getClass().getName()).error("number task is out of bounds! Task wasn't deleted!", e);
              }

              return Controller.Actions.MENU.getInt();
       }
}
