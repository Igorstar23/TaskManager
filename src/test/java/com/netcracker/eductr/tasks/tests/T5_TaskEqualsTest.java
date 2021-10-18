package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.Task;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static com.netcracker.eductr.tasks.tests.model.TaskCreator.*;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.*;
import static com.netcracker.eductr.tasks.tests.utils.TaskUtil.describeTask;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.TASK_BASE;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T5_TaskEqualsTest {
	@BeforeClass
	public static void init() {
		Assume.assumeTrue(checkClassExistence(TASK_BASE));
		Assume.assumeTrue(checkDeclaredMethodExistence(HASH_CODE, TASK_BASE));
		Assume.assumeTrue(checkDeclaredMethodExistence(EQUALS, TASK_BASE));
	}

	@Test
	public void part1_testEqualsToItself() {
		Task a = createA();
		Assert.assertTrue("a.equals(a), a != null не виконується для " + describeTask(a), a.equals(a));
	}

	@Test
	public void part2_testEquals() {
		Task a = createA();
		Task b = createA();

		Assert.assertTrue("Об'єкти мають бути рівні: " + describeTask(a) + " та " + describeTask(b), a.equals(b));
		Assert.assertTrue("a = b <=> b = a не виконується для " + describeTask(a) + " та " + describeTask(b), b.equals(a));
	}

	@Test
	public void part3_testEqualsChanged() {
		Task a = createA();
		Task b = createB();
		modify(a);
		modify(b);

		Assert.assertTrue("Об'єкти мають бути рівні: " + describeTask(a) + " та " + describeTask(b), a.equals(b));
	}

	@Test
	public void part4_testEqualsNull() {
		Task a = createA();
		Assert.assertFalse("x.equals(null) == false не виконується", a.equals(null));
	}

	@Test
	public void part5_testEqualsToString() {
		Task a = createA();

		Assert.assertFalse("Об'єкти не повинні дорівнювати об'єктам зовсім іншого типу", a.equals("some string"));
	}

	@Test
	public void part6_testHashCode() {
		Task a = createA();
		Task b = createB();
		modify(a);
		modify(b);

		Assert.assertTrue("Хеш-коди для " + describeTask(a) + " (" + a.hashCode() + ") та "
				+ describeTask(b) + " (" + b.hashCode() + ") мають бути рівні", a.hashCode() == b.hashCode());
	}
}
