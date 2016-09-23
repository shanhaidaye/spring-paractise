package org.register.test;

/**
 * Created by shanhaidaye on 2016/6/29.
 */
class ThreadTest  implements Runnable
{
    private boolean flag = false;
    private int j = 0;
    public synchronized void son()
    {
        int time = 1;
        if(flag)
            try{this.wait();}catch(Exception e){e.printStackTrace();};
        for(int i = 0;i<10;i++)
        {
            System.out.println("sunThread..."+time++);
        }
        flag = true;
        this.notifyAll();
    }

    public synchronized void father()
    {
        int time = 1;
        if(!flag)
            try{this.wait();}catch(Exception e){e.printStackTrace();}
        for(int i = 0;i<100;i++)
        {
            System.out.println("mainThread..."+time++);
        }
        flag = false;
        this.notifyAll();
    }

    public void run() {

        for(int i = 0;i<50;i++)
        {
            if(j==0)
            {
                son();
            }else
            {
                father();
            }
            j=(j+1)%2;
        }
    }
}