package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.controller.Inputer;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;

import java.io.IOException;


public class MenuView implements View{

       @Override
       public int printInfo(AbstractTaskList list) {
              System.out.println("\nEnter variant action : \n");
              System.out.println("Enter 1 for add new task to your task list");
              System.out.println("Enter 2 for edit task");
              System.out.println("Enter 3 for delete task from your task list");
              System.out.println("Enter 4 for show all tasks");
              System.out.println("Enter 5 for show calendar");
              System.out.println("Enter -1 for exit");
              int choice = Controller.Actions.MENU.getInt();

              choice = Inputer.readInt();
              return choice;
       }
}
