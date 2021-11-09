package ua.edu.sumdu.j2se.igor.tasks;

import java.util.Objects;

public class Task {
       private String title;
       private boolean active;
       private boolean repeat;
       private int time;
       private int start;
       private int end;
       private int interval;

       public Task(String title) {
              this(title, 0);
       }
       /**
        * Constructor for non-active and non-repetitive task
        * */
       public Task(String title, int time) {
              this.title = title;
              this.time = time;
              this.active = false;
              this.repeat = false;
       }

	   /**
	    * Constructor for non-active and repetitive task
	    * */
       public Task(String title, int start, int end, int interval) {
              this.title = title;
              this.start = start;
              this.end = end;
              this.interval = interval;
              this.active = false;
              this.repeat =  true;
              this.time = this.nextTimeAfter(this.start);
       }

       /**
        * @param title isn't null and empty else set value "Title is None!"
        * */
	   public void setTitle(String title) { this.title = (title != null && !title.isEmpty())? title : "Title is None!"; }
       public void setActive(boolean active) { this.active = active; }
       /**
        * Set time for non-repetitive task
        * if the task is repetitive then reset the repetition time interval and turn off repeat of the task
        * */
       public void setTime(int time) {
              this.time = time;

              if (this.isRepeated()) this.resetTimeInterval();
       }
       /**
        * Set time for repetitive task
        * if task is non-repetitive then turn on repeat for the task
        * @param start must be < end and >= 0
        * @param end must be > start and >= 0
        * @param interval must be > 0
        * */
       public void setTime(int start, int end, int interval) {
              this.start = start;
              this.end = end;
              this.interval = interval;
              this.repeat = true;
              this.time = this.nextTimeAfter(this.start);
       }

       public String getTitle() { return this.title; }
       public boolean isActive() { return this.active; }
       public boolean isRepeated() { return this.repeat; }
       /**
        * Get time for non-repetitive task
        * @return the time of task or time start of the repetition time interval for repetitive task
        * */
       public int getTime() { return (this.isRepeated())? this.start : this.time; }
       /**
        * @return the time start of the repetition time interval or the time of task for non-repetitive task
        * */
       public int getStartTime() { return (this.isRepeated())? this.start : this.time; }
       /**
        * @return the time end of the repetition time interval or the time of task for non-repetitive task
        * */
       public int getEndTime() { return (this.isRepeated())? this.end : this.time; }
       /**
        * @return value of interval or 0 for non-repetitive task
        * */
       public int getRepeatInterval() { return (this.isRepeated())? this.interval : 0; }

       /**
        * For reset the repetition time interval and turn off repeat of the task
        * */
       public void resetTimeInterval() {
              this.start = 0;
              this.end = 0;
              this.interval = 0;
              this.repeat = false;
       }
       /**
        * @return next time of task or -1 if next time of task is out of the time interval or the task is non-active
        * */
       public int nextTimeAfter(int current) {

              if (!this.active) return -1;

              if (this.isRepeated()) {

                  if (current >= this.end) return -1;

                  int next = this.start;
                  for (; next <= current; next += this.interval);

                  if (next <= this.end) return next;
                  return -1;
              }

              if (current >= this.time) return -1;
              return this.time;
       }

       @Override
       public boolean equals(Object obj) {

              if (this == obj) return true;

              if (!(obj instanceof Task)) return false;
              Task temp = (Task)obj;

              if (Objects.equals(title, temp.title)) {

                     if (this.active == temp.active) {

                            if (this.repeat == temp.repeat) {

                                   if (this.time == temp.time) {

                                          if (this.start == temp.start) {

                                                 if (this.end == temp.end) {

                                                        if (this.interval == temp.interval) {
                                                               return true;
                                                        }
                                                 }
                                          }
                                   }
                            }
                     }
              }
              return false;
       }

       @Override
       public int hashCode() {
              return Objects.hash(title, active, repeat, time, start, end, interval);
       }

       @Override
       public String toString() {
              return this.title + "with t = " + this.time;
       }
}