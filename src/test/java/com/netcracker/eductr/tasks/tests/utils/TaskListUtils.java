package com.netcracker.eductr.tasks.tests.utils;

import com.netcracker.eductr.tasks.tests.model.Task;
import com.netcracker.eductr.tasks.tests.model.TaskList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskListUtils {
    public static List<Task> tasksToList(TaskList taskList) {
        return IntStream.range(0, taskList.size()).mapToObj(i -> taskList.getTask(i)).collect(Collectors.toList());
    }

    public static String describeTasks(List<Task> target) {
        String s = target
                .stream()
                .map(t -> "Task{title:'" + t.getTitle()
                        + "',time:" + t.getTime()
                        + ",startTime:" + t.getStartTime()
                        + ",endTime:" + t.getEndTime()
                        + ",repeatInterval:" + t.getRepeatInterval()
                        + ",active:" + t.isActive()
                        + "}")
                .collect(Collectors.joining(","));
        return "[" + s + "]";
    }

    public static String describeTasks(TaskList taskList) {
        return describeTasks(tasksToList(taskList));
    }
}
