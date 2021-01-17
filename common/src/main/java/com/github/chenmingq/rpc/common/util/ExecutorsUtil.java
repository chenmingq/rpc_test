package com.github.chenmingq.rpc.common.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : cmq
 * date : 2021/01
 * description :
 */

@Slf4j
public class ExecutorsUtil {

    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final ExecutorService COMMON_EXECUTORS = new ThreadPoolExecutor(PROCESSORS, PROCESSORS,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadFactory() {
        final AtomicInteger count = new AtomicInteger(0);

        String name = "处理线程";

        @Override
        public Thread newThread(Runnable r) {
            int count = this.count.incrementAndGet();
            log.info("创建线程 {}-{} ", name, count);
            return new Thread(r, name + " - " + count);
        }
    });

    private static final ExecutorService SING_EXECUTORS = Executors.newSingleThreadExecutor(r -> new Thread(r, "单个线程池执行-"));


    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newScheduledThreadPool(2, new ThreadFactory() {
        final AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "定时器执行 - " + count.incrementAndGet());
        }
    });

    /**
     * 提交线程
     *
     * @param runnable
     */
    public static void execute(Runnable runnable) {
        COMMON_EXECUTORS.execute(runnable);
    }

    /**
     * 执行一个有返回结果的
     *
     * @param callable
     * @param <T>
     * @return
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return COMMON_EXECUTORS.submit(callable);
    }

    /**
     * 单个线程池提交
     *
     * @param runnable
     */
    public static void submitSing(Runnable runnable) {
        SING_EXECUTORS.execute(runnable);
    }

    /**
     * 定时一个任务
     *
     * @param runnable 要执行的任务
     * @param delay    从现在开始延迟执行的时间
     * @param timeUnit 延时参数的时间单位
     */
    public static void schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
        SCHEDULED_EXECUTOR_SERVICE.schedule(runnable, delay, timeUnit);
    }

    /**
     * 定时一个任务
     *
     * @param callable 要执行的任务
     * @param delay    从现在开始延迟执行的时间
     * @param timeUnit 延时参数的时间单位
     */
    public static <T> Future<T> schedule(Callable<T> callable, long delay, TimeUnit timeUnit) {
        return SCHEDULED_EXECUTOR_SERVICE.schedule(callable, delay, timeUnit);
    }
}
