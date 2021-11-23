package ua.edu.sumdu.j2se.igor.tasks;

public class LinkedTaskList {
       private Node first;
       private Node last;

       private class Node {
               private Task item;
               private Node nextNode; // next move to Last
               private Node prevNode; // prev move to First

               public Node() {}
               public Node(Task task) {

                      if (task == null) throw new IllegalArgumentException("param task != null!");
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
        * @param task must be != null else IllegalArgException
       */
       public LinkedTaskList(Task task) {

              if (task == null) throw new IllegalArgumentException("Param task != null!");
              this.add(task);
       }
       /**
       * @param tasks must be != null else IllegalArgException
       */
       public LinkedTaskList(Task[] tasks) {

              if (tasks == null) throw new IllegalArgumentException("Param array tasks != null!");
              for (int i = 0; i < tasks.length; i++) this.add(tasks[i]);
       }
       /**
        * For add Task to list
        * @param task must be != null else IllegalArgException
        */
       public void add(Task task) {

            if (task == null) throw new IllegalArgumentException("param task != null!");

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
       * @param index must be >= 0 and  < size of list
       * @return IndexException if index out of size list else Task with need index
       * */
       public Task getTask(int index) {

              if (index < 0 || index >= this.size()) throw new IndexOutOfBoundsException("Param Index must be >= 0 && < size of list!");

              if (index == 0) return this.first.getItem();

              if (index == this.size() - 1) return this.last.getItem();

              Node needNode = this.first;
              for (int i = 0; i < index; i++) needNode = needNode.getNextNode();
              return needNode.getItem();
       }
       /**
       * For delete task from list
       * @param task must be != null else IllegalArgException
       * @return true if task was deleted or false if the task for delete isn't Found*/
       public boolean remove(Task task) {

              if (task == null) throw new IllegalArgumentException("param task != null!");

              if (this.isEmpty()) return false;

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
       * For get Array Task from current list
       * @param from must be >= 0 else IllegalArgException
       * @param to must be >= 0 else IllegalArgException
       * @return Array of Tasks from the current list Where Tasks be in time interval [from, to]*/
       public LinkedTaskList incoming(int from, int to) {

              if (from < 0 || to < 0) throw new IllegalArgumentException("Params 'from' and 'to' must are >= 0");

              LinkedTaskList temp = new LinkedTaskList();

              for (int i = 0; i < this.size(); i++) {

                   if (this.getTask(i).nextTimeAfter(from) != -1 && this.getTask(i).nextTimeAfter(from) <= to) {
                       temp.add(this.getTask(i));
                   }
              }
              return temp;
       }
}