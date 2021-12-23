package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.controller.Inputer;
import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.model.Task;
import ua.edu.sumdu.j2se.igor.tasks.model.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CalendarView implements View {

       @Override
       public int printInfo(AbstractTaskList list) {
              System.out.print("\nEnter start of Calendar(" + Inputer.DateFormat.DEF_POINTS + "): ");
              LocalDateTime start = Inputer.readDateFromLine();
              System.out.print("Enter end of Calendar(" + Inputer.DateFormat.DEF_POINTS + "): ");
              LocalDateTime end = Inputer.readDateFromLine();
              System.out.println();
              this.printCalendar(list, start, end);
              return 0;
       }
       public void printCalendar(AbstractTaskList list, LocalDateTime start, LocalDateTime end) {
              for (var el : Tasks.calendar(list, start, end).entrySet()) {
                     System.out.println(el.getKey().format(Inputer.DateFormat.DEF_POINTS.getFormat()) + " : "
                             + el.getValue().stream().map(e->e.toString(Inputer.DateFormat.DEF_POINTS.getFormat()))
                             .collect(Collectors.toList())
                     );
              }
       }
}
