package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.TaskList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static com.netcracker.eductr.tasks.tests.model.TaskListCreator.*;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkClassExistence;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkMethodExistence;
import static com.netcracker.eductr.tasks.tests.utils.TaskListUtils.describeTasks;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.LINKED_LIST;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T5_LinkedTaskListEqualsTest {
	@BeforeClass
	public static void init() {
		Assume.assumeTrue(checkClassExistence(LINKED_LIST));
		Assume.assumeTrue(checkMethodExistence(HASH_CODE, LINKED_LIST));
		Assume.assumeTrue(checkMethodExistence(EQUALS, LINKED_LIST));
	}

	@Test
	public void part1_testEqualsToItself() {
		TaskList a = new TaskList();
		Assert.assertTrue("a.equals(a), a != null не виконується для " + describeTasks(a), a.equals(a));
	}

	@Test
	public void part2_testEquals() {
		TaskList a = new TaskList();
		TaskList b = new TaskList();

		Assert.assertTrue("Об'єкти мають бути рівні: " + describeTasks(a) + " та " + describeTasks(b), a.equals(b));
		Assert.assertTrue("a = b <=> b = a не виконується для " + describeTasks(a) + " та " + describeTasks(b), b.equals(a));
	}

	@Test
	public void part3_testEqualsChanged() {
		TaskList a = createA();
		TaskList b = createB();
		modify(a);
		modify(b);

		Assert.assertTrue("Об'єкти мають бути рівні: " + describeTasks(a) + " та " + describeTasks(b), a.equals(b));
	}

	@Test
	public void part4_testEqualsNull() {
		TaskList a = createA();
		Assert.assertFalse("x.equals(null) == false yе виконується", a.equals(null));
	}

	@Test
	public void part5_testEqualsToString() {
		TaskList a = createA();

		Assert.assertFalse("Об'єкти не повинні дорівнювати об'єктам зовсім іншого типу", a.equals("some string"));
	}

	@Test
	public void part6_testHashCode() {
		TaskList a = createA();
		TaskList b = createB();
		modify(a);
		modify(b);

		Assert.assertTrue("Хеш-коди для " + describeTasks(a) + " (" + a.hashCode() + ") та "
				+ describeTasks(b) + " (" + b.hashCode() + ") мають бути рівні", a.hashCode() == b.hashCode());
	}
}
