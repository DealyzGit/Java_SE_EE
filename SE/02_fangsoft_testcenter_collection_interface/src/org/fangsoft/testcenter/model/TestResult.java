package org.fangsoft.testcenter.model;

import java.util.Date;
import java.util.List;

public class TestResult {
    private Customer customer;
    //    private int score;
    private Test test;
    //    private int pass;
    private java.util.Date startTime;
    private java.util.Date endTime;

//    private int score ;
//    private int pass ;
    private List<QuestionResult> questionResult;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<QuestionResult> getQuestionResult() {
        return questionResult;
    }

    public void setQuestionResult(List<QuestionResult> questionResult) {
        this.questionResult = questionResult;
    }

    private static final int UNKNOW_SCORE = 0;
    private static final int UNKNOW_PASS = -1;
    private static final int PASS_SUCCESS = 1;
    private static final int PASS_FAILURE = 0;

    private int score = UNKNOW_SCORE;//新建,考试得分未知
    private int pass = UNKNOW_PASS; //新建,考试通过结果未知

    protected int computeScore() {
        for (QuestionResult qr : this.questionResult) {
            this.score += qr.computeAnswer();
        }
        if (this.score == UNKNOW_SCORE) this.score = 0;
        if (this.score != UNKNOW_SCORE) return this.score;
        return this.score;
    }

    protected boolean computePass() {
        if (this.pass != UNKNOW_PASS) {
            return this.pass == PASS_SUCCESS ? true : false;
        }
        int rights = 0;
        for (QuestionResult qr : this.questionResult) {
            if (qr.isResult()) {
                rights++;
            }
        }
        if (100 * rights >= 70 * this.getQuestionResult().size()) {
            this.pass = PASS_SUCCESS;
            return true;
        } else {
            this.pass = PASS_FAILURE;
            return false;
        }
    }

    public boolean commitTest() {
        this.computeScore();
        return this.computePass();
    }
}
