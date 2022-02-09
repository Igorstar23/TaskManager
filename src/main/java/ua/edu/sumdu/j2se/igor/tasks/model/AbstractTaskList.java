package ua.edu.sumdu.j2se.igor.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.igor.tasks.view.DeleteTaskView;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {
       private static final Logger LOGGER = Logger.getLogger(AbstractTaskList.class);
       abstract public Task getTask(int index);
       abstract public boolean remove(Task task);
       abstract public int size();
       abstract public Task getFirstTask();
       abstract public Task getLastTask();

       abstract public void add(Task task);
       abstract public void setNotificator(Notificator ntf);
       public void addAll(Task[] tasks) { Arrays.stream(tasks).forEach(this::add); }
       public void addAllFromList(AbstractTaskList tasks) { tasks.getStream().forEach(this::add); }

       /**
        * @return Task with min date from list
        * */
       public Task getMinTask() {
              Task min = this.getFirstTask();
              for (Iterator<Task> i = this.iterator(); i.hasNext(); ) {
                   Task next = i.next();
                   LocalDateTime t1 = (min.isRepeated())? min.getStartTime() : min.getTime();
                   LocalDateTime t2 = (next.isRepeated())? next.getStartTime() : next.getTime();

                   if (t1.compareTo(t2) > 0) min = next;
              }
              return min;
       }
       /**
         * @return Task with max date from list
       * */
       public Task getMaxTask() {
              Task max = this.getFirstTask();
              for (Iterator<Task> i = this.iterator(); i.hasNext(); ) {
                   Task next = i.next();
                   LocalDateTime t1 = (max.isRepeated())? max.getEndTime() : max.getTime();
                   LocalDateTime t2 = (next.isRepeated())? next.getEndTime() : next.getTime();

                   if (t1.compareTo(t2) < 0) max = next;
              }
              return max;
       }


       /**
        * For get Array Task from current list
        * @param from must be >= 0
        * @param to must be >= 0
        * @throws IllegalArgumentException if param 'from' or 'to' is null
        * @return Array of Tasks from the current list Where Tasks be in time interval [from, to]*/
       public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) {

              if (from == null) throw new IllegalArgumentException("Param 'from' is null!");

              if (to == null) throw new IllegalArgumentException("Param 'to' is null!");
              AbstractTaskList temp = TaskListFactory.createTaskList(ListTypes.getTypeList(this));
              this.getStream().filter(x -> x.nextTimeAfter(from) != null && x.nextTimeAfter(from).compareTo(to) <= 0).forEach(temp::add);
              return temp;
       }

       /**
        * Indicates whether some other object is "equal to" this one.
        * <p>
        * The {@code equals} method implements an equivalence relation
        * on non-null object references:
        * <ul>
        * <li>It is <i>reflexive</i>: for any non-null reference value
        *     {@code x}, {@code x.equals(x)} should return
        *     {@code true}.
        * <li>It is <i>symmetric</i>: for any non-null reference values
        *     {@code x} and {@code y}, {@code x.equals(y)}
        *     should return {@code true} if and only if
        *     {@code y.equals(x)} returns {@code true}.
        * <li>It is <i>transitive</i>: for any non-null reference values
        *     {@code x}, {@code y}, and {@code z}, if
        *     {@code x.equals(y)} returns {@code true} and
        *     {@code y.equals(z)} returns {@code true}, then
        *     {@code x.equals(z)} should return {@code true}.
        * <li>It is <i>consistent</i>: for any non-null reference values
        *     {@code x} and {@code y}, multiple invocations of
        *     {@code x.equals(y)} consistently return {@code true}
        *     or consistently return {@code false}, provided no
        *     information used in {@code equals} comparisons on the
        *     objects is modified.
        * <li>For any non-null reference value {@code x},
        *     {@code x.equals(null)} should return {@code false}.
        * </ul>
        * <p>
        * The {@code equals} method for class {@code Object} implements
        * the most discriminating possible equivalence relation on objects;
        * that is, for any non-null reference values {@code x} and
        * {@code y}, this method returns {@code true} if and only
        * if {@code x} and {@code y} refer to the same object
        * ({@code x == y} has the value {@code true}).
        * <p>
        * Note that it is generally necessary to override the {@code hashCode}
        * method whenever this method is overridden, so as to maintain the
        * general contract for the {@code hashCode} method, which states
        * that equal objects must have equal hash codes.
        *
        * @param obj the reference object with which to compare.
        * @return {@code true} if this object is the same as the obj
        * argument; {@code false} otherwise.
        * @see #hashCode()
        * @see HashMap
        */
       @Override
       public boolean equals(Object obj) {

              if (this == obj) return true;

              if (!(obj instanceof AbstractTaskList)) return false;
              AbstractTaskList objATL = (AbstractTaskList)obj;

              if (objATL.size() != this.size()) return false;
              for (int i = 0; i < this.size(); i++) if (!this.getTask(i).equals(objATL.getTask(i))) return false;
              return true;
       }
       /**
        * Return copy of current object*/
       @Override
       protected AbstractTaskList clone() {
                 AbstractTaskList temp = null;
                 try {
                      temp = TaskListFactory.createTaskList(ListTypes.getTypeList(this));
                 } catch (Exception e) {
                      LOGGER.error(e);
                      e.printStackTrace();
                 }
                 for (int i = 0; i < this.size(); i++) temp.add(this.getTask(i).clone());
                 return temp;
       }
       /**
        * @return a hash code value for this object.
        * @implSpec As far as is reasonably practical, the {@code hashCode} method defined
        * by class {@code Object} returns distinct integers for distinct objects.
        * @see Object#equals(Object)
        * @see System#identityHashCode
       */
       @Override
       public int hashCode() {
             int hash = 1;
             for (var el : this) {
                  hash *= 23;
                  hash += (el == null)? 0 : el.hashCode();
             }
             return hash;
      }
       /**
        * For get Stream Tasks for this Collection*/
       abstract public Stream<Task> getStream();
}
