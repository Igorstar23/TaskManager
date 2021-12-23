package ua.edu.sumdu.j2se.igor.tasks.controller;

import ua.edu.sumdu.j2se.igor.tasks.view.View;

public class SaveController extends Controller {

       public SaveController(View view) {
              super(view);
              this.typeAction = Actions.SAVE;
       }

       public SaveController(View view, Actions act) {
              super(view, act);
       }
}
