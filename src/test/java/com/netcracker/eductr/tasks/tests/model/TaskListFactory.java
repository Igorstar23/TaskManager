package com.netcracker.eductr.tasks.tests.model;

import com.netcracker.eductr.tasks.tests.utils.Types;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import static com.netcracker.eductr.tasks.tests.utils.Types.classTypes.ARRAY_LIST;

public class TaskListFactory {
    private static Class<?> targetClass;

    public static void setTargetClass(Class<?> targetClass) {
        TaskListFactory.targetClass = targetClass;
    }

    public static TaskList createTaskList(Types.classTypes type) {
        try {
            Class<?> enumClass = ListTypes.getEnumClass();
            String target = type == ARRAY_LIST ? "ARRAY" : "LINKED";
            Object param = Arrays.stream(enumClass.getFields()).filter(f -> target.equals(f.getName())).findFirst().get().get(null);
            return new TaskList(targetClass.getMethod("createTaskList", enumClass).invoke(null, param));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            return null;
        }
    }
}
