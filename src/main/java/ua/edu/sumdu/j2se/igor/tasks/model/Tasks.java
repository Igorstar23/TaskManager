package ua.edu.sumdu.j2se.igor.tasks.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.StreamSupport;

public class Tasks {

       /**
         * For get Array Task from current Iterable
         * @param start must be not null
         * @param end must be not null
         * @param tasks must be not null
         * @return Array of Tasks from the current Iterable Where Tasks be in time interval [start, end]
       * */
       public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {

              if (tasks == null) throw new IllegalArgumentException("Param tasks is null!");

              if (start == null ) throw new IllegalArgumentException("Param 'start' is null!");

              if (end == null) throw new IllegalArgumentException("Param 'end' is null!");
              AbstractTaskList temp = TaskListFactory.createTaskList(ListTypes.getTypeList(tasks));
              StreamSupport.stream(tasks.spliterator(),false)
                      .filter(x -> x.nextTimeAfter(start) != null && x.nextTimeAfter(start).compareTo(end) <= 0)
                      .forEach(temp::add);
              return temp;
       }
       /**
         * For get calendar from Iterable of tasks for time interval [start, end]
         * @param start must be not null
         * @param end must be not null
         * @param tasks must be not null
         * @throws IllegalArgumentException if param start or end ot tasks is null
         * @return sortedMap of tasks where task in time interval [start, end]
       * */
       public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {

              if (tasks == null) throw new IllegalArgumentException("Param tasks is null!");

              if (start == null) throw new IllegalArgumentException("Param 'start' is null!");

              if (end == null) throw new IllegalArgumentException("Param 'end' is null!");
              TreeMap<LocalDateTime, Set<Task>> tempMap = new TreeMap<>();
              for (var task : tasks) {

                   if (!task.isRepeated()) {

                       if (task.getTime().compareTo(start) >= 0 && task.getTime().compareTo(end) <= 0) {
                           tempMap.putIfAbsent(task.getTime(), new HashSet<Task>());
                           tempMap.get(task.getTime()).add(task);
                       }
                       continue;
                   }

                   LocalDateTime currentTime = task.nextTimeAfter(start);
                   while (currentTime != null && currentTime.compareTo(end) <= 0) {
                          tempMap.putIfAbsent(currentTime, new HashSet<Task>());
                          tempMap.get(currentTime).add(task);
                          currentTime = task.nextTimeAfter(currentTime);
                   }
              }
              return tempMap;
       }
       /**
        * @param list must be not null
        * @throws IllegalArgumentException if param list is null
        * */
       public static Task[] getSortedTasksFromList(AbstractTaskList list) {

              if (list == null) throw new IllegalArgumentException("Param list is null!");
              Task arr[] = new Task[list.size()];

              for (int i = 0; i < list.size(); i++) {
                   arr[i] = list.getTask(i);
              }

              for (int i = 0; i < list.size(); i++) {
                   for (int j = 0; j < list.size() - 1; j++) {
                        LocalDateTime t1 = (arr[j].isRepeated())? arr[j].getStartTime() : arr[j].getTime();
                        LocalDateTime t2 = (arr[j + 1].isRepeated())? arr[j + 1].getStartTime() : arr[j + 1].getTime();

                        if (t1.compareTo(t2) > 0) {
                            Task temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                   }
              }
              return arr;
       }
}
