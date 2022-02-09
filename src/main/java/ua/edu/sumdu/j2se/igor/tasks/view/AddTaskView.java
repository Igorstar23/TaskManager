package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.Task;

import java.time.LocalDateTime;

public class AddTaskView implements View {

       /**
       * For add task to list and print added task
       * */
       @Override
       public int printInfo(AbstractTaskList list) {
              list.add(this.getTaskFromConsole());
              System.out.println("\nNew task was added!");
              System.out.println(list.getLastTask());
              return Controller.Actions.SAVE.getInt();
       }
       /**
       * For enter task from console
       * */
       public Task getTaskFromConsole() {
              Task temp = new Task("Def");

              System.out.println("\nEnter new Task : \n");
              System.out.print("Enter title fo Task : ");
              temp.setTitle(Inputer.readLine());

              System.out.print("Enter active of Task (y/n): ");
              temp.setActive(Inputer.readBoolFromLine());

              System.out.print("Enter repeat of Task (y/n): ");

              if (Inputer.readBoolFromLine()) {
                  System.out.print("Enter start time of Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                  LocalDateTime start = Inputer.readDateFromLine();

                  while (!this.isCorrectDate(start)) {
                         System.out.println("\nEntered date isn't correct! Input date must be future or now");
                         System.out.print("Enter again the start time of Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                         start = Inputer.readDateFromLine();
                  }

                  System.out.print("Enter end time of Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                  LocalDateTime end = Inputer.readDateFromLine();

                  while (!this.isCorrectDate(end)) {
                         System.out.println("\nEntered date isn't correct! Input date must be future or now");
                         System.out.print("Enter again the end time of Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                         end = Inputer.readDateFromLine();
                  }

                  System.out.print("Enter interval of Task(minute): ");
                  int interval = Inputer.readInt() * 60;
                  temp.setTime(start, end, interval);

              } else {
                  System.out.print("Enter time of Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                  LocalDateTime date = Inputer.readDateFromLine();

                  while(!this.isCorrectDate(date)) {
                        System.out.println("\nEntered date isn't correct! Input date must be future or now");
                        System.out.print("Enter again the time of Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                        date = Inputer.readDateFromLine();
                  }
                  temp.setTime(date);
              }
              return temp;
       }

       /**
       * @return false if date is null or be <= now time else true
       * */
       public boolean isCorrectDate(LocalDateTime date) {

              if (date == null) return false;
              return Inputer.compareDates(LocalDateTime.now(), date) <= 0;
       }
}
