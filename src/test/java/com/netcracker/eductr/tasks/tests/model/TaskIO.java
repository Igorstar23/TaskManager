package com.netcracker.eductr.tasks.tests.model;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

import static com.netcracker.eductr.tasks.tests.utils.ClassFinder.getTargetClass;
import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.ABSTRACT_LIST;

public class TaskIO extends BaseObject {
	private static Class<?> targetClass;

	public static void setTargetClass(Class<?> targetClass) {
		TaskIO.targetClass = targetClass;
	}

	public static void write(TaskList tasks, OutputStream out) {
		try {
			targetClass.getMethod("write", getTargetClass(ABSTRACT_LIST), OutputStream.class).invoke(null, tasks.getInstance(), out);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void read(TaskList tasks, InputStream in) {
		try {
			targetClass.getMethod("read", getTargetClass(ABSTRACT_LIST), InputStream.class).invoke(null, tasks.getInstance(), in);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void writeBinary(TaskList tasks, File file) {
		try {
			targetClass.getMethod("writeBinary", getTargetClass(ABSTRACT_LIST), File.class).invoke(null, tasks.getInstance(), file);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void readBinary(TaskList tasks, File file) {
		try {
			targetClass.getMethod("readBinary", getTargetClass(ABSTRACT_LIST), File.class).invoke(null, tasks.getInstance(), file);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void write(TaskList tasks, Writer out) {
		try {
			targetClass.getMethod("write", getTargetClass(ABSTRACT_LIST), Writer.class).invoke(null, tasks.getInstance(), out);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void read(TaskList tasks, Reader in) {
		try {
			targetClass.getMethod("read", getTargetClass(ABSTRACT_LIST), Reader.class).invoke(null, tasks.getInstance(), in);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void writeText(TaskList tasks, File file) {
		try {
			targetClass.getMethod("writeText", getTargetClass(ABSTRACT_LIST), File.class).invoke(null, tasks.getInstance(), file);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void readText(TaskList tasks, File file) {
		try {
			targetClass.getMethod("readText", getTargetClass(ABSTRACT_LIST), File.class).invoke(null, tasks.getInstance(), file);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
