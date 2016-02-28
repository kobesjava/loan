package com.finance.framework.util;

public class CheckDoubleClick {
	private static long lastClickTime = 0;

	public static boolean isFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 500) {
			return true;
		}
		lastClickTime = time;
		return false;
	}

	public static boolean isLongFastDoubleClick() {
		long time = System.currentTimeMillis();
		long timeD = time - lastClickTime;
		if (0 < timeD && timeD < 700) {
			return true;
		}
		lastClickTime = time;
		return false;
	}
}