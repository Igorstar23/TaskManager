package ua.edu.sumdu.j2se.igor.tasks.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList{
       private Node first;
       private Node last;

       private class Node {
               private Task item;
               private Node nextNode; // next move to Last
               private Node prevNode; // prev move to First

               public Node() {}
               /**
                 * @param task must be not null
                 * @throws IllegalArgumentException if param task is null
               * */
               public Node(Task task) {

                      if (task == null) throw new IllegalArgumentException("param task is null!");
                      this.item = task;
               }
               public Node(Task task, Node prevNode) {
                      this(task);
                      this.prevNode = prevNode;
               }

               public void setNextNode(Node nextNode) {
                      this.nextNode = nextNode;
               }
               public void setPrevNode(Node prevNode) {
                      this.prevNode = prevNode;
               }
               public Node getNextNode() { return this.nextNode; }
               public Node getPrevNode() { return this.prevNode; }
               public Task getItem() { return this.item; }
       }

       public LinkedTaskList() {}
       /**
        * @param task must be != null
        * @throws IllegalArgumentException if param task is null
       */
       public LinkedTaskList(Task task) {

              if (task == null) throw new IllegalArgumentException("Param task is null!");
              this.add(task);
       }
       /**
         * @param tasks must be != null
         * @throws IllegalArgumentException if param tasks is null
       * */
       public LinkedTaskList(Task[] tasks) {

              if (tasks == null) throw new IllegalArgumentException("Param array tasks is null!");
              for (int i = 0; i < tasks.length; i++) this.add(tasks[i]);
       }
       /**
         * For add Task to list
         * @param task must be != null
         * @throws IllegalArgumentException if param task is null
       * */
       @Override
       public void add(Task task) {

            if (task == null) throw new IllegalArgumentException("param task is null!");

            if (this.isEmpty()) {
                this.first = new Node(task);
                this.last = first;
                return;
            }

            if (this.last == this.first) {
                this.last = new Node(task, this.first);
                this.first.setNextNode(this.last);
                return;
            }

            this.last.setNextNode(new Node(task, this.last));
            this.last = this.last.getNextNode();
       }
       /**
       * @param index must be >= 0 and < size of list
       * @return Task with need index from this list
       * @throws IndexOutOfBoundsException if index < 0 or >= size of this list
       * */
       @Override
       public Task getTask(int index) {

              if (index < 0) throw new IndexOutOfBoundsException("Param Index < 0!");

              if (index >= this.size()) throw new IndexOutOfBoundsException("Param index >= size of this List!");

              if (index == 0) return this.first.getItem();

              if (index == this.size() - 1) return this.last.getItem();

              Node needNode = this.first;
              for (int i = 0; i < index; i++) needNode = needNode.getNextNode();
              return needNode.getItem();
       }
       @Override
       public Task getLastTask() { return this.last.item; }
       @Override
       public Task getFirstTask() { return this.first.item; }

       /**
         * For delete task from list
         * @param task must be != null
         * @throws IllegalArgumentException if param task is null
         * @return true if task was deleted or false if the task for delete isn't Found
       * */
       @Override
       public boolean remove(Task task) {

              if (task == null) throw new IllegalArgumentException("param task is null!");

              if (this.isEmpty()) return false;

              if (this.first == this.last) {

                  if (!this.first.item.equals(task)) return false;
                  this.first = null;
                  this.last = null;
                  return true;
              }

              if (this.first.item.equals(task)) {
                  this.first = this.first.getNextNode();

                  if (this.first != null) this.first.setPrevNode(null);
                  return true;
              }

              if (this.last.item.equals(task)) {
                  this.last = this.last.getPrevNode();

                  if (this.last != null) this.last.setNextNode(null);
                  return true;
              }

              Node oldNode = this.first.getNextNode();
              boolean isFound = false;
              for (; oldNode != this.last; oldNode = oldNode.getNextNode()) {

                   if (oldNode.item.equals(task)) {
                       isFound = true;
                       break;
                   }
              }

              if (!isFound) return false;

              // Node prevOld = oldNode.getPrevNode();
              // Node nextOld = oldNode.getNextNode();
              // prevOld.setNextNode(nextOld);
              oldNode.getPrevNode().setNextNode(oldNode.getNextNode());

              // nextOld.setPrevNode(prevOld);
              oldNode.getNextNode().setPrevNode(oldNode.getPrevNode());
              oldNode = null;
              return true;
       }
       /**
        * For delete task from end*/
       public boolean remove() { return !this.isEmpty() && this.remove(this.last.item); }
       /**
       *@return size of list
       */
       public int size() {

              if (this.isEmpty()) return 0;
              int count = 0;

              for (Node cur = this.first; cur != this.last; count++, cur = cur.getNextNode());

              if (this.last != null) count++;
              return count;
       }
       public boolean isEmpty() { return (this.first == null && this.last == null); }



       /**
         * Returns an iterator over elements of type {@code T}.
         * @throws IllegalStateException if iterator be on null element
         * @return an Iterator.
         */
       @Override
       public Iterator<Task> iterator() {
              return new Iterator() {
                     private int index = -1;

                     @Override
                     public boolean hasNext() {
                            return (index + 1 < size());
                     }

                     @Override
                     public Task next() {
                            return getTask(++index);
                     }


                     @Override
                     public void remove() {

                            if (index < 0) throw new IllegalStateException("iterator be on null element!");
                            LinkedTaskList.this.remove(getTask(index));
                            --index;
                     }
              };
       }
       /**
        * @return a hash code value for this object.
        * @implSpec As far as is reasonably practical, the {@code hashCode} method defined
        * by class {@code Object} returns distinct integers for distinct objects.
        * @see Object#equals(Object)
        * @see System#identityHashCode
       */
       @Override
       public int hashCode() { return super.hashCode(); }
       @Override
       public String toString() {
              String res = " ";
              for (var el : this) res += el.toString() + " ";
              return res;
       }
       @Override
       public Stream<Task> getStream() {
              Task[] tasks = new Task[this.size()];
              for (int i = 0; i < this.size(); i++) tasks[i] = this.getTask(i);
              return Arrays.stream(tasks);
       }
       @Override
       public LinkedTaskList clone() {
              return (LinkedTaskList) super.clone();
       }
}