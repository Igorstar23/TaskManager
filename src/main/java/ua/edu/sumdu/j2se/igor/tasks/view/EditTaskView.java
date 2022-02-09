package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.Task;
import ua.edu.sumdu.j2se.igor.tasks.model.Tasks;

import java.time.LocalDateTime;

public class EditTaskView extends ShowAllTaskView {

       /**
       * For print info need for editing task
       * */
       @Override
       public int printInfo(AbstractTaskList list) {
              super.printInfo(list);
              System.out.print("\nEnter number of Task for edit : ");
              int num = Inputer.readInt();

              while (num < 1 || num > list.size()) {
                     System.out.println("Wrong number!\nEnter number task again : ");
                     num = Inputer.readInt();
              }

              Task oldTask = Tasks.getSortedTasksFromList(list)[num - 1];
              Task editTask = oldTask.clone();
              System.out.println("\nTask '" + editTask.getTitle() + "' was selected for edit!\n");

              if (this.printEditingTask(editTask, false)) {
                  list.remove(oldTask);
                  System.out.println("Task " + oldTask + " was edited to " + editTask + " !");
                  list.add(editTask);

              } else {
                  System.out.println("Task wasn't edit! Returning to global menu...");
              }
              return Controller.Actions.SAVE.getInt();
       }
       /**
       * For print action list need for edit task
       * */
       public void printEditAction(Task editTask) {
              System.out.println("Enter variant edit action : ");
              System.out.println("Enter 1 for rename title");
              System.out.println("Enter 2 for set active");
              System.out.println("Enter 3 for set repeat");

              if (!editTask.isRepeated()) System.out.println("Enter 4 for set time");
              System.out.println("Enter other number not in variants for exit from edit action menu");
       }
       /**
       * For print info for editing and save changing of task
       * */
       public boolean printEditingTask(Task editTask, boolean continued) {
              this.printEditAction(editTask);

              int choice = Inputer.readInt();
              System.out.println();

              switch (choice) {
                     case 1 : System.out.print("Enter new title of Task : ");
                              editTask.setTitle(Inputer.readLine());
                     break;

                     case 2 : System.out.print("Enter active of Task(y/n): ");
                              editTask.setActive(Inputer.readBoolFromLine());
                     break;

                     case 3 : System.out.print("Enter repeat of Task(y/n): ");
                              boolean repeat = Inputer.readBoolFromLine();

                              if (!repeat) {
                                  editTask.resetTimeInterval();
                                  System.out.println("Enter new time for Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                                  editTask.setTime(Inputer.readDateFromLine());

                              } else {
                                  LocalDateTime start, end;
                                  while (true) {
                                      System.out.print("Enter new start time of Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                                      start = Inputer.readDateFromLine();
                                      System.out.print("Enter new end time of Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                                      end = Inputer.readDateFromLine();

                                      if (start.compareTo(end) < 0) break;
                                      System.out.println("Wrong Interval! start must be > end! Enter again :");
                                  }
                                  System.out.print("Enter new interval of Task(minute): ");
                                  int interval = Inputer.readInt() * 60;
                                  editTask.setTime(start, end, interval);
                              }
                     break;

                     case 4 : if (editTask.isRepeated()) {

                                  if (!continued) return false;
                                  System.out.print("Accept changing of Task '" + editTask.getTitle() + "' (y/n)? : ");
                                  return Inputer.readBoolFromLine();
                              }
                              System.out.println("Enter new time for Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                              editTask.setTime(Inputer.readDateFromLine());
                     break;

                     default : if (!continued) return false;
                               System.out.print("Accept changing of Task '" + editTask.getTitle() + "' (y/n)? : ");
                               return Inputer.readBoolFromLine();
              }
              System.out.print("Continue editing(y/n)? : ");

              if (Inputer.readBoolFromLine()) {
                  System.out.println();
                  return this.printEditingTask(editTask, true);

              } else {
                  System.out.print("Accept changing of Task '" + editTask.getTitle() + "' (y/n)? : ");
                  return Inputer.readBoolFromLine();
              }

       }
}
