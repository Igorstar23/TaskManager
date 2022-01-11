package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.View;

public class CalendarController extends Controller{
       public CalendarController(View view) {
              super(view);
              this.typeAction = Actions.SHOW_CALENDAR;
       }

       public CalendarController(View view, Actions act) { super(view, act); }

       @Override
       public int process(AbstractTaskList tasks) { return super.process(tasks); }
}