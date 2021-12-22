package ua.edu.sumdu.j2se.igor.tasks;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.controller.Inputer;
import ua.edu.sumdu.j2se.igor.tasks.controller.MenuController;
import ua.edu.sumdu.j2se.igor.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.MenuView;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.atomic.LongAccumulator;

public class Main {

	   public static void main(String[] args) {
		      /*
		      LinkedTaskList arr = new LinkedTaskList();
		      Controller ctr = new MenuController(new MenuView(), arr);
			  ctr.process(arr);
			  */
		      LocalDateTime t = Inputer.readDateFromString("15-07-2004-15:37");
		      System.out.println(t);
	   }
}