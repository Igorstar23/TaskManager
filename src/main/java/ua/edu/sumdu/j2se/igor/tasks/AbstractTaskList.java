package ua.edu.sumdu.j2se.igor.tasks;

public abstract class AbstractTaskList {
       abstract public void add(Task task);
       abstract public Task getTask(int index);
       abstract public boolean remove(Task task);
       abstract public int size();

       /**
        * For get Array Task from current list
        * @param from must be >= 0 else IllegalArgException
        * @param to must be >= 0 else IllegalArgException
        * @return Array of Tasks from the current list Where Tasks be in time interval [from, to]*/
       public AbstractTaskList incoming(int from, int to) {

              if (from < 0 || to < 0) throw new IllegalArgumentException("Params 'from' and 'to' must are >= 0");

              AbstractTaskList temp = TaskListFactory.createTaskList(ListTypes.getTypeList(this));

              for (int i = 0; i < this.size(); i++) {

                     if (this.getTask(i).nextTimeAfter(from) != -1 && this.getTask(i).nextTimeAfter(from) <= to) {
                            temp.add(this.getTask(i));
                     }
              }
              return temp;
       }
}
