package ua.edu.sumdu.j2se.igor.tasks.view;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.TaskIO;

import java.io.File;
import java.io.IOException;

public class SaveView implements View {

       @Override
       public int printInfo(AbstractTaskList list) {
              System.out.println("\nDo you want to save changing(y/n)? : ");

              if (!Inputer.readBoolFromLine()) return Controller.Actions.MENU.getInt();

              try {
                  Logger.getLogger(SaveView.class).debug("Start writing data...");
                  TaskIO.writeBinary(list, new File("saving.out"));

              } catch (IOException e) {
                  System.out.println("It wasn't save list!");
                  Logger.getLogger(SaveView.class).debug("Didn't write data!", e);
                  return Controller.Actions.SAVE.getInt();
              }
              System.out.println("\nChanging was saved!\n");
              Logger.getLogger(SaveView.class).debug("data was saved");
              return Controller.Actions.MENU.getInt();
       }
}
