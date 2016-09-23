package org.register.test;

/**
 * Created by shanhaidaye on 2016/6/29.
 */
class MultiThread {
    private Boolean isMainThreadWait = true; // 用于保证SubThread先执行

    public static void main(String[] args) {
        MultiThread test = new MultiThread();
        Thread mainThread = test.new MainThread();
        Thread subThread = test.new SubThread();
        mainThread.start();
        subThread.start();

                                try {
            Thread.sleep(5000);
            System.out.println("main");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class MainThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                synchronized (MultiThread.class) {
                    if (isMainThreadWait) {
                        try {
                            MultiThread.class.wait();
                            System.out.println("mainThread wait completed");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    for (int j = 0; j < 5; j++) {
                        System.out.println("MainThread" + "i=" + i + "j=" + j);
                    }
                    System.out.println("MainThreadNotify" + i);
                    isMainThreadWait = true;
                    MultiThread.class.notify();

                    try {
                        Thread.sleep(100);
                        System.out.println("MainThread sleep completed");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return;
        }
    }

    private class SubThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                synchronized (MultiThread.class) {
                    if (!isMainThreadWait) {
                        try {
                            MultiThread.class.wait();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    for (int j = 0; j < 5; j++) {
                        System.out.println("SubThread" + "i=" + i + "j=" + j);
                    }
                    System.out.println("SubThreadNotify" + i);
                    isMainThreadWait = false;
                    MultiThread.class.notify();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return;
        }
    }
}

