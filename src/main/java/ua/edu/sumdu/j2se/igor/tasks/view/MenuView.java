package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;

import java.lang.reflect.AccessibleObject;
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
       private int[] notAvailAbleActions = new int[] {
               (Controller.Actions.DELETE_TASK.getInt() - 1),
               (Controller.Actions.EDIT_TASK.getInt() - 1),
               (Controller.Actions.SHOW_ALL_CALENDAR.getInt() - 1),
               (Controller.Actions.SHOW_CALENDAR.getInt() - 1)
       };

       @Override
       public int printInfo(AbstractTaskList list) {
              System.out.println("\nEnter variant action : \n");

              if (list.size() == 0) {
                  int[] availAbleAction = new int[this.actionMenu.length - this.notAvailAbleActions.length];
                  availAbleAction[0] = Controller.Actions.ADD_NEW_TASK.getInt();
                  availAbleAction[1] = Controller.Actions.SHOW_ALL_TASK.getInt();
                  availAbleAction[2] = Controller.Actions.SAVE.getInt();
                  availAbleAction[3] = Controller.Actions.LOAD.getInt();
                  availAbleAction[4] = Controller.Actions.END_ACT.getInt();

                  for (int i = 0; i < availAbleAction.length; i++) {
                       System.out.println("Enter " + (i + 1) + " " + this.actionMenu[availAbleAction[i] - 1]);
                  }
                  int choice = Controller.Actions.MENU.getInt();

                  while ((choice = Inputer.readInt()) <= Inputer.ERROR_READ_INT) System.out.println("Wrong Input integer! Repeat value : ");

                  if (choice > availAbleAction.length) return Controller.Actions.END_ACT.getInt();

                  return (choice > 0)? availAbleAction[choice - 1] : choice;
              }

              for (int i = 0; i < actionMenu.length; i++) System.out.println("Enter " + (i + 1) + " " + this.actionMenu[i]);

              int choice = Controller.Actions.MENU.getInt();

              while ((choice = Inputer.readInt()) <= Inputer.ERROR_READ_INT) System.out.println("Wrong Input integer! Repeat value : ");

              return (choice > this.actionMenu.length)? Controller.Actions.END_ACT.getInt() : choice;
       }
}
