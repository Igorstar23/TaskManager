package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.TaskListFactory;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static com.netcracker.eductr.tasks.tests.model.BaseCreator.isOld;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.*;
import static com.netcracker.eductr.tasks.tests.utils.EqualsUtil.hasParent;
import static com.netcracker.eductr.tasks.tests.model.ListTypes.count;
import static com.netcracker.eductr.tasks.tests.model.ListTypes.isRightNames;
import static com.netcracker.eductr.tasks.tests.utils.EqualsUtil.sameTypes;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.*;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T4_AbstractTaskList {
    @BeforeClass
    public static void init() {
        Assume.assumeTrue(checkClassExistence(ARRAY_LIST));
        Assume.assumeTrue(checkClassExistence(LINKED_LIST));
        Assume.assumeTrue(checkClassExistence(ABSTRACT_LIST));
        Assume.assumeTrue(checkClassExistence(LIST_TYPES));
        Assume.assumeTrue(checkClassExistence(TASK_LIST_FACTORY));
    }

    @Test
    public void part1_checkInheritance() {
        Assert.assertTrue("ArrayTaskList повинен наслідуватись від AbstractTaskList", hasParent(ARRAY_LIST));
        Assert.assertTrue("LinkedTaskList повинен наслідуватись від AbstractTaskList", hasParent(LINKED_LIST));
    }

    @Test
    public void part2_checkMethodExistence() {
        Assert.assertTrue("AbstractTaskList повинен містити метод size()", checkDeclaredMethodExistence(SIZE, ABSTRACT_LIST));
        Assert.assertTrue("AbstractTaskList повинен містити метод getTask()", checkDeclaredMethodExistence(GET_TASK, ABSTRACT_LIST));
        Assert.assertTrue("AbstractTaskList повинен містити метод add()", checkDeclaredMethodExistence(ADD, ABSTRACT_LIST));
        Assert.assertTrue("AbstractTaskList повинен містити метод remove()", checkDeclaredMethodExistence(REMOVE, ABSTRACT_LIST));
        if (isOld()) Assert.assertTrue("AbstractTaskList повинен містити метод incoming()", checkDeclaredMethodExistence(INCOMING, ABSTRACT_LIST));
    }

    @Test
    public void part3_checkTypes() {
        Assert.assertEquals("ListTypes.types повинен мати 2 елемента", 2, count());
        Assert.assertTrue("ListTypes.types повинен мати значення ARRAY і LINKED", isRightNames());
    }

    @Test
    public void part4_checkFactory() {
        Assert.assertTrue("TaskListFactory.createTaskList(ARRAY) повинен повертати об'єкт класу ArrayTaskList", sameTypes(ARRAY_LIST, TaskListFactory.createTaskList(ARRAY_LIST)));
        Assert.assertTrue("TaskListFactory.createTaskList(LINKED) повинен повертати об'єкт класу LinkedTaskList", sameTypes(LINKED_LIST, TaskListFactory.createTaskList(LINKED_LIST)));
    }
}
