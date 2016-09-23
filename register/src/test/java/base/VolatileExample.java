package base;

/**
 * Created by sunshiwang on 16/6/2.
 */
public class VolatileExample extends Thread {
    private static  boolean flag=false;
    public void run(){
        while (!flag){
//            System.out.println();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new VolatileExample().start();
        Thread.sleep(1000);
        flag=true;
    }
}
