package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.View;

public class ShowAllTaskController extends Controller {

       public ShowAllTaskController(View view, AbstractTaskList list) {
              super(view);
              this.typeAction = Actions.SHOW_ALL_TASK;
       }

       public ShowAllTaskController(View view, Actions act) {
             super(view, act);
       }

       @Override
       public int process(AbstractTaskList tasks) { return super.process(tasks); }
}