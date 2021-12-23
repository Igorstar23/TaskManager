package ua.edu.sumdu.j2se.igor.tasks;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.controller.MenuController;
import ua.edu.sumdu.j2se.igor.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.Task;
import ua.edu.sumdu.j2se.igor.tasks.model.TaskIO;
import ua.edu.sumdu.j2se.igor.tasks.view.MenuView;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;


public class Main {

	   public static void main(String[] args) throws IOException {

		      LinkedTaskList arr = new LinkedTaskList(new Task[] {
					  		//new Task("First", LocalDateTime.now(), true),
					        //new Task("Two", LocalDateTime.now().plusHours(1), true),
					        //new Task("Three", LocalDateTime.now().minusHours(2), true),
					        //new Task("Four", LocalDateTime.now().minusHours(1), LocalDateTime.now().plusDays(1), 60*60*2,true)
			  });
		      Controller ctr = new MenuController(new MenuView(), arr);
			  ctr.process(arr);
	   }
}