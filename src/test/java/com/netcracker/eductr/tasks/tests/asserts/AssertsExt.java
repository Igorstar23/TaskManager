package com.netcracker.eductr.tasks.tests.asserts;

import com.netcracker.eductr.tasks.tests.model.Task;
import com.netcracker.eductr.tasks.tests.model.TaskList;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

import static com.netcracker.eductr.tasks.tests.utils.EqualsUtil.areEqual;
import static com.netcracker.eductr.tasks.tests.utils.EqualsUtil.getMessage;
import static com.netcracker.eductr.tasks.tests.utils.TaskListUtils.tasksToList;

public class AssertsExt {
    public static void assertEquals(TaskList actual, List<Task> expected) {
        boolean check = areEqual(tasksToList(actual), expected);
        Assert.assertTrue(getMessage(), check);
    }

    public static void assertEquals(TaskList actual, Task... expected) {
        assertEquals(actual, Arrays.asList(expected));
    }

    public static void assertEquals(TaskList actual, TaskList expected) {
        assertEquals(actual, tasksToList(expected));
    }
}
