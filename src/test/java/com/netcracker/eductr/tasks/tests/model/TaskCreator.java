package com.netcracker.eductr.tasks.tests.model;

import java.time.LocalDateTime;

import static com.netcracker.eductr.tasks.tests.utils.DatesTimes.FROM_NOW_10;
import static com.netcracker.eductr.tasks.tests.utils.DatesTimes.NOW;

public class TaskCreator extends BaseCreator {
    public static Task create(String title) {
        return IS_OLD ? create(title, 5) : create(title, NOW);
    }

    public static Task create(String title, int time, boolean active) {
        Task task = create(title, time);
        task.setActive(active);
        return task;
    }

    public static Task create(String title, int time) {
        return new Task(title, time);
    }

    public static Task create(String title, int start, int end, int interval) {
        return new Task(title, start, end, interval);
    }

    public static Task create(String title, int start, int end, int interval, boolean active) {
        Task task = create(title, start, end, interval);
        task.setActive(active);
        return task;
    }

    public static Task create(String title, LocalDateTime start, LocalDateTime end, int interval) {
        return new Task(title, start, end, interval);
    }

    public static Task create(String title, LocalDateTime dateTime) {
        return new Task(title, dateTime);
    }

    public static Task create(String title, LocalDateTime dateTime, boolean active) {
        Task task = create(title, dateTime);
        task.setActive(active);
        return task;
    }

    public static Task create(String title, LocalDateTime start, LocalDateTime end, int interval, boolean active) {
        Task task = create(title, start, end, interval);
        task.setActive(active);
        return task;
    }

    public static Task createA() {
        return IS_OLD ? create("A", 10) : create("A", NOW);
    }

    public static Task createB() {
        return IS_OLD ? create("B", 20) : create("B", FROM_NOW_10);
    }

    public static void modify(Task t) {
        t.setTitle("Some task");
        if (IS_OLD) t.setTime(42);
        else t.setTime(NOW);
        t.setActive(true);
    }
}
