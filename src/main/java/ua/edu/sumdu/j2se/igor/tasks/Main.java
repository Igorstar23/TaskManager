package ua.edu.sumdu.j2se.igor.tasks;

import ua.edu.sumdu.j2se.igor.tasks.controller.Controller;
import ua.edu.sumdu.j2se.igor.tasks.controller.Inputer;
import ua.edu.sumdu.j2se.igor.tasks.controller.MenuController;
import ua.edu.sumdu.j2se.igor.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.LinkedTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.MenuView;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedList;
import java.util.Locale;
import java.util.concurrent.atomic.LongAccumulator;

public class Main {

	   public static void main(String[] args) {

		      LinkedTaskList arr = new LinkedTaskList();
		      Controller ctr = new MenuController(new MenuView(), arr);
			  ctr.process(arr);

		      /*System.out.println(Inputer.DateFormat.DEF_POINTS.toString());
		   	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Inputer.DateFormat.DEF_POINTS.toString()).withZone(ZoneId.systemDefault());
		      LocalDateTime t =  ZonedDateTime.parse("17.05.2001|18:00", formatter).toLocalDateTime();
		      System.out.println(t.format(formatter));*/
	   }
}