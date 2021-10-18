package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.Task;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static com.netcracker.eductr.tasks.tests.model.BaseCreator.isOld;
import static com.netcracker.eductr.tasks.tests.model.TaskCreator.create;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkClassExistence;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkMethodExistence;
import static com.netcracker.eductr.tasks.tests.utils.DatesTimes.*;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.TASK_BASE;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.NEXT_TIME_AFTER;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T1_TaskTest2 {
	@BeforeClass
	public static void init() {
		Assume.assumeTrue(checkClassExistence(TASK_BASE));
		Assume.assumeTrue(checkMethodExistence(NEXT_TIME_AFTER, TASK_BASE));
	}

	@Test
	public void part1_testNextNonRepeative() {
		if (isOld()) {
			Task task = new Task("some", 10);
			task.setActive(true);
			Assert.assertEquals("nextTimeAfter 0", 10, task.nextTimeAfter(0));
			Assert.assertEquals("nextTimeAfter 9", 10, task.nextTimeAfter(9));
			Assert.assertEquals("nextTimeAfter 10", -1, task.nextTimeAfter(10));
			Assert.assertEquals("nextTimeAfter 100", -1, task.nextTimeAfter(100));
		} else {
			Task task = create("some", FROM_NOW_10);
			task.setActive(true);
			Assert.assertEquals("nextTimeAfter now", FROM_NOW_10, task.nextTimeAfter(NOW));
			Assert.assertEquals("nextTimeAfter 9 from now", FROM_NOW_10, task.nextTimeAfter(FROM_NOW_9));
			Assert.assertEquals("nextTimeAfter 10 from now", null, task.nextTimeAfter(FROM_NOW_10));
			Assert.assertEquals("nextTimeAfter 100 from now", null, task.nextTimeAfter(FROM_NOW_100));
		}
	}
	@Test
	public void part2_testNextRepeative() {
		if (isOld()) {
			Task task = new Task("some", 10, 100, 20);
			task.setActive(true);
			Assert.assertEquals("nextTimeAfter 0", 10, task.nextTimeAfter(0));
			Assert.assertEquals("nextTimeAfter 9", 10, task.nextTimeAfter(9));
			Assert.assertEquals("nextTimeAfter 30", 50, task.nextTimeAfter(30));
			Assert.assertEquals("nextTimeAfter 40", 50, task.nextTimeAfter(40));
			Assert.assertEquals("nextTimeAfter 10", 30, task.nextTimeAfter(10));
			Assert.assertEquals("nextTimeAfter 95", -1, task.nextTimeAfter(95));
			Assert.assertEquals("nextTimeAfter 100", -1, task.nextTimeAfter(100));
		} else {
			Task task = new Task("some", FROM_NOW_10, FROM_NOW_100, 20);
			task.setActive(true);
			Assert.assertEquals("nextTimeAfter now", FROM_NOW_10, task.nextTimeAfter(NOW));
			Assert.assertEquals("nextTimeAfter 9 from now", FROM_NOW_10, task.nextTimeAfter(FROM_NOW_9));
			Assert.assertEquals("nextTimeAfter 30 from now", FROM_NOW_50, task.nextTimeAfter(FROM_NOW_30));
			Assert.assertEquals("nextTimeAfter 40 from now", FROM_NOW_50, task.nextTimeAfter(FROM_NOW_40));
			Assert.assertEquals("nextTimeAfter 10 from now", FROM_NOW_30, task.nextTimeAfter(FROM_NOW_10));
			Assert.assertEquals("nextTimeAfter 95 from now", null, task.nextTimeAfter(FROM_NOW_95));
			Assert.assertEquals("nextTimeAfter 100 from now", null, task.nextTimeAfter(FROM_NOW_100));
		}
	}
	@Test
	public void part3_testNextInactive() {
		if (isOld()) {
			Task task = new Task("some", 10);
			task.setActive(false);
			Assert.assertEquals("nextTimeAfter 0", -1, task.nextTimeAfter(0));
		} else {
			Task task = new Task("some", FROM_NOW_10);
			task.setActive(false);
			Assert.assertEquals("nextTimeAfter now", null, task.nextTimeAfter(NOW));
		}
	}
}
