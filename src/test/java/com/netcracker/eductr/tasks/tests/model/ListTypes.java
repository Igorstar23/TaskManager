package com.netcracker.eductr.tasks.tests.model;

import java.util.Arrays;
import java.util.List;

public class ListTypes {
    private static Class<?> targetClass;
    private static List<String> set = Arrays.asList("ARRAY", "LINKED");

    public static void setTargetClass(Class<?> targetClass) {
        ListTypes.targetClass = targetClass;
    }

    public static int count() {
        return targetClass.getClasses()[0].getFields().length;
    }

    public static boolean isRightNames() {
        return Arrays.stream(targetClass.getClasses()[0].getFields()).map(f -> f.getName()).filter(n -> !set.contains(n)).count() == 0;
    }

    public static Class<?> getEnumClass() {
        return targetClass.getClasses()[0];
    }
}
