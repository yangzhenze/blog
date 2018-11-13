package com.yzz.blog.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zzy
 * @Date 2018/9/14 下午1:20
 */
public class ThreadPoolExecutor {
    private java.util.concurrent.ThreadPoolExecutor pool = null;

    /**
     * 线程池初始化方法
     *
     * corePoolSize 核心线程池大小----10
     * maximumPoolSize 最大线程池大小----30
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     * 							即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),
     * 						          任务会交给RejectedExecutionHandler来处理
     */
    public void init(int corePoolSize,int maximumPoolSize,int queueSize) {
        pool = new java.util.concurrent.ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                30,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(queueSize),
                new CustomThreadFactory());
        //new CustomRejectedExecutionHandler());
    }


    public void destroyNow() {
        if(pool != null) {
            pool.shutdownNow();
        }
    }
    public void destroy() {
        if(pool != null) {
            pool.shutdown();
        }
    }


    public ExecutorService getThreadPoolExecutor() {
        return this.pool;
    }

    private class CustomThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = ThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
            System.out.println(threadName);
            t.setName(threadName);
            return t;
        }
    }
}
