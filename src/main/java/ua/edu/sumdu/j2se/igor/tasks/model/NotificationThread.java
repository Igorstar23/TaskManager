package ua.edu.sumdu.j2se.igor.tasks.model;

import ua.edu.sumdu.j2se.igor.tasks.model.Notification;

public class NotificationThread extends Thread{
       private Notification ntf;

       public NotificationThread() {
              super();
              this.setDaemon(true);
       }
       public NotificationThread(Notification ntf) {
              this();
              this.ntf = ntf;
       }

       @Override
       public void run() { this.ntf.checkTask(); }

       public Notification getNtf() { return this.ntf; }
       public void setNtf(Notification ntf) { this.ntf = ntf; }
}
