package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.View;

public class ExitController extends SaveController {

       public ExitController(View view) {
              super(view);
              this.typeAction = Actions.END_ACT;
       }

       public ExitController(View view, Actions act) {
              super(view, act);
       }

       @Override
       public int process(AbstractTaskList tasks) {
              super.process(tasks);
              return Actions.END_ACT.getInt();
       }

}
