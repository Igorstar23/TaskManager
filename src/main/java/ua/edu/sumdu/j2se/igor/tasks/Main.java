package ua.edu.sumdu.j2se.igor.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.controller.MenuController;
import ua.edu.sumdu.j2se.igor.tasks.model.*;
import ua.edu.sumdu.j2se.igor.tasks.view.MenuView;

import java.io.IOException;

public class Main {
	   private static final Logger LOGGER = Logger.getLogger(Main.class);

	   public static void main(String[] args) throws IOException, InterruptedException {
		      LinkedTaskList arr = new LinkedTaskList(new Task[] {});
			  Notificator ntf = new Notificator(arr);
			  arr.setNotificator(ntf);
			  ntf.assignThreads();
			  ntf.startThreads();
			  Controller ctr = new MenuController(new MenuView(), arr);
			  LOGGER.debug("Start process ...");
			  ctr.process(arr);
			  LOGGER.debug("End process ...");
	   }
}