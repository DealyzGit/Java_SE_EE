package com.psbc.demo01;

public class TestThread4 implements Runnable {
    private int ticketNums = 10;


    @Override
    public void run() {
        while (true) {
            if (ticketNums <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->get " + ticketNums--);

        }
    }


    public static void main(String[] args) {

        TestThread4 thread4 = new TestThread4();
        new Thread(thread4, "A").start();
        new Thread(thread4, "B").start();
        new Thread(thread4, "C").start();
    }
}
