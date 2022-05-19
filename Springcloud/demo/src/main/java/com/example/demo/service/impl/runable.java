package com.example.demo.service.impl;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yzj
 */
@Service
public class runable implements Runnable {
    private Long a = 10000L;
    private boolean flag = true;
    private final ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {
        while (flag) {
            exec();
        }
    }
   public void exec() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "**********" +a);
            a--;
            if (a < 1) {
                judge();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void judge() {
        this.flag = false;
    }


    /* 冒泡排序 */




    public static void main(String[] args) {


        runable runable = new runable();
        runable runable1 = new runable();
        runable runable2 = new runable();
        runable runable3 = new runable();
        runable runable4 = new runable();

        //1核心 2最大 3 超时等待 4队列 5工厂 6拒绝策略池中线程数小于corePoolSize，新任务过来会直接创建新线程来执行。
        //池中线程数大于等于corePoolSize，且workQueue未饱和，新任务过来会加入workQueue等待执行。
        //池中线程数大于等于corePoolSize，且workQueue已饱和，但线程数小于maximumPoolSize，新任务过来会创建新线程来执行。
        //池中线程数大于等于corePoolSize，且workQueue已饱和，并且线程数等于maximumPoolSize，新任务会被拒绝，拒绝策略就是handler。
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {
            executorService.execute(runable);
            executorService.execute(runable1);
            executorService.execute(runable2);
            executorService.execute(runable3);
            executorService.execute(runable4);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}