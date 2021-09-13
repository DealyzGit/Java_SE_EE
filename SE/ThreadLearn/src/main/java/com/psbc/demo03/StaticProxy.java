package com.psbc.demo03;

public class StaticProxy {
    public static void main(String[] args) {

        weddingCompany weddingCompany = new weddingCompany(new You());
        weddingCompany.HappyMarry();
    }
}

interface Marry {

    void HappyMarry();
}


class You implements Marry {

    @Override
    public void HappyMarry() {
        System.out.println("Married !");
    }
}

class weddingCompany implements Marry {

    private Marry target;

    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }


    public weddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {

        before();
        this.target.HappyMarry();
        after();

    }
}