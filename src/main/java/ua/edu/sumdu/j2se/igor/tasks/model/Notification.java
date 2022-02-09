package ua.edu.sumdu.j2se.igor.tasks.model;

import java.time.LocalDateTime;

public class Notification {
       private Task task;
       private LocalDateTime[] notifyDates;

       public Notification() {}
       public Notification(Task task) {
              this.task = task;
              this.updateDates();
       }

       public void updateDates() { this.notifyDates = (task != null)? task.getAllNotifyDates() : this.notifyDates; }
       public void setTask(Task task) {
              this.task = task;
              this.updateDates();
       }

       public Task getTask() { return task; }
       public boolean isActual(LocalDateTime nowTime) {

              if (task == null) return false;

              if (!task.isActive()) return false;
              LocalDateTime endTime = task.getEndTime();
              return (compareTimes(nowTime, endTime) <= 0);
       }
       public boolean isTimeToNotify(LocalDateTime time) {
              for (var el : notifyDates) {

                   if (timesEquals(el, time)) return true;
              }
              return false;
       }

       /**
       * @param t is not null
       * @param t2 is not null
       * @throws IllegalArgumentException if param t or t2 is null
       * @return -1 if t < t2, zero if t = t2 else 1
       * */
       public static int compareTimes(LocalDateTime t, LocalDateTime t2) {

              if (t == null || t2 == null) throw new IllegalArgumentException("Param t or t2 is null!");

              if (t.equals(t2)) return 0;

              if (t.getYear() < t2.getYear()) return -1;

              if (t.getYear() > t2.getYear()) return 1;

              if (t.getMonthValue() < t2.getMonthValue()) return -1;

              if (t.getMonthValue() > t2.getMonthValue()) return 1;

              if (t.getDayOfMonth() < t2.getDayOfMonth()) return -1;

              if (t.getDayOfMonth() > t2.getDayOfMonth()) return 1;

              if (t.getHour() < t2.getHour()) return -1;

              if (t.getHour() > t2.getHour()) return 1;

              if (t.getMinute() < t2.getMinute()) return -1;

              if (t.getMinute() > t2.getMinute()) return 1;

              return 0;
       }
       public static boolean timesEquals(LocalDateTime t, LocalDateTime t2) {

              if (t == null || t2 == null) return false;
              return (compareTimes(t, t2) == 0);
       }

       public void toNotify(LocalDateTime notifyTime) {
              System.out.println("\n\nTime to go!!! : " + notifyTime + "\nFor task : " + task);

              for (int i = 0; i < notifyDates.length; i++) {

                   if (timesEquals(notifyTime, notifyDates[i])) {
                        notifyDates[i] = null;
                        break;
                   }
              }
       }
       public void checkTask() {
              LocalDateTime time = LocalDateTime.now();
              while (this.isActual(time)) {

                     if (this.isTimeToNotify(time)) this.toNotify(time);
                     time = LocalDateTime.now();
              }
       }

}
