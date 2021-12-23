package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.controller.Inputer;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.Task;

import java.time.LocalDateTime;

public class AddTaskView implements View {

       @Override
       public int printInfo(AbstractTaskList list) {
              list.add(this.getTaskFromConsole());
              System.out.println("\nNew task was added!");
              System.out.println(list.getLastTask());
              return Controller.Actions.SAVE.getInt();
       }

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
                  System.out.print("Enter end time of Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                  LocalDateTime end = Inputer.readDateFromLine();
                  System.out.print("Enter interval of Task(minute): ");
                  int interval = Inputer.readInt() * 60;
                  temp.setTime(start, end, interval);

              } else {
                  System.out.print("Enter time of Task(" + Inputer.DateFormat.DEF_POINTS + "): ");
                  temp.setTime(Inputer.readDateFromLine());
              }
              return temp;
       }
}
