package com.psbc.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestThread2 extends Thread {
    private String url;
    private String name;


    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run(){
        WebDownload webDownload=new WebDownload();
        webDownload.downloader(url,name);
        System.out.println("Download Complete :"+name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("","");
        TestThread2 t2 = new TestThread2("","");
        TestThread2 t3 = new TestThread2("","");

        t1.start();
        t2.start();
        t3.start();
    }
}


class WebDownload{

    public void downloader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
