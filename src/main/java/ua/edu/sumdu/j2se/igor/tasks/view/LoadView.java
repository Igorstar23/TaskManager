package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.ListTypes;
import ua.edu.sumdu.j2se.igor.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.igor.tasks.model.TaskListFactory;

import java.io.File;
import java.io.IOException;

public class LoadView implements View {

       @Override
       public int printInfo(AbstractTaskList list) {
              System.out.println("Loading saving ...");

              try {
                   TaskIO.readBinary(list, new File("saving.out"));
              } catch (IOException e) {
                   System.out.println("\nFailed load saving! " + e);
                   return Controller.Actions.MENU.getInt();
              }
              System.out.println("\nSaving was loaded!");
              return Controller.Actions.MENU.getInt();
       }
}
