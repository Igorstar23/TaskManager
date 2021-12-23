package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.TaskIO;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;

public class LoadView implements View {
       private static Logger logger = Logger.getLogger(String.valueOf(LoadView.class));
       @Override
       public int printInfo(AbstractTaskList list) {
              System.out.println("Loading saving ...");
              logger.debug("start loading saving ...");
              try {
                   TaskIO.readBinary(list, new File("saving.out"));
              } catch (IOException e) {
                   System.out.println("\nFailed load saving! " + e);
                   logger.debug("Failed load saving : ", e);
                   return Controller.Actions.MENU.getInt();
              }
              System.out.println("\nSaving was loaded!");

              logger.debug("Saving was loaded");
              return Controller.Actions.MENU.getInt();
       }
}
