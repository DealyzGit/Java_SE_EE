package com.psbc.demo01;

public class TestThread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("1. reading code...."+i);
        }
    }

    public static void main(String[] args) {


        TestThread1 thread1 = new TestThread1();
        thread1.start();
        
        for (int i = 0; i < 200; i++) {
            System.out.println("2. reading code...."+i);
        }



    }
}
