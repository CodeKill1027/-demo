package com.example.service;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yzj
 */
public class runable implements Runnable {
    private int a = 10000;
    private boolean flag = true;
    private final ReentrantLock lock = new ReentrantLock();



    @Override
    public void run() {
        while (flag) {
            Exec();
        }
    }

    public void Exec() {
        lock.lock();
        try {
            Thread.currentThread().setName("线程1");

            System.out.println(Thread.currentThread().getName() + "--------" + a);
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


    public static void main(String[] args) {


        runable runable = new runable();
        runable runable1 = new runable();
        runable runable2 = new runable();

        //1核心 2最大 3 超时等待 4队列 5工厂 6拒绝策略
        ExecutorService executorService = new ThreadPoolExecutor(4, 6, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {

            executorService.execute(runable);
            executorService.execute(runable1);
            executorService.execute(runable2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }



    }
}