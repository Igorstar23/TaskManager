package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.TaskList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static com.netcracker.eductr.tasks.tests.model.BaseCreator.isOld;
import static com.netcracker.eductr.tasks.tests.model.Task.createWithException;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.*;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.*;
import static com.netcracker.eductr.tasks.tests.utils.Types.constructorTypes.TITLE_TIME;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.GET_TASK;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T3_Exceptions {
    @BeforeClass
    public static void init() {
        Assume.assumeTrue(checkClassExistence(TASK_BASE));
        Assume.assumeTrue(checkClassExistence(ARRAY_LIST));
        Assume.assumeTrue(checkClassExistence(LINKED_LIST));
        Assume.assumeTrue(checkConstructorExistence(TITLE_TIME, TASK_BASE));
        Assume.assumeTrue(checkMethodExistence(GET_TASK, ARRAY_LIST));
        Assume.assumeTrue(checkMethodExistence(GET_TASK, LINKED_LIST));
    }

    @Test(expected = IllegalArgumentException.class)
    public void part1_checkTaskConstructor() throws Throwable {
        if (isOld()) createWithException("A", -1);
        else createWithException("A", null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void part2_checkGetMethods() throws Throwable {
        Assume.assumeTrue(checkClassExistence(ARRAY_LIST));
        new TaskList().getTaskWithException(-1);
        Assume.assumeTrue(checkClassExistence(LINKED_LIST));
        new TaskList().getTaskWithException(-1);
    }
}
