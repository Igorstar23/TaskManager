package com.netcracker.eductr.tasks.tests.model;

import java.util.stream.IntStream;

import static com.netcracker.eductr.tasks.tests.model.TaskCreator.create;
import static com.netcracker.eductr.tasks.tests.utils.DatesTimes.TODAY;
import static com.netcracker.eductr.tasks.tests.utils.DatesTimes.TOMORROW;

public class TaskListCreator extends BaseCreator {

	public static TaskList createA() {
		TaskList list = new TaskList();
		Task A = TaskCreator.createA();
		IntStream.range(0, 10).forEach(i -> list.add(A));
		return list;
	}

	public static TaskList createB() {
		TaskList list = new TaskList();
		Task B = TaskCreator.createB();
		IntStream.range(0, 10).forEach(i -> list.add(B));
		return list;
	}

	public static void modify(TaskList list) {
		IntStream.range(0, list.size()).mapToObj(i -> list.getTask(0)).forEach(list::remove);
		Task A = TaskCreator.createA();
		IntStream.range(0, 10).forEach(i -> list.add(A));
	}

	public static TaskList createList() {
		TaskList tasks = new TaskList();
		tasks.add(create("A", TODAY));
		tasks.add(create("B", TODAY, TOMORROW, 3600));
		Task t = create("C", TODAY);
		t.setActive(true);
		tasks.add(t);
		t = create("B", TODAY, TOMORROW, 3600);
		t.setActive(true);
		tasks.add(t);
		return tasks;
	}
}
