package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.view.View;

public class AllCalendarController extends Controller {
      public AllCalendarController(View view) {
             super(view);
             this.typeAction = Actions.SHOW_ALL_CALENDAR;
      }

      public AllCalendarController(View view, Actions act) {
            super(view, act);
      }
}
