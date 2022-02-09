package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.View;

public class AllCalendarController extends Controller {

      public AllCalendarController(View view) {
             super(view);
             this.typeAction = Actions.SHOW_ALL_CALENDAR;
      }

      public AllCalendarController(View view, Actions act) {
            super(view, act);
      }

      @Override
      public boolean isThatAction(Actions e) {
        return (this.typeAction.getInt() == e.getInt());
    }

      @Override
      public int process(AbstractTaskList tasks) { return super.process(tasks); }
}
