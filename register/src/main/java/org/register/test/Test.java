package org.register.test;

/**
 * Created by shanhaidaye on 2016/6/29.
 */
public class Test {

    public static void main(String[] args) {
//        ThreadTest test=new ThreadTest();
        Thread1 test=new Thread1();
        Thread t1=new Thread(test);
        Thread t2=new Thread(test);
        t1.run();
        System.out.println("T1之星");
        t2.run();
        System.out.println("T2之星");
    }
}
