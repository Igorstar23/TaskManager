package ua.edu.sumdu.j2se.igor.tasks.view;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;

import java.time.LocalDateTime;

public class AllCalendarView extends CalendarView {

       @Override
       public int printInfo(AbstractTaskList list) {
              LocalDateTime start = (list.getMinTask().isRepeated())? list.getMinTask().getStartTime().minusDays(1)
                      : list.getMinTask().getTime().minusDays(30);
              LocalDateTime end = (list.getMaxTask().isRepeated())? list.getMaxTask().getEndTime().plusDays(1)
                      : list.getMaxTask().getTime().plusDays(30);
              this.printCalendar(list, start, end);
              return 0;
       }
}