package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.TaskList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static com.netcracker.eductr.tasks.tests.model.TaskCreator.create;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkClassExistence;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkMethodExistence;
import static com.netcracker.eductr.tasks.tests.utils.EqualsUtil.areEqual;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.ARRAY_LIST;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.CLONE;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T5_ArrayTaskListCloneTest {
	@BeforeClass
	public static void init() {
		Assume.assumeTrue(checkClassExistence(ARRAY_LIST));
		Assume.assumeTrue(checkMethodExistence(CLONE, ARRAY_LIST));
	}

	private TaskList createList() {
		TaskList tasks = new TaskList();
		tasks.add(create("A"));
		tasks.add(create("B"));
		tasks.add(create("C"));
		return tasks;
	}

	@Test(timeout = 100)
	public void part1_testClone() {
		TaskList original = createList();
		TaskList copy = original.clone();

		Object origRef = original.getInstance();
		Object cloneRef = copy.getInstance();

		Assert.assertTrue("{ x.clone() != x } не виконується", origRef != cloneRef);

		Assert.assertEquals("{ x.clone().getClass() == x.getClass() } не виконується", origRef.getClass(), cloneRef.getClass());

		Assert.assertTrue("{ x.clone().equals(x) } не виконується", areEqual(original, copy));
	}

	@Test(timeout = 100)
	public void part2_testCloneIndependenceRemove() {
		TaskList original = createList();
		TaskList etalon = createList();
		TaskList copy = original.clone();

		copy.remove(original.getTask(0));
		Assert.assertTrue("Після зміни копії оригінал також змінився", areEqual(original, etalon));
	}

	@Test(timeout = 100)
	public void part3_testCloneIndependenceAdd() {
		TaskList original = createList();
		TaskList etalon = createList();
		TaskList copy = original.clone();

		copy.add(create("D"));
		Assert.assertTrue("Після зміни копії оригінал також змінився", areEqual(original, etalon));
	}
}
