package ua.edu.sumdu.j2se.igor.tasks;

public class LinkedTaskList {
       private Node First = null;
       private Node Last = null;

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
        * Fro add Task to list
        * @param task must be != null else IllegalArgException
        */
       void add(Task task) {

            if (task == null) throw new IllegalArgumentException("param task != null!");

            if (this.isEmpty()) {
                this.First = new Node(task);
                this.Last = First;
                return;
            }

            if (this.Last == this.First) {
                this.Last = new Node(task, this.First);
                this.First.setNextNode(this.Last);
                return;
            }

            this.Last.setNextNode(new Node(task, this.Last));
            this.Last = this.Last.getNextNode();
       }
       /**
       * @param index must be >= 0 and  < size of list
       * @return IndexException if index out of size list else Task with need index
       * */
       public Task getTask(int index) {

              if (index < 0 || index >= this.size()) throw new IndexOutOfBoundsException("Param Index must be >= 0 && < size of list!");

              if (index == 0) return this.First.getItem();

              if (index == this.size() - 1) return this.Last.getItem();

              Node needNode = this.First;
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

              if (this.First.item.equals(task)) {
                  this.First = this.First.getNextNode();

                  if (this.First != null) this.First.setPrevNode(null);
                  return true;
              }

              if (this.Last.item.equals(task)) {
                  this.Last = this.Last.getPrevNode();

                  if (this.Last != null) this.Last.setNextNode(null);
                  return true;
              }

              Node oldNode = this.First.getNextNode();
              boolean isFound = false;
              for (; oldNode != this.Last; oldNode = oldNode.getNextNode()) {

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
       public boolean remove() { return !this.isEmpty() && this.remove(this.Last.item); }
       /**
       *@return size of list
       */
       public int size() {

              if (this.isEmpty()) return 0;
              int count = 0;
              for (Node cur = this.First; cur != this.Last; count++, cur = cur.getNextNode());

              if (this.Last != null) count++;
              return count;
       }
       public boolean isEmpty() { return (this.First == null && this.Last == null); }
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