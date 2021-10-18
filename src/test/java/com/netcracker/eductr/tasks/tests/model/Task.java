package com.netcracker.eductr.tasks.tests.model;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

public class Task extends BaseObject {
    private static Class<?> targetClass;

    public static void setTargetClass(Class<?> targetClass) {
        Task.targetClass = targetClass;
    }

    public Task(String title, int time) {
        try {
            instance = targetClass.getConstructor(String.class, int.class).newInstance(title, time);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        }
    }

    public Task(Object instance) {
        this.instance = instance;
    }

    public String getTitle() {
        try {
            return (String) targetClass.getMethod("getTitle").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public void setTitle(String title) {
        try {
            targetClass.getMethod("setTitle", String.class).invoke(instance, title);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }

    public boolean isRepeated() {
        try {
            return (boolean) targetClass.getMethod("isRepeated").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return false;
        }
    }

    public boolean isActive() {
        try {
            return (boolean) targetClass.getMethod("isActive").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return false;
        }
    }

    public Object getTime() {
        try {
            return targetClass.getMethod("getTime").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public Object getStartTime() {
        try {
            return targetClass.getMethod("getStartTime").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public Object getEndTime() {
        try {
            return targetClass.getMethod("getEndTime").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public int getRepeatInterval() {
        try {
            return (int) targetClass.getMethod("getRepeatInterval").invoke(instance);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return Integer.MIN_VALUE;
        }
    }

    public Task(String title, int start, int end, int interval) {
        try {
            instance = targetClass.getConstructor(String.class, int.class, int.class, int.class).newInstance(title, start, end, interval);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }

    public void setTime(int time) {
        try {
            targetClass.getMethod("setTime", int.class).invoke(instance, time);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }

    public void setTime(int start, int end, int interval) {
        try {
            targetClass.getMethod("setTime", int.class, int.class, int.class).invoke(instance, start, end, interval);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }

    public void setActive(boolean active) {
        try {
            targetClass.getMethod("setActive", boolean.class).invoke(instance, active);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }

    public int nextTimeAfter(int time) {
        try {
            return (int) targetClass.getMethod("nextTimeAfter", int.class).invoke(instance, time);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return Integer.MIN_VALUE;
        }
    }

    public static Class<?> getTargetClass() {
        return targetClass;
    }

    public static void createWithException(String title, int time) throws Throwable {
        try {
            targetClass.getConstructor(String.class, int.class).newInstance(title, time);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw e.getCause();
        }
    }

    public Task clone() {
        try {
            return new Task(targetClass.getMethod("clone").invoke(instance));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }

    public boolean equals(Object o) {
        try {
            return (boolean) targetClass.getMethod("equals", Object.class).invoke(instance, o instanceof Task ? ((Task) o).instance : o);
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

    public Task(String title, LocalDateTime time) {
        try {
            instance = targetClass.getConstructor(String.class, LocalDateTime.class).newInstance(title, time);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
        }
    }

    public void setTime(LocalDateTime time) {
        try {
            targetClass.getMethod("setTime", LocalDateTime.class).invoke(instance, time);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        try {
            instance = targetClass.getConstructor(String.class, LocalDateTime.class, LocalDateTime.class, int.class).newInstance(title, start, end, interval);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }

    public void setTime(LocalDateTime start, LocalDateTime end, int interval) {
        try {
            targetClass.getMethod("setTime", LocalDateTime.class, LocalDateTime.class, int.class).invoke(instance, start, end, interval);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {

        }
    }

    public LocalDateTime nextTimeAfter(LocalDateTime time) {
        try {
            return (LocalDateTime) targetClass.getMethod("nextTimeAfter", LocalDateTime.class).invoke(instance, time);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return LocalDateTime.MIN;
        }
    }

    public static void createWithException(String title, LocalDateTime time) throws Throwable {
        try {
            targetClass.getConstructor(String.class, LocalDateTime.class).newInstance(title, time);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw e.getCause();
        }
    }
}
