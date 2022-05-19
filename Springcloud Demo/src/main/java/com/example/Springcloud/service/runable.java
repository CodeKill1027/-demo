package com.example.Springcloud.service;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yzj
 */
@Service
public class runable implements Runnable {
    Integer goods =1000;
    Logger logger = Logger.getLogger(runable.class);
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

           if (goods>= 1){
               goods--;
               logger.debug("所剩商品数量:"+goods);
           }else{
               logger.debug("无商品");
               judge();
           }
           } finally{
               lock.unlock();
           }
   }
    public void judge() {this.flag = false;}

    public static void main(String[] args) {
        runable runable = new runable();
        //1核心 2最大 3 超时等待 4队列 5工厂 6拒绝策略池中线程数小于corePoolSize，新任务过来会直接创建新线程来执行。
        //池中线程数大于等于corePoolSize，且workQueue未饱和，新任务过来会加入workQueue等待执行。
        //池中线程数大于等于corePoolSize，且workQueue已饱和，但线程数小于maximumPoolSize，新任务过来会创建新线程来执行。
        //池中线程数大于等于corePoolSize，且workQueue已饱和，并且线程数等于maximumPoolSize，新任务会被拒绝，拒绝策略就是handler。0.  max-core=空闲线程，超过指定时间就被淘汰
        ExecutorService executorService = new ThreadPoolExecutor(1, 2, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        try {
            executorService.execute(runable);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}