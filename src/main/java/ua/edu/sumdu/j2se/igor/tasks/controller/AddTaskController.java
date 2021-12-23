package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.View;

public class AddTaskController extends Controller {

       public AddTaskController(View view) {
              super(view);
              this.typeAction = Actions.ADD_NEW_TASK;
       }

       public AddTaskController(View view, Actions act) {
              super(view, act);
       }

       @Override
       public boolean isThatAction(Actions e) {
              return (this.typeAction.getInt() == e.getInt());
       }

       @Override
       public int process(AbstractTaskList tasks) {
              return super.process(tasks);
       }

       @Override
       public Actions getTypeAction() {
              return super.getTypeAction();
       }

       @Override
       public int getIntAction() {
              return super.getIntAction();
       }
}
