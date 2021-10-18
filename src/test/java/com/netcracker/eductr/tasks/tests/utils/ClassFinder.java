package com.netcracker.eductr.tasks.tests.utils;

import com.netcracker.eductr.tasks.tests.model.*;

import java.io.*;
import java.time.LocalDateTime;

import static com.netcracker.eductr.tasks.tests.model.BaseCreator.isOld;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.*;

public class ClassFinder {
	private static final String BASE_PACKAGE = "ua.edu.sumdu.j2se.Lyfar.tasks";

	public static boolean checkClassExistence(Types.classTypes type) {
		try {
			switch (type) {
				case TASK_BASE:
					Class<?> clazz = getTargetClass(type);
					Task.setTargetClass(clazz);
					boolean isOld = false;
					try {
						isOld = clazz.getConstructor(String.class, int.class) != null;
					} catch (NoSuchMethodException e) {
					}
					BaseCreator.setIsOld(isOld);
					return true;

				case ARRAY_LIST:
				case LINKED_LIST:
				case ABSTRACT_LIST:
					TaskList.setTargetClass(getTargetClass(type));
					return true;

				case LIST_TYPES:
					ListTypes.setTargetClass(getTargetClass(type));
					return true;

				case TASK_LIST_FACTORY:
					TaskListFactory.setTargetClass(getTargetClass(type));
					return true;

				case TASKS:
					Tasks.setTargetClass(getTargetClass(type));
					return true;

				case TASK_IO:
					TaskIO.setTargetClass(getTargetClass(type));
					return true;

				default:
					return false;
			}
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	public static Class<?> getTargetClass(Types.classTypes clazz) throws ClassNotFoundException {
		switch (clazz) {
			case TASK_BASE:
				return Class.forName(BASE_PACKAGE + ".Task");
			case ARRAY_LIST:
				return Class.forName(BASE_PACKAGE + ".ArrayTaskList");
			case LINKED_LIST:
				return Class.forName(BASE_PACKAGE + ".LinkedTaskList");
			case ABSTRACT_LIST:
				return Class.forName(BASE_PACKAGE + ".AbstractTaskList");
			case LIST_TYPES:
				return Class.forName(BASE_PACKAGE + ".ListTypes");
			case TASK_LIST_FACTORY:
				return Class.forName(BASE_PACKAGE + ".TaskListFactory");
			case TASKS:
				return Class.forName(BASE_PACKAGE + ".Tasks");
			case TASK_IO:
				return Class.forName(BASE_PACKAGE + ".TaskIO");
			default:
				throw new ClassNotFoundException();
		}
	}

	public static boolean checkMethodExistence(Types.methodTypes method, Types.classTypes clazz) {
		try {
			switch (method) {
				case SIZE:
					return getTargetClass(clazz).getMethod("size") != null;
				case NEXT_TIME_AFTER:
					return (isOld()
							? getTargetClass(clazz).getMethod("nextTimeAfter", int.class)
							: getTargetClass(clazz).getMethod("nextTimeAfter", LocalDateTime.class)) != null;
				case ITERATOR:
					return getTargetClass(clazz).getMethod("iterator") != null;
				case CLONE:
					return getTargetClass(clazz).getMethod("clone") != null;
				case EQUALS:
					return getTargetClass(clazz).getMethod("equals", Object.class) != null;
				case HASH_CODE:
					return getTargetClass(clazz).getMethod("hashCode") != null;
				case GET_STREAM:
					return getTargetClass(clazz).getMethod("getStream") != null;
				case INCOMING:
					return getTargetClass(clazz).getMethod("incoming", Iterable.class, LocalDateTime.class, LocalDateTime.class) != null;
				case READ_BINARY:
					return getTargetClass(clazz).getMethod("read", getTargetClass(ABSTRACT_LIST), InputStream.class) != null
							&& getTargetClass(clazz).getMethod("readBinary", getTargetClass(ABSTRACT_LIST), File.class) != null;
				case WRITE_BINARY:
					return getTargetClass(clazz).getMethod("write", getTargetClass(ABSTRACT_LIST), OutputStream.class) != null
							&& getTargetClass(clazz).getMethod("writeBinary", getTargetClass(ABSTRACT_LIST), File.class) != null;
				case READ:
					return getTargetClass(clazz).getMethod("read", getTargetClass(ABSTRACT_LIST), Reader.class) != null
							&& getTargetClass(clazz).getMethod("readText", getTargetClass(ABSTRACT_LIST), File.class) != null;
				case WRITE:
					return getTargetClass(clazz).getMethod("write", getTargetClass(ABSTRACT_LIST), Writer.class) != null
							&& getTargetClass(clazz).getMethod("writeText", getTargetClass(ABSTRACT_LIST), File.class) != null;
				case GET_TIME:
					return getTargetClass(clazz).getMethod("getTime") != null;
				case GET_START_TIME:
					return getTargetClass(clazz).getMethod("getStartTime") != null;
				case GET_END_TIME:
					return getTargetClass(clazz).getMethod("getEndTime") != null;
				case SET_TIME:
					return (isOld()
							? getTargetClass(clazz).getMethod("setTime", int.class)
							: getTargetClass(clazz).getMethod("setTime", LocalDateTime.class)) != null;
				case IS_ACTIVE:
					return getTargetClass(clazz).getMethod("isActive") != null;
				case IS_REPEATED:
					return getTargetClass(clazz).getMethod("isRepeated") != null;
				case GET_REPEAT_INTERVAL:
					return getTargetClass(clazz).getMethod("getRepeatInterval") != null;
				case GET_TITLE:
					return getTargetClass(clazz).getMethod("getTitle") != null;
				case ADD:
					return getTargetClass(clazz).getMethod("add", getTargetClass(TASK_BASE)) != null;
				case GET_TASK:
					return getTargetClass(clazz).getMethod("getTask", int.class) != null;
			}

		} catch (ClassNotFoundException | NoSuchMethodException e) {
		}
		return false;
	}

	public static boolean checkDeclaredMethodExistence(Types.methodTypes method, Types.classTypes clazz) {
		try {
			switch (method) {
				case SIZE:
					return getTargetClass(clazz).getDeclaredMethod("size") != null;
				case ADD:
					return getTargetClass(clazz).getDeclaredMethod("add", getTargetClass(TASK_BASE)) != null;
				case REMOVE:
					return getTargetClass(clazz).getDeclaredMethod("remove", getTargetClass(TASK_BASE)) != null;
				case GET_TASK:
					return getTargetClass(clazz).getDeclaredMethod("getTask", int.class) != null;
				case INCOMING:
					return getTargetClass(clazz).getDeclaredMethod("incoming", int.class, int.class) != null;
				case EQUALS:
					return getTargetClass(clazz).getDeclaredMethod("equals", Object.class) != null;
				case HASH_CODE:
					return getTargetClass(clazz).getDeclaredMethod("hashCode") != null;
			}
		} catch (ClassNotFoundException | NoSuchMethodException e) {
		}
		return false;
	}

	public static boolean checkConstructorExistence(Types.constructorTypes constructor, Types.classTypes clazz) {
		try {
			switch (constructor) {
				case TITLE_TIME:
					return (isOld()
							? getTargetClass(clazz).getConstructor(String.class, int.class)
							: getTargetClass(clazz).getConstructor(String.class, LocalDateTime.class)) != null;
				case TITLE_START_END_INTERVAL:
					return (isOld()
							? getTargetClass(clazz).getConstructor(String.class, int.class, int.class, int.class)
							: getTargetClass(clazz).getConstructor(String.class, LocalDateTime.class, LocalDateTime.class, int.class)) != null;
			}
		} catch (NoSuchMethodException | ClassNotFoundException e) {
		}
		return false;
	}
}
