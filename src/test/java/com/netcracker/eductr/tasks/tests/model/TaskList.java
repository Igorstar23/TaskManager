package com.netcracker.eductr.tasks.tests.model;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Stream;

public class TaskList extends BaseObject {
    private static Class<?> targetClass;

    public static void setTargetClass(Class<?> targetClass) {
        TaskList.targetClass = targetClass;
    }

    public TaskList(Object instance) {
        this.instance = instance;
    }

    public static Class<?> getTargetClass() {
        return targetClass;
    }

    public TaskList() {
        try {
            instance = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public int size() {
        try {
            return (int) targetClass.getMethod("size").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return Integer.MAX_VALUE;
        }
    }

    public void add(Task task) {
        try {
            targetClass.getMethod("add", Task.getTargetClass()).invoke(instance, task.getInstance());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }

    public Task getTask(int index) {
        try {
            return new Task(targetClass.getMethod("getTask", int.class).invoke(instance, index));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public boolean remove(Task task) {
        try {
            return (boolean) targetClass.getMethod("remove", Task.getTargetClass()).invoke(instance, task.getInstance());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return false;
        }
    }

    public TaskList incoming(int from, int to) {
        try {
            Object o = targetClass.getMethod("incoming", int.class, int.class).invoke(instance, from, to);
            targetClass = o.getClass();
            return new TaskList(o);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public void getTaskWithException(int index) throws Throwable {
        try {
            new Task(targetClass.getMethod("getTask", int.class).invoke(instance, index));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw e.getCause();
        }
    }

    public Iterator<Task> iterator() {
        try {
            return (Iterator<Task>) targetClass.getMethod("iterator").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public TaskList clone() {
        try {
            return new TaskList(targetClass.getMethod("clone").invoke(instance));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        try {
            return (boolean) targetClass.getMethod("equals", Object.class).invoke(instance, o instanceof TaskList ? ((TaskList) o).instance : o);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return false;
        }
    }

    public int hashCode() {
        try {
            return (int) targetClass.getMethod("hashCode").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return Integer.MIN_VALUE;
        }
    }

    public Stream<Task> getStream() {
        try {
            return (Stream<Task>) targetClass.getMethod("getStream").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
    public TaskList incoming(LocalDateTime from, LocalDateTime to) {
        try {
            Object o = targetClass.getMethod("incoming", LocalDateTime.class, LocalDateTime.class).invoke(instance, from, to);
            targetClass = o.getClass();
            return new TaskList(o);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
