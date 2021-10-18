package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.TaskList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static com.netcracker.eductr.tasks.tests.model.TaskListCreator.createA;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.*;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.*;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.GET_STREAM;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.INCOMING;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T6_StreamAPITest {
	@BeforeClass
	public static void init() {
		Assume.assumeTrue(checkClassExistence(TASK_BASE));
		Assume.assumeTrue(checkClassExistence(ARRAY_LIST));
		Assume.assumeTrue(checkClassExistence(LINKED_LIST));
		Assume.assumeTrue(checkMethodExistence(GET_STREAM, ABSTRACT_LIST));
	}

	@Test
	public void part1_getStreamTest() throws ClassNotFoundException {
		TaskList.setTargetClass(getTargetClass(ARRAY_LIST));
		Assert.assertEquals("Stream містить неочікувану кількість задач", createA().getStream().count(), 10);
		TaskList.setTargetClass(getTargetClass(LINKED_LIST));
		Assert.assertEquals("Stream містить неочікувану кількість задач", createA().getStream().count(), 10);
	}

	@Test
	public void part2_checkMethodDeclaration() {
		Assert.assertFalse("ArrayTaskList не повинен містими метод incoming", checkDeclaredMethodExistence(INCOMING, ARRAY_LIST));
		Assert.assertFalse("LinkedTaskList не повинен містими метод incoming", checkDeclaredMethodExistence(INCOMING, LINKED_LIST));
	}
}
