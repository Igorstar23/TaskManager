package com.netcracker.eductr.tasks.tests;

import com.netcracker.eductr.tasks.tests.model.Task;
import com.netcracker.eductr.tasks.tests.model.TaskList;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.*;
import java.util.stream.Collectors;

import static com.netcracker.eductr.tasks.tests.model.TaskCreator.create;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkClassExistence;
import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.checkMethodExistence;
import static com.netcracker.eductr.tasks.tests.utils.TaskUtil.describeTask;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.ARRAY_LIST;
import static com.netcracker.eductr.tasks.tests.utils.Types.methodTypes.ITERATOR;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T5_ArrayTaskListIteratorTest {
	@BeforeClass
	public static void init() {
		Assume.assumeTrue(checkClassExistence(ARRAY_LIST));
		Assume.assumeTrue(checkMethodExistence(ITERATOR, ARRAY_LIST));
	}

	@Test(timeout = 1000)
	public void part1_testIteration() {
		List<Task> array = Arrays.asList(create("A"), create("B"), create("C"), create("D"));
		TaskList tasks = new TaskList();
		Set<String> titles = array.stream().map(t -> t.getTitle()).collect(Collectors.toSet());
		array.forEach(tasks::add);
		for (Iterator<Task> it = tasks.iterator(); it.hasNext();) {
			Task t = new Task(it.next());
			String title = t.getTitle();
			String description = describeTask(t);
			Assert.assertTrue("При ітерації знайдено невідому задачу: " + description, titles.contains(title));
			titles.remove(title);
		}
		Assert.assertTrue("При ітерації не знайдено наступні задачі: " + titles, titles.isEmpty());
	}

	@Test(timeout = 1000)
	public void part2_testIteratorRemove() {
		List<Task> array = Arrays.asList(create("A"), create("B"), create("C"), create("D"));
		TaskList tasks = new TaskList();
		array.forEach(tasks::add);

		// saving order
		List<String> etalon = new ArrayList<>(array.size());
		for (Iterator<Task> it = tasks.iterator(); it.hasNext();) {
			etalon.add(new Task(it.next()).getTitle());
		}

		Iterator<Task> it = tasks.iterator();
		Iterator<String> etalonIt = etalon.iterator();
		try {
			it.remove();
			Assert.fail("Виклик Iterator.remove без next повинен призводити до помилки");
		}
		catch (IllegalStateException e) {
			// OK
		}

		String actual = new Task(it.next()).getTitle();
		String expected = etalonIt.next();
		Assert.assertEquals("При ітерації задачі повинні зберігати порядок. Задача " + actual + " знаходиться не на своєму місці", expected, actual);
		it.remove(); etalonIt.remove();
		actual = new Task(it.next()).getTitle();
		expected = etalonIt.next();
		Assert.assertEquals("При ітерації задачі повинні зберігати порядок. Задача " + actual + " знаходиться не на своєму місці", expected, actual);
		it.next(); etalonIt.next();
		it.remove(); etalonIt.remove();
		it.next(); etalonIt.next();

		it = tasks.iterator();
		etalonIt = etalon.iterator();
		actual = new Task(it.next()).getTitle();
		expected = etalonIt.next();
		Assert.assertEquals("Неочікуваний перший елемент після видалення", expected, actual);
		actual = new Task(it.next()).getTitle();
		expected = etalonIt.next();
		Assert.assertEquals("Неочікуваний Другий елемент після видалення", expected, actual);
		Assert.assertTrue("Після видалення у списку має бути два елемента", etalonIt.hasNext() == it.hasNext());
	}

	@Test(timeout = 1000)
	public void part3_testParallelIteration() {
		TaskList tasks = new TaskList();
		Arrays.asList(create("A"), create("B"), create("C")).forEach(tasks::add);
		Set<String> pairs = new HashSet<>(
				Arrays.asList( "AA", "AB", "AC", "BA", "BB", "BC", "CA", "CB", "CC" ));

		for (Iterator<Task> outer = tasks.iterator(); outer.hasNext();) {
			String out = new Task(outer.next()).getTitle();
			for (Iterator<Task> inner = tasks.iterator(); inner.hasNext();) {
				String in = new Task(inner.next()).getTitle();
				String it = out + in;
				Assert.assertTrue("Паралельна ітерація не повинна призводити до " + it, pairs.contains(it));
				pairs.remove(it);
			}
		}

		Assert.assertTrue("Паралельна ітерація повинна призводити до " + pairs, pairs.isEmpty());
	}
}
