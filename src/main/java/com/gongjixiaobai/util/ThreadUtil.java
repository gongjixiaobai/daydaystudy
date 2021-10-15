package com.gongjixiaobai.util;


/**
 * @author weiming02
 */
public class ThreadUtil {
    public static java.util.List<Thread> listThreads() {
        int tc = Thread.activeCount();
        Thread[] ts = new Thread[tc];
        Thread.enumerate(ts);
        return java.util.Arrays.asList(ts);
    }
}
