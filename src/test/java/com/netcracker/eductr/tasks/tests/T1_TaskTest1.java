package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.Task;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static com.netcracker.eductr.tasks.tests.model.BaseCreator.isOld;
import static com.netcracker.eductr.tasks.tests.model.TaskCreator.create;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.*;
import static com.netcracker.eductr.tasks.tests.utils.DatesTimes.*;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.TASK_BASE;
import static com.netcracker.eductr.tasks.tests.utils.Types.constructorTypes.TITLE_START_END_INTERVAL;
import static com.netcracker.eductr.tasks.tests.utils.Types.constructorTypes.TITLE_TIME;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T1_TaskTest1 {

	@BeforeClass
	public static void init() {
		Assume.assumeTrue(checkClassExistence(TASK_BASE));
		Assume.assumeTrue(checkConstructorExistence(TITLE_TIME, TASK_BASE));
		Assume.assumeTrue(checkConstructorExistence(TITLE_START_END_INTERVAL, TASK_BASE));
		Assume.assumeTrue(checkMethodExistence(GET_TITLE, TASK_BASE));
		Assume.assumeTrue(checkMethodExistence(IS_REPEATED, TASK_BASE));
		Assume.assumeTrue(checkMethodExistence(IS_ACTIVE, TASK_BASE));
		Assume.assumeTrue(checkMethodExistence(GET_TIME, TASK_BASE));
		Assume.assumeTrue(checkMethodExistence(GET_START_TIME, TASK_BASE));
		Assume.assumeTrue(checkMethodExistence(GET_END_TIME, TASK_BASE));
		Assume.assumeTrue(checkMethodExistence(GET_REPEAT_INTERVAL, TASK_BASE));
		Assume.assumeTrue(checkMethodExistence(SET_TIME, TASK_BASE));
	}

	@Test
	public void part1_testConstructorNonRepeat() {
		if (isOld()) {
			Task task = create("Task title", 42);

			Assert.assertEquals("Задача має неочікувану назву", "Task title", task.getTitle());

			Assert.assertFalse("Задача не повинна повторюватись", task.isRepeated());

			Assert.assertFalse("Задача повинна бути неактивною", task.isActive());

			Assert.assertEquals("Задача має неочікуваний час виконання", 42, task.getTime());

			Assert.assertEquals("Задача має неочікуваний час початку", 42, task.getStartTime());

			Assert.assertEquals("Задача має неочікуваний час закінчення", 42, task.getEndTime());

			Assert.assertEquals("Задача має неочікуваний інтервал повторення", 0, task.getRepeatInterval());
		} else {
			Task task = create("Task title", FROM_NOW_42);

			Assert.assertEquals("Задача має неочікувану назву", "Task title", task.getTitle());

			Assert.assertFalse("Задача не повинна повторюватись", task.isRepeated());

			Assert.assertFalse("Задача повинна бути неактивною", task.isActive());

			Assert.assertEquals("Задача має неочікуваний час виконання", FROM_NOW_42, task.getTime());

			Assert.assertEquals("Задача має неочікуваний час початку", FROM_NOW_42, task.getStartTime());

			Assert.assertEquals("Задача має неочікуваний час закінчення", FROM_NOW_42, task.getEndTime());

			Assert.assertEquals("Задача має неочікуваний інтервал повторення", 0, task.getRepeatInterval());
		}
	}

	@Test
	public void part2_testConstructorRepeat() {
		if (isOld()) {
			Task task = create("Task title", 5, 25, 3);

			Assert.assertEquals("Задача має неочікувану назву", "Task title", task.getTitle());

			Assert.assertTrue("Задача повинна повторюватись", task.isRepeated());

			Assert.assertFalse("Задача повинна бути неактивною", task.isActive());

			Assert.assertEquals("Задача має неочікуваний час початку", 5, task.getStartTime());

			Assert.assertEquals("Задача має неочікуваний час закінчення", 25, task.getEndTime());

			Assert.assertEquals("Задача має неочікуваний інтервал повторення", 3, task.getRepeatInterval());

			Assert.assertEquals("Задача має неочікуваний час виконання", 5, task.getTime());
		} else {
			Task task = create("Task title", FROM_NOW_5, FROM_NOW_25, 3);

			Assert.assertEquals("Задача має неочікувану назву", "Task title", task.getTitle());

			Assert.assertTrue("Задача повинна повторюватись", task.isRepeated());

			Assert.assertFalse("Задача повинна бути неактивною", task.isActive());

			Assert.assertEquals("Задача має неочікуваний час початку", FROM_NOW_5, task.getStartTime());

			Assert.assertEquals("Задача має неочікуваний час закінчення", FROM_NOW_25, task.getEndTime());

			Assert.assertEquals("Задача має неочікуваний інтервал повторення", 3, task.getRepeatInterval());

			Assert.assertEquals("Задача має неочікуваний час виконання", FROM_NOW_5, task.getTime());
		}
	}

	@Test
	public void part3_testSetTime()
	{
		if (isOld()) {
			Task task = create("Task title", 5, 25, 3);

			task.setTime(42);

			Assert.assertFalse("Задача не повинна повторюватись", task.isRepeated());

			Assert.assertEquals("Задача має неочікуваний час виконання", 42, task.getTime());

			Assert.assertEquals("Задача має неочікуваний час початку", 42, task.getStartTime());

			Assert.assertEquals("Задача має неочікуваний час закінчення", 42, task.getEndTime());

			Assert.assertEquals("Задача має неочікуваний інтервал повторення", 0, task.getRepeatInterval());

			task.setTime(5, 25, 3);

			Assert.assertTrue("Задача повинна повторюватись", task.isRepeated());

			Assert.assertEquals("Задача має неочікуваний час початку", 5, task.getStartTime());

			Assert.assertEquals("Задача має неочікуваний час закінчення", 25, task.getEndTime());

			Assert.assertEquals("Задача має неочікуваний інтервал повторення", 3, task.getRepeatInterval());

			Assert.assertEquals("Задача має неочікуваний час виконання", 5, task.getTime());
		} else {
			Task task = create("Task title", FROM_NOW_5, FROM_NOW_25, 3);

			task.setTime(FROM_NOW_42);

			Assert.assertFalse("Задача не повинна повторюватись", task.isRepeated());

			Assert.assertEquals("Задача має неочікуваний час виконання", FROM_NOW_42, task.getTime());

			Assert.assertEquals("Задача має неочікуваний час початку", FROM_NOW_42, task.getStartTime());

			Assert.assertEquals("Задача має неочікуваний час закінчення", FROM_NOW_42, task.getEndTime());

			Assert.assertEquals("Задача має неочікуваний інтервал повторення", 0, task.getRepeatInterval());

			task.setTime(FROM_NOW_5, FROM_NOW_25, 3);

			Assert.assertTrue("Задача повинна повторюватись", task.isRepeated());

			Assert.assertEquals("Задача має неочікуваний час початку", FROM_NOW_5, task.getStartTime());

			Assert.assertEquals("Задача має неочікуваний час закінчення", FROM_NOW_25, task.getEndTime());

			Assert.assertEquals("Задача має неочікуваний інтервал повторення", 3, task.getRepeatInterval());

			Assert.assertEquals("Задача має неочікуваний час виконання", FROM_NOW_5, task.getTime());
		}
	}
}
