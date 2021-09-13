package com.psbc.demo01;

public class TestThread3 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            System.out.println("1. reading code...."+i);
        }
    }

    public static void main(String[] args) {


        TestThread3 thread3 = new TestThread3();
        new Thread(thread3).start();

        for (int i = 0; i < 200; i++) {
            System.out.println("2. reading code...."+i);
        }



    }
}
