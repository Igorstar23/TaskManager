package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.TaskIO;
import com.netcracker.eductr.tasks.tests.model.TaskList;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.*;

import static com.netcracker.eductr.tasks.tests.asserts.AssertsExt.assertEquals;
import static com.netcracker.eductr.tasks.tests.model.BaseCreator.isOld;
import static com.netcracker.eductr.tasks.tests.model.TaskListCreator.createList;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkClassExistence;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkMethodExistence;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.*;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T8_TaskIOTest {
	@BeforeClass
	public static void init() {
		Assume.assumeTrue(checkClassExistence(TASK_BASE));
		Assume.assumeTrue(checkClassExistence(TASK_IO));
		Assume.assumeTrue(checkClassExistence(ARRAY_LIST));
		Assume.assumeFalse(isOld());
	}

	@Test(timeout = 1000)
	public void part1_testBinary() throws IOException {
		Assume.assumeTrue(checkMethodExistence(WRITE_BINARY, TASK_IO));
		Assume.assumeTrue(checkMethodExistence(READ_BINARY, TASK_IO));
		TaskList expected = createList();

		PipedInputStream in = new PipedInputStream();
		PipedOutputStream out = new PipedOutputStream(in);

		TaskIO.write(expected, out);
		TaskList result = new TaskList();
		TaskIO.read(result, in);

		assertEquals(result, expected);
	}

	@Test(timeout = 1000)
	public void part2_testText() throws IOException {
		Assume.assumeTrue(checkMethodExistence(WRITE, TASK_IO));
		Assume.assumeTrue(checkMethodExistence(READ, TASK_IO));

		TaskList expected = createList();
		TaskList actual = new TaskList();

		TaskIO.write(expected, new FileWriter("test.json"));
		TaskIO.read(actual, new FileReader("test.json"));

		assertEquals(actual, expected);
	}
}
