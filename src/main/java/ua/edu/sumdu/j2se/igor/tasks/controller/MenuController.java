package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.AddTaskView;
import ua.edu.sumdu.j2se.igor.tasks.view.View;

import java.util.ArrayList;
import java.util.List;

public class MenuController extends Controller{
       private AbstractTaskList tasks;
       private List<Controller> controllers = new ArrayList<Controller>();

       public MenuController(View menuView, AbstractTaskList tasks) {
              super(menuView, Actions.MENU);
              this.tasks = tasks;

              this.controllers.add(this);
              this.controllers.add(new AddTaskController(new AddTaskView(), Actions.ADD_NEW_TASK));
              //TODO: controllers.add(new NEWTypeCont, Actions.NEED_ACT); Это будет наблюдателем а остальные подписчики:)
       }

       @Override
       public boolean isThatAction(Actions e) { return (this.typeAction.getInt() == e.getInt()); }
       @Override
       public Actions getTypeAction() { return this.typeAction; }
       @Override
       public AbstractTaskList getTaskList() { return this.tasks; }

       @Override
       public int process(AbstractTaskList tasks) {
              int act = this.view.printInfo(tasks);
              for ( ; ; ) {
                   for (var ctr : this.controllers) {
                        act = (ctr.getIntAction() == act)? ctr.process(tasks): act;
                   }

                   if (act == Actions.END_ACT.getInt()) break;
              }
              return Actions.END_ACT.getInt();
       }
}
