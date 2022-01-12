package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;

import java.util.HashSet;

public class MenuView implements View{
       private String[] actionMenu = new String[] {
               "for add new task to your task list",
               "for edit task",
               "for delete task from your task list",
               "for show all tasks",
               "for show all tasks in calendar",
               "for show calendar for need time",
               "for save changing",
               "for load saving",
               "or more num for exit"
       };
       private HashSet<Integer> notAvailAbleActions = new HashSet<Integer>() {};

       {
              notAvailAbleActions.add(Controller.Actions.DELETE_TASK.getInt() - 1);
              notAvailAbleActions.add(Controller.Actions.EDIT_TASK.getInt() - 1);
              notAvailAbleActions.add(Controller.Actions.SHOW_ALL_CALENDAR.getInt() - 1);
              notAvailAbleActions.add(Controller.Actions.SHOW_CALENDAR.getInt() - 1);
       }

       @Override
       public int printInfo(AbstractTaskList list) {
              System.out.println("\nEnter variant action : \n");
              for (int i = 0; i < actionMenu.length; i++) {

                   if (list.size() == 0) {

                       if (this.notAvailAbleActions.contains(i)) continue;
                   }
                   System.out.println("Enter " + (i + 1) + " " + this.actionMenu[i]);
              }
              int choice = Controller.Actions.MENU.getInt(); //TODO: remove getInt()

              while ((choice = Inputer.readInt()) == Inputer.ERROR_READ_INT) System.out.println("Wrong Input integer! Repeat value : ");

              if (list.size() == 0 || choice > this.actionMenu.length) {

                  if (this.notAvailAbleActions.contains(choice - 1) || choice > this.actionMenu.length) {
                      choice = Controller.Actions.END_ACT.getInt();
                  }
              }
              return choice;
       }
}
