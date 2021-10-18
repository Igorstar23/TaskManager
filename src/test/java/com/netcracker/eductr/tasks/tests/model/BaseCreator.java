package com.netcracker.eductr.tasks.tests.model;

public class BaseCreator {
	protected static boolean IS_OLD;

	public static void setIsOld(boolean isOld) {
		IS_OLD = isOld;
	}

	public static boolean isOld() {
		return IS_OLD;
	}
}
