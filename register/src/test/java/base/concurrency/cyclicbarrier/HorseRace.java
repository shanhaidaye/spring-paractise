package base.concurrency.cyclicbarrier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by shanhaidaye on 2016/8/30.
 */
public class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<Horse>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public HorseRace(int nHorses, final int pause) {

        //向CyclicBarrier 提供一个栅栏动作，是一个Runnable，当计数值达到0时自动执行，这里栅栏动作作为一个匿名内部类提供给CyclicBarrier的
        //构造器
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            public void run() {
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    s.append("=");
                }
                System.out.println(s);
                for (Horse horse : horses) {
                    System.out.println(horse.tracks());
                }
                for (Horse horse : horses) {
                    if (horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse + "won!");
                        exec.shutdownNow();
                        return;
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        System.out.println("verifyRoleExists2.rolename".toLowerCase().indexOf("rolename"));
//        int nHorses = 7;
//        int pause = 1000;
//        if (args.length > 0) {
//            int n = new Integer(args[0]);
//            nHorses = n > 0 ? n : nHorses;
//        }
//        if (args.length > 1) {
//            int p = new Integer(args[1]);
//            pause = p > -1 ? p : pause;
//        }
//        new HorseRace(nHorses, pause);
    }
}
