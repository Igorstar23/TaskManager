package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.*;

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
              this.controllers.add(new ShowAllTaskController(new ShowAllTaskView(), Actions.SHOW_ALL_TASK));
              this.controllers.add(new CalendarController(new CalendarView(), Actions.SHOW_CALENDAR));
              this.controllers.add(new AllCalendarController(new AllCalendarView(), Actions.SHOW_ALL_CALENDAR));
              this.controllers.add(new DeleteTaskController(new DeleteTaskView(), Actions.DELETE_TASK));
              this.controllers.add(new EditTaskController(new EditTaskView(), Actions.EDIT_TASK));
              //TODO: controllers.add(new NEWTypeCont, Actions.NEED_ACT); Это будет наблюдателем, а остальные подписчики:)
       }

       @Override
       public boolean isThatAction(Actions e) { return (this.typeAction.getInt() == e.getInt()); }
       @Override
       public Actions getTypeAction() { return this.typeAction; }

       public AbstractTaskList getTaskList() { return this.tasks; }

       @Override
       public int process(AbstractTaskList tasks) {
              int act = this.view.printInfo(tasks);
              for ( ; ; ) {
                   for (var ctr : this.controllers) {
                        act = (ctr.getIntAction() == act)? ctr.process(tasks): act;
                   }

                   if (act == Actions.END_ACT.getInt() || act != 0) break; //TODO : delete act != 0
              }
              return Actions.END_ACT.getInt();
       }
}
