package ua.edu.sumdu.j2se.igor.tasks;

import java.util.ArrayList;

public class ArrayTaskList extends AbstractTaskList {
       private Task[] list;
       private static final int DEF_SIZE = 5;
       private static final int DEF_RATIO = 2;

       public ArrayTaskList() { this.list = new Task[DEF_SIZE]; }

       public ArrayTaskList(Task tsk) {
              this();

              if (tsk == null) throw new IllegalArgumentException("Array tsk must be != null!");

              this.list[0] = tsk;
       }
       public ArrayTaskList(Task[] tasks) {

              if (tasks == null) throw new IllegalArgumentException("Array tsk must be != null!");
              this.list = tasks;
       }


       public void add(Task task) {

              if (task == null) throw new IllegalArgumentException("param task != null!");

              if (this.size() == 0) {
                  this.list[0] = task;
                  return;
              }

              if (this.list.length - this.size() > 0) {
                  this.list[this.size()] = task;
                  return;
              }

              Task[] temp = new Task[this.size() * DEF_RATIO];
              for (int i = 0; i < this.size(); i++) temp[i] = this.list[i];
              temp[this.size()] = task;
              this.list = temp;
       }
       /**
        * For delete task in array
        * @return true if task was deleted or false if the task for delete isn't Found*/
       public boolean remove(Task task) {

              if (task == null) throw new IllegalArgumentException("param task != null!");

              int index = -1;
              for (int i = 0; i < this.size(); i++) {

                   if (this.list[i].equals(task)) {
                       index = i;
                       break;
                   }
              }

              if (index == -1) return false;

              if (index == this.size() - 1) {
                  this.list[this.size() - 1] = null;
                  return true;
              }

              for (int i = index; i < this.size(); i++) {

                   if (i + 1 < this.size()) this.list[i] = this.list[i + 1];
              }
              this.list[this.size() - 1] = null;
              return true;
       }
       /**
       *@return size of array
       */
       public int size() {
              int count = 0;
              for (int i = 0; i < this.list.length; i++) {

                   if (this.list[i] != null) count++;
              }
              return count;
       }
       /**
        * @param index must be >= 0 and  < size of array
        * @return IndexException if index out of size array else Task with need index
        * */
       public Task getTask(int index) {

              if (index < 0 || index >= this.size()) throw new IndexOutOfBoundsException("Param Index must be >= 0 && < size array!");
              return this.list[index];
       }
}
