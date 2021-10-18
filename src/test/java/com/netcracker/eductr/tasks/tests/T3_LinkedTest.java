package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.Task;
import com.netcracker.eductr.tasks.tests.model.TaskList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.netcracker.eductr.tasks.tests.asserts.AssertsExt.assertEquals;
import static com.netcracker.eductr.tasks.tests.model.BaseCreator.isOld;
import static com.netcracker.eductr.tasks.tests.model.TaskCreator.create;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkClassExistence;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkMethodExistence;
import static com.netcracker.eductr.tasks.tests.utils.DatesTimes.*;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.LINKED_LIST;
import static com.netcracker.eductr.tasks.tests.utils.TaskListUtils.describeTasks;
import static com.netcracker.eductr.tasks.tests.utils.TaskUtil.describeTask;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.TASK_BASE;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T3_LinkedTest {
	@BeforeClass
	public static void init() {
        Assume.assumeTrue(checkClassExistence(TASK_BASE));
		Assume.assumeTrue(checkClassExistence(LINKED_LIST));
        Assume.assumeTrue(checkMethodExistence(SIZE, LINKED_LIST));
        Assume.assumeTrue(checkMethodExistence(ADD, LINKED_LIST));
        Assume.assumeTrue(checkMethodExistence(GET_TASK, LINKED_LIST));
	}

    @Test
    public void part1_testSizeAddGet() {
        TaskList actual = new TaskList();
        Assert.assertEquals("LinkedTaskList.size()", 0, actual.size());
        List<Task> expected = Arrays.asList(
                create("A"),
                create("B"),
                create("C"));

	    expected.forEach(actual::add);
        Assert.assertEquals("LinkedTaskList.size()", 3, actual.size());

        assertEquals(actual, expected);
    }

    @Test
    public void part2_testRemove() {
        TaskList actual = new TaskList();
        Task[] expected = {
                create("A"),
                create("B"),
                create("C"),
                create("D"),
                create("E")};
	    Arrays.stream(expected).forEach(actual::add);

        // remove first
        Assert.assertTrue("LinkedTaskList.remove(A) повинно бути істинним", actual.remove(expected[0]));
        Assert.assertEquals("LinkedTaskList.size()", 4, actual.size());
        assertEquals(actual, expected[1], expected[2], expected[3], expected[4]);


        // remove last
        Assert.assertTrue("LinkedTaskList.remove(E) повинно бути істинним", actual.remove(expected[4]));
        Assert.assertEquals("LinkedTaskList.size()", 3, actual.size());
        assertEquals(actual, expected[1], expected[2], expected[3]);

        // remove middle
        Assert.assertTrue("LinkedTaskList.remove(C) повинно бути істинним", actual.remove(expected[2]));
        Assert.assertEquals("LinkedTaskList.size()", 2, actual.size());
        assertEquals(actual, expected[1], expected[3]);

        // remove unexistent
        Assert.assertFalse("LinkedTaskList.remove(D) не повинно бути істинним", actual.remove(create("F")));

        // test multiple remove
        actual.add(expected[0]);
        actual.add(expected[0]);
        Assert.assertEquals("LinkedTaskList.size()", 4, actual.size());
        assertEquals(actual, expected[0], expected[1], expected[3]);
        Assert.assertTrue("LinkedTaskList.remove(A) повинно бути істинним", actual.remove(expected[0]));
        Assert.assertEquals("LinkedTaskList.size()", 3, actual.size());
        assertEquals(actual, expected[0], expected[1], expected[3]);
        Assert.assertTrue("LinkedTaskList.remove(A) повинно бути істинним", actual.remove(expected[0]));
        Assert.assertEquals("LinkedTaskList.size()", 2, actual.size());
        assertEquals(actual, expected[1], expected[3]);
    }

    @Test(timeout = 1000)
    public void part3_testIncomingInactive() {
        Assume.assumeTrue(isOld());
        TaskList tasks = new TaskList();

        Task[] ts = {create("A", 0, false), create("B", 1, false), create("C", 2, false)};

        Arrays.stream(ts).forEach(tasks::add);
        TaskList res;
        if (isOld()) {
            res = tasks.incoming(0, 1000);
            Assert.assertEquals("incoming(0, 1000) має бути пустим, але насправді: " + describeTasks(res), 0, res.size());
        } else {
            res = tasks.incoming(NOW, FROM_NOW_1000);
            Assert.assertEquals("incoming(now, 1000 from now) має бути пустим, але насправді: " + describeTasks(res), 0, res.size());
        }

    }

    @Test(timeout = 1000)
    public void part4_testIncoming() {
        Assume.assumeTrue(isOld());
        // range: 50 60
        List<Task> ts = Arrays.asList(
                create("Simple IN", 55, true),
                create("Simple OUT", 10, true),
                create("Inactive OUT", 0, 1000, 1, false),
                create("Simple bound OUT", 50, true),
                create("Simple bound IN", 60, true),
                create("Repeat inside IN", 51, 58, 2, true),
                create("Repeat outside IN", 0, 100, 5, true),
                create("Repeat outside OUT", 0, 100, 22, true),
                create("Repeat left OUT", 0, 40, 1, true),
                create("Repeat right OUT", 65, 100, 1, true),
                create("Repeat left intersect IN 1", 0, 55, 13, true),
                create("Repeat left intersect IN 2", 0, 60, 30, true),
                create("Repeat left intersect OUT", 0, 55, 22, true),

                create("Repeat right intersect IN", 55, 100, 20, true));

        TaskList tasks = new TaskList();
        ts.forEach(tasks::add);
        Set<String> incoming = ts.stream().map(t -> t.getTitle()).filter(t -> t.contains("IN")).collect(Collectors.toSet());

        tasks = tasks.incoming(50, 60);
        IntStream.range(0, tasks.size()).mapToObj(tasks::getTask).forEach(t -> {
            Assert.assertTrue("incoming(" + (isOld() ? "50, 60" : FROM_NOW_50 + ", " + FROM_NOW_60) + ") не повинно містити " + describeTask(t), incoming.contains(t.getTitle()));
            incoming.remove(t.getTitle());
        });
    }
}
