package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.View;

public class DeleteTaskController extends Controller {
       public DeleteTaskController(View view) {
              super(view);
              this.typeAction = Actions.DELETE_TASK;
       }

       public DeleteTaskController(View view, Actions act) {
              super(view, act);
       }
}
