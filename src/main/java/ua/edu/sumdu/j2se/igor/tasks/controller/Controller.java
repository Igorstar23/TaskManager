package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.igor.tasks.view.View;

public abstract class Controller {
       protected View view;
       protected Actions typeAction = Actions.MENU;
       public enum Actions {
                   MENU { public int getInt() { return 0;} },
                   ADD_NEW_TASK { public int getInt() { return 1; } },
                   EDIT_TASK { public int getInt() { return 2; } },
                   DELETE_TASK { public int getInt() { return 3; } },
                   SHOW_ALL_TASk { public int getInt() { return 4; } },
                   SHOW_CALENDAR { public int getInt() { return 5; } },
                   END_ACT { public int getInt() { return 6; } };

                   public abstract int getInt();
                   public Actions getTypeInt(int type) {

                          switch (type) {
                                case 0 : return Actions.MENU;
                                case 1 : return Actions.ADD_NEW_TASK;
                                case 2 : return Actions.EDIT_TASK;
                                case 3 : return Actions.DELETE_TASK;
                                case 4 : return Actions.SHOW_ALL_TASk;
                                case 5 : return Actions.SHOW_CALENDAR;
                                default : return Actions.END_ACT;
                          }
                   }
       }

       public Controller(View view) {
              this.view = view;
       }
       public Controller(View view, Actions act) {
              this.view = view;
              this.typeAction = act;
       }

       public void setTypeAction(Actions act) { this.typeAction = act; }

       public int process(AbstractTaskList tasks) { return view.printInfo(tasks); }
       public Actions getTypeAction() { return this.typeAction; }
       public int getIntAction() { return this.typeAction.getInt(); }
       public abstract AbstractTaskList getTaskList();
       public abstract boolean isThatAction(Actions e);
}
