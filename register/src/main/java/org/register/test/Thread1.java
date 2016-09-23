package org.register.test;

/**
 * Created by shanhaidaye on 2016/6/29.
 */
public class Thread1 implements Runnable{

    public static boolean flag=false;
    public void run() {
        while (true){
            doSomething();
        }

    }

    public  void doSomething() {
        synchronized (Thread1.class) {
            try {
                if (!flag) {
                    Thread1.class.wait(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().toString() + " do");
            flag = true;
            this.notifyAll();
        }
    }
}
