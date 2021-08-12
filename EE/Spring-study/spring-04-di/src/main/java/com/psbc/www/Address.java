package com.psbc.www;

public class Address {
    private String addrss;

    public String getAddrss() {
        return addrss;
    }

    public void setAddrss(String addrss) {
        this.addrss = addrss;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addrss='" + addrss + '\'' +
                '}';
    }
}
