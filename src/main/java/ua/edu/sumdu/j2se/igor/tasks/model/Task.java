package ua.edu.sumdu.j2se.igor.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Task implements Serializable {
       private String title;
       private boolean active;
       private boolean repeat;
       private LocalDateTime time;
       private LocalDateTime start;
       private LocalDateTime end;
       private int interval; // seconds

       public Task(String title) {
              this(title, LocalDateTime.now());
       }
       /**
        * Constructor for non-active and non-repetitive task
        * @param time must be not null
        * @throws IllegalArgumentException if param time is null
        * */
       public Task(String title, LocalDateTime time) {

              if (time == null) throw new IllegalArgumentException("param time is null!");

              this.title = title;
              this.time = time;
              this.active = false;
              this.repeat = false;
       }
       /**
        * Constructor for active and non-repetitive task
        * @param time must be not null
        * @throws IllegalArgumentException if param time is null!
        * */
       public Task(String title, LocalDateTime time, boolean active) {

              if (time == null) throw new IllegalArgumentException("param time is null!");

              this.title = title;
              this.time = time;
              this.active = active;
              this.repeat = false;
       }
	   /**
	    * Constructor for non-active and repetitive task
        * @param interval is seconds and it must be > 0
        * @param start must be not null
        * @param end must be not null
        * @throws IllegalArgumentException if param start or end is null or interval <= 0
	    * */
       public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {

              if (start == null) throw new IllegalArgumentException("param start is null!");

              if (end == null) throw new IllegalArgumentException("param end is null!");

              if (interval <= 0) throw new IllegalArgumentException("param interval <= 0!");

              this.title = title;
              this.start = start;
              this.end = end;
              this.interval = interval;
              this.active = false;
              this.repeat =  true;
              this.time = this.nextTimeAfter(this.start);
       }
       /**
        * Constructor for active and repetitive task
        * @param interval is seconds
        * */
       public Task(String title, LocalDateTime start, LocalDateTime end, int interval, boolean active) {
              this(title, start,end,interval);
              this.active = active;
       }
       /**
        * Constructor for init all fields
        * @param interval is seconds
        * */
       public Task(String title, LocalDateTime time, LocalDateTime start, LocalDateTime end, int interval, boolean active, boolean repeat) {
              this.title = title;
              this.active = active;
              this.repeat = repeat;
              this.time = time;
              this.start = start;
              this.end = end;
              this.interval = interval;
       }

       /**
        * @param title isn't null and empty else set value "Title is None!"
        * */
	   public void setTitle(String title) { this.title = (title != null && !title.isEmpty())? title : "Title is None!"; }
       public void setActive(boolean active) { this.active = active; }
       /**
        * Set time for non-repetitive task
        * if the task is repetitive then reset the repetition time interval and turn off repeat of the task
        * @param time must be not null
        * @throws IllegalArgumentException if param time is null
        * */
       public void setTime(LocalDateTime time) {

              if (time == null) throw new IllegalArgumentException("param time is null!");

              this.time = time;

              if (this.isRepeated()) this.resetTimeInterval();
       }
       /**
        * Set time for repetitive task
        * if task is non-repetitive then turn on repeat for the task
        * @param start must be < end and >= 0
        * @param end must be > start and >= 0
        * @param interval must be > 0
        * @throws IllegalArgumentException if param start or end is null or interval <= 0
        * */
       public void setTime(LocalDateTime start, LocalDateTime end, int interval) {

              if (start == null) throw new IllegalArgumentException("param start is null!");

              if (end == null) throw new IllegalArgumentException("param end is null");

              if (interval <= 0) throw new IllegalArgumentException("param interval <= 0!");

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
       public LocalDateTime getTime() { return (this.isRepeated())? this.start : this.time; }
       /**
        * @return the time start of the repetition time interval or the time of task for non-repetitive task
        * */
       public LocalDateTime getStartTime() { return (this.isRepeated())? this.start : this.time; }
       /**
        * @return the time end of the repetition time interval or the time of task for non-repetitive task
        * */
       public LocalDateTime getEndTime() { return (this.isRepeated())? this.end : this.time; }
       /**
        * @return value of interval or 0 for non-repetitive task
        * */
       public int getRepeatInterval() { return (this.isRepeated())? this.interval : 0; }

       /**
        * For reset the repetition time interval and turn off repeat of the task
        * */
       public void resetTimeInterval() {
              this.start = null;
              this.end = null;
              this.interval = 0;
              this.repeat = false;
       }
       /**
        * @return next time of task or null if next time of task is out of the time interval or the task is non-active
        * @param current must be not null
        * @throws IllegalArgumentException if param current is null
        * */
       public LocalDateTime nextTimeAfter(LocalDateTime current) {

              if (current == null) throw new IllegalArgumentException("Param current is null!");

              if (!this.active) return null;

              if (this.isRepeated()) {

                  if (current.compareTo(this.end) >= 0) return null;

                  LocalDateTime next = this.start;
                  for (; next.compareTo(current) <= 0; next = next.plusSeconds(this.interval));

                  if (next.compareTo(this.end) <= 0) return next;
                  return null;
              }

              if (current.compareTo(this.time) >= 0) return null;
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

                                   if (Objects.equals(this.time, temp.time)) {

                                          if (Objects.equals(this.start, temp.start)) {

                                                 if (Objects.equals(this.end, temp.end)) {

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

              return "[" + this.title +( (this.isActive())? " is active " : " isn't active ") + ((this.isRepeated())? "start = "
                      + this.start + " end = " + this.end + " interval = " + this.interval
                      + " seconds]": " time = " + this.time + "]");
       }
       /**
        * @param format must be not null
        * @throws IllegalArgumentException if param 'format' is null
        * */
       public String toString(DateTimeFormatter format) {

              if (format == null) throw new IllegalArgumentException("Param 'format' is null!");
              return "["+this.title +( (this.isActive())? " is active " : " isn't active ") + ((this.isRepeated())? "start = "
                      + this.start.format(format) + " end = " + this.end.format(format) + " interval = " + this.interval
                      + " seconds]": " time = " + this.time.format(format) + "]");
       }

       /**
         * @return a clone of this instance.
       * */
       @Override
       public Task clone() {
              return new Task(this.title, this.time, this.start, this.end, this.interval, this.active, this.repeat);
       }

}