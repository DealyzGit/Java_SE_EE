package org.fangsoft.testcenter.server;

import org.fangsoft.testcenter.controller.TestCenterController;
import org.fangsoft.testcenter.dao.DaoFactory;
import org.fangsoft.testcenter.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerTestCenterController extends TestCenterController {
    private DaoFactory daoFactory;

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public ServerTestCenterController() {}
    public boolean isTestTimeout(){
        return (System.currentTimeMillis()-this.getTestDeadTime()>0);
    }
    public void handleTimeOut(){}

    public Customer login(String userId, String password) {
        return daoFactory.getCustomerDao().login(userId,password);
    }
    public List<String> findAllTestNames() {
        return this.getDaoFactory().getTestDao().findAllTestNames();
    }
    public Test selectTest(String testName) {
        return this.getDaoFactory().getTestDao().findTestByName(testName);
    }

    private Test loadQuestion(Test test) {
        this.getDaoFactory().getQuestionDao().loadQuestion(test);
        return test;
    }
    public TestResult startTest(Test test, Customer customer){
        this.loadQuestion(test);
        TestResult testResult=new TestResult();
        testResult.setCustomer(customer);
        testResult.setTest(test);
        testResult.setStartTime(new Date());
        long testDeadTime=testResult.getStartTime().getTime()+
                test.getTimeLimitMin()*1000*60;
        this.setTestDeadTime(testDeadTime);
        testResult.setStatus(TestResult.Status.TESTING);
        testResult.setQuestionResult(
                new ArrayList<QuestionResult>(test.getQuestion().size()));
        for(Question q:test.getQuestion()){
            QuestionResult qr=new QuestionResult();
            qr.setQuestion(q);
            testResult.getQuestionResult().add(qr);
        }
        return testResult;
    }
    public void commitTest(TestResult testResult ){
        testResult.setEndTime(new Date());
        testResult.commitTest();
        this.getDaoFactory().getTestResultDao().save(testResult);
    }


}
