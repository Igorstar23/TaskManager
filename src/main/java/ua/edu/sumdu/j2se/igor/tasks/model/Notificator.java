package ua.edu.sumdu.j2se.igor.tasks.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Notificator {
       private AbstractTaskList list;
       private ArrayList<NotificationThread> ntfs;

       public Notificator() { this.ntfs = new ArrayList<NotificationThread>(); }
       public Notificator(AbstractTaskList list) {
              this();
              this.list = list;
       }

       /**
       * For assign threads to list
       * */
       public void assignThreads() { for (var el : list) ntfs.add(new NotificationThread(new Notification(el))); }
       /**
       * For start all threads
       * */
       public void startThreads() { for (var el : ntfs) if (el.getState() == Thread.State.NEW) el.start(); }
       /**
       * For assign thread to Task and start him
       * */
       public void assignThreadToTask(Task task) {
              NotificationThread thread = new NotificationThread(new Notification(task));
              ntfs.add(thread);
              thread.start();
       }
       /**
       * For remove thread for task and deactivate task
       * */
       public void removeThreadForTask(Task task) {
              Iterator i = ntfs.iterator();
              NotificationThread ntfTH = null;
              while (i.hasNext()) {
                     ntfTH = (NotificationThread) i.next();

                     if (ntfTH.getNtf().getTask().equals(task)) break;
              }

              ntfTH.getNtf().setTask(null);
              ntfTH.setNtf(null);
              ntfs.remove(ntfTH);
              task.setActive(false);
       }
}
