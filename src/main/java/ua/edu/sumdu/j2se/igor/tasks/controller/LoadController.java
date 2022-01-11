package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.View;

import java.util.logging.Logger;

public class LoadController extends Controller {
       //private static Logger logger = Logger.getLogger(String.valueOf(LoadController.class));

       public LoadController(View view) {
              super(view);
              this.typeAction = Actions.LOAD;
       }

       public LoadController(View view, Actions act) {
              super(view, act);
       }

       @Override
       public int process(AbstractTaskList tasks) { return super.process(tasks); }
}
