package org.fangsoft.testcenter.business;

import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.dao.DaoFactory;
import org.fangsoft.testcenter.dao.TestReservationDao;
import org.fangsoft.testcenter.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestCenterFacade {
    private DaoFactory daoFactory;

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public TestCenterFacade(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;

    }

    public Customer login(String userId, String password) {
        return this.getDaoFactory().getCustomerDao().login(userId, password);
    }

    public boolean pay(int testReservationId) {
        TestReservationDao dao = this.getDaoFactory().getTestReservationDao();
        return dao.updateStatus(testReservationId, TestReservation.Status.PAYED);
    }

    public TestReservation reserveTest(int testId, Customer customer) {
        Test test = this.getDaoFactory().getTestDao().findTestByPK(testId);
        TestReservation testReservation = new TestReservation();
        testReservation.setCustomer(customer);
        testReservation.setOrderDate(new Date());
        testReservation.setStatus(TestReservation.Status.ORDERED);
        testReservation.setTest(test);
        this.getDaoFactory().getTestReservationDao().save(testReservation);
        return testReservation;
    }

    public TestResult startTest(int testId, int testReservationId, Customer customer) {
        Test test = this.getDaoFactory().getTestDao().findTestByPK(testId);
        this.getDaoFactory().getQuestionDao().loadQuestion(test);
        TestResult testResult = new TestResult();
        testResult.setCustomer(customer);
        testResult.setTest(test);
        testResult.setStartTime(new Date());
        testResult.setStatus(TestResult.Status.TESTING);
        testResult.setQuestionResult(new ArrayList<QuestionResult>(test.getQuestion().size()));
        for (Question q : test.getQuestion()) {
            QuestionResult qr = new QuestionResult();
            qr.setQuestion(q);
            testResult.getQuestionResult().add(qr);
        }
        updateTestReservationStatus(testReservationId,TestReservation.Status.FULFILLING);
        return testResult;
    }

    public void updateTestReservationStatus(int testReservationId, TestReservation.Status status) {
        this.getDaoFactory().getTestReservationDao().updateStatus(testReservationId, status);
    }

    public void commitTest(TestResult testResult, int testReservationId) {
        testResult.setEndTime(new Date());
        testResult.commitTest();
        this.getDaoFactory().getTestResultDao().save(testResult);
        updateTestReservationStatus(testReservationId, TestReservation.Status.FULFILLED);
    }

    public List<Test> findAllTest() {
        return this.getDaoFactory().getTestDao().findAllTest();
    }

    public List<TestReservation> findActiveTestReservationByUserId(String userId) {
        return this.getDaoFactory().getTestReservationDao().getTestReservationNotInStatus(userId, TestReservation.Status.FULFILLED);
    }

    public List<TestResult> findTestResultByUserId(String userId) {
        return this.getDaoFactory().getTestResultDao().findTestResultByCustomer(userId);
    }

    public Test findTestByPK(int testId) {
        return this.getDaoFactory().getTestDao().findTestByPK(testId);
    }

    public TestResult findTestResultByPK(int testResultId) {
        TestResult testResult = this.getDaoFactory().getTestResultDao().findTestResultByPK(testResultId);
        this.getDaoFactory().getTestResultDao().loadQuestionResult(testResult);
        return testResult;
    }

    private static final TestCenterFacade instance = new TestCenterFacade(Configuration.getDaoFactory());

    public static final TestCenterFacade getInstance() {
        return instance;
    }

    public int getRemainingTestTime(TestResult testResult) {
        int testTimeLimitSecond=testResult.getTest().getTimeLimitMin()*60;
        int passTimeSecond=(int)(System.currentTimeMillis()-
                testResult.getStartTime().getTime())/1000;
        return (testTimeLimitSecond-passTimeSecond);
    }

}
