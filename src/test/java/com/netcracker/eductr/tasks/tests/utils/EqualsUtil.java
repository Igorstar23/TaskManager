package com.netcracker.eductr.tasks.tests.utils;

import com.netcracker.eductr.tasks.tests.model.Task;
import com.netcracker.eductr.tasks.tests.model.TaskList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.getTargetClass;
import static com.netcracker.eductr.tasks.tests.utils.TaskListUtils.describeTasks;
import static com.netcracker.eductr.tasks.tests.utils.TaskListUtils.tasksToList;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.ABSTRACT_LIST;


public class EqualsUtil {
	private static List<String> left;
	private static List<Task> extra = new ArrayList<>();
	private static List<Task> missing = new ArrayList<>();

	public static boolean areEqual(List<Task> actual, List<Task> expected) {
		left = expected.stream().map(e -> e.getTitle()).collect(Collectors.toList());
		extra.addAll(actual);
		extra.removeIf(t -> left.contains(t.getTitle()));

		left = actual.stream().map(e -> e.getTitle()).collect(Collectors.toList());
		missing.addAll(expected);
		missing.removeIf(t -> left.contains(t.getTitle()));
		return extra.size() == 0 && missing.size() == 0;
	}

	public static String getMessage() {
		StringBuilder sb = new StringBuilder();
		if (extra.size() != 0) sb.append("Unexpected tasks found: " + describeTasks(extra));
		if (missing.size() != 0) {
			if (sb.length() > 0) sb.append("\n");
			sb.append("Missing tasks found: " + describeTasks(missing));
		}
		return sb.toString();
	}

	public static boolean hasParent(Types.classTypes type) {
		try {
			return getTargetClass(type).getSuperclass().equals(getTargetClass(ABSTRACT_LIST));
		} catch (ClassNotFoundException e) {

		}
		return false;
	}

	public static boolean sameTypes(Types.classTypes clazz, TaskList taskList) {
		try {
			return getTargetClass(clazz).equals(taskList.getInstance().getClass());
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static boolean areEqual(TaskList list1, TaskList list2) {
		return list1.size() == list2.size() && areEqual(tasksToList(list1), tasksToList(list2));
	}

	public static boolean areEqual(Task t1, Task t2) {
		return t1 != null && t2 != null && t1.getTitle().equals(t2.getTitle()) && t1.getTime().equals(t2.getTime());
	}

	public static boolean areEqual(Collection<Task> list1, Collection<Task> list2) {
		return areEqual(new ArrayList<>(list1), new ArrayList<>(list2));
	}
}
