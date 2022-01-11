package ua.edu.sumdu.j2se.igor.tasks.view;

//import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.controller.Inputer;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.TaskIO;

import java.io.File;
import java.io.IOException;

public class SaveView implements View {
       //private static Logger logger = Logger.getLogger(SaveView.class);

       @Override
       public int printInfo(AbstractTaskList list) {
              System.out.println("\nDo you want to save changing(y/n)? : ");

              if (!Inputer.readBoolFromLine()) return Controller.Actions.MENU.getInt();

              try {
                  //logger.debug("Start writing data...");
                  TaskIO.writeBinary(list, new File("saving.out"));

              } catch (IOException e) {
                  System.out.println("It wasn't save list!");
                  //logger.debug("Didn't write data!", e);
                  return Controller.Actions.SAVE.getInt();
              }
              System.out.println("\nChanging was saved!\n");
              //logger.debug("data was saved");
              return Controller.Actions.MENU.getInt();
       }
}
