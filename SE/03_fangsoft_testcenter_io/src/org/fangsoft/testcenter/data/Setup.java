package org.fangsoft.testcenter.data;

import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.Test;

public class Setup {
    public static void exportTest2File() {
        for(String[][] data:TestData.allTest){
            Test test=TestData.newTest(data);
            TestData.loadQuestion(test, data);
            Configuration.getDaoFactory().getTestDao().save(test);
        }
    }
    public static void exportCustomer2File() {
        for(Customer c:CustomerData.getCustomer()){
            Configuration.getDaoFactory().getCustomerDao().save(c);
        }
    }
    public static void main(String[] args){
        exportTest2File();
        exportCustomer2File();
    }
}


