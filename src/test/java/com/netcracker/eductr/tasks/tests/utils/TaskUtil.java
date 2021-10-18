package com.netcracker.eductr.tasks.tests.utils;

import com.netcracker.eductr.tasks.tests.model.Task;

import java.util.Collections;

import static com.netcracker.eductr.tasks.tests.utils.TaskListUtils.describeTasks;

public class TaskUtil {
    public static String describeTask(Task task) {
        return describeTasks(Collections.singletonList(task));
    }
}
