package com.qtt.framework.util;

public class MemoryUtils {
	public static long getFreeMemorySize() {
	    long freeSize = 0L;
	    //long totalSize = 0L;
	    //long usedSize = -1L;
	    try {
	        Runtime info = Runtime.getRuntime();
	        freeSize = info.freeMemory();
	        //totalSize = info.totalMemory();
	        //usedSize = totalSize - freeSize;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return freeSize;
	}
}
