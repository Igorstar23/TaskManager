package com.netcracker.eductr.tasks.tests.model;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

public class Tasks extends BaseObject {
    private static Class<?> targetClass;

    public static void setTargetClass(Class<?> targetClass) {
        Tasks.targetClass = targetClass;
    }

    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        try {
            List listParam = new ArrayList();
            for (Task t : tasks) listParam.add(t.getInstance());
            return (Iterable<Task>) targetClass.getMethod("incoming", Iterable.class, LocalDateTime.class, LocalDateTime.class).invoke(null, listParam, start, end);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        List listParam = new ArrayList();
        for (Task t : tasks) listParam.add(t.getInstance());
        try {
            return (SortedMap<LocalDateTime, Set<Task>>) targetClass.getMethod("calendar", Iterable.class, LocalDateTime.class, LocalDateTime.class).invoke(null, listParam, start, end);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
