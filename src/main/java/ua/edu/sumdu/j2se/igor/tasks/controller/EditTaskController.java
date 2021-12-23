package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.view.View;

public class EditTaskController extends Controller {

       public EditTaskController(View view) {
              super(view);
              this.typeAction = Actions.EDIT_TASK;
       }

      public EditTaskController(View view, Actions act) {
             super(view, act);
      }
}
