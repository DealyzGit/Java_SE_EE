package org.fangsoft.testcenter.data;

import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.dao.CustomerDao;
import org.fangsoft.testcenter.dao.QuestionDao;
import org.fangsoft.testcenter.dao.TestDao;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.Question;
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

    public static void exportTest2Database() throws Exception{
        TestDao tdao=Configuration.getDaoFactory().getTestDao();
        QuestionDao qdao=Configuration.getDaoFactory().getQuestionDao();
        for(String[][] data:TestData.allTest){
            Test test=TestData.newTest(data);
            TestData.loadQuestion(test, data);
            tdao.save(test);
            for(Question q:test.getQuestion()){
                qdao.addQuestionToTest(test, q);
            }
        }
    }
    public static void exportCustomer2Database() {
        CustomerDao cdao= Configuration.getDaoFactory().getCustomerDao();
        for(Customer c:CustomerData.getCustomer()){
            cdao.save(c);
        }
    }
    public static void main(String[] args) throws Exception{
//        exportTest2File();
//        exportCustomer2File();
        exportTest2Database();
        exportCustomer2Database();
    }

}


