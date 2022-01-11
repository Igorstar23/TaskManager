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
                   SHOW_ALL_TASK { public int getInt() { return 4; } },
                   SHOW_ALL_CALENDAR { public  int getInt() { return 5; } },
                   SHOW_CALENDAR { public int getInt() { return 6; } },
                   SAVE { public int getInt() { return 7; } },
                   LOAD { public int getInt() {return 8; } },
                   END_ACT { public int getInt() { return 9; } };

                   public abstract int getInt();
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
       public boolean isThatAction(Actions e) { return (this.typeAction.equals(e)); }
}
