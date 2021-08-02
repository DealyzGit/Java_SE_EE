package org.fangsoft.testcenter.model;

import java.util.Date;

public class TestReservation {
    private int id;
    private Date orderDate;
    private Customer customer;
    private Test test;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;

    public static enum Status {
        ORDERED(0, "ordered"),
        PAYED(1, "payed"),
        FULFILLING(2, "fulfilling"),
        FULFILLED(3, "fulfilled");

        public void setIntVal(int intVal) {
            this.intVal = intVal;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        private int intVal;
        private String description;

        private Status(int intVal, String description) {
            this.intVal = intVal;
            this.description = description;
        }

        public int getIntVal() {
            return this.intVal;
        }

        public String getDescription() {
            return this.description;
        }
    }


}
