package org.fangsoft.testcenter.model;

import java.io.Serializable;
import java.util.*;

public class TestResult implements Serializable {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private Customer customer;
    //    private int score;
    private Test test;
    //    private int pass;
    private Date startTime;
    private Date endTime;

    //    private int score ;
//    private int pass ;
    private List<QuestionResult> questionResult;

    public static enum Status {

        NEW(0, "new"),
        TESTING(1, "testing"),
        SUSPEND(2, "suspend"),
        FINISH(3, "finish");
        private int intVal;
        private String description;

        private Status(int intVal, String desc) {
            this.intVal = intVal;
            this.description = desc;
        }

        public int getIntVal() {
            return this.intVal;
        }

        public String getDescription() {
            return description;
        }
    }

    public static enum Result {
        PASS(1, "pass"),
        FAILURE(0, "fail"),
        UNKNOW(-1, "unknow");
        private String value;
        private int intVal;

        private Result(int intVal, String value) {
            this.value = value;
            this.intVal = intVal;
        }

        public String getValue() {
            return this.value;
        }

        public int getIntVal() {
            return this.intVal;
        }
    }

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

    public QuestionResult getQuestionResult(int index) {
        return questionResult.get(index);
    }

    public void setQuestionResult(List<QuestionResult> questionResult) {
        this.questionResult = questionResult;
    }

    private static final int UNKNOW_SCORE = 0;
    private static final int UNKNOW_PASS = -1;
    private static final int PASS_SUCCESS = 1;
    private static final int PASS_FAILURE = 0;

    private int score = UNKNOW_SCORE;//新建,考试得分未知
    private Result result;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    private Status status;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    //private int pass = UNKNOW_PASS; //新建,考试通过结果未知


    private int computeScore() {
        if (this.getStatus() == Status.FINISH) return this.score;
        if (this.score < 0) this.score = 0;
        for (QuestionResult qr : this.questionResult) {
            this.score += qr.computeAnswer();
        }
        return this.score;
    }

    private Result computePass() {
        int rights = 0;
        for (QuestionResult qr : this.questionResult) {
            if (qr.isResult()) {
                rights++;
            }
        }
        if (100 * rights >= 70 * this.getQuestionResult().size()) {
            this.setResult(Result.PASS);
        } else {
            this.setResult(Result.FAILURE);
        }
        return this.getResult();
    }

    public Result commitTest() {
        if (this.status == Status.FINISH) return this.getResult();
        else if (this.getStatus() == Status.TESTING) {
            this.computeScore();
            this.computePass();
            this.setStatus(Status.FINISH);
            return this.getResult();
        }
        this.setResult(Result.UNKNOW);
        return Result.UNKNOW;
    }

    public void suspendTest() {
        if (this.status != Status.TESTING)
            return;
        this.setEndTime(new Date());
        this.setStatus(Status.SUSPEND);
    }

    public void resumeTest() {
        if (this.status != Status.SUSPEND)
            return;
        long elapsedTime = this.getEndTime().getTime() -
                this.getStartTime().getTime();
        long testTime =
                this.getTest().getTimeLimitMin() - elapsedTime / (60 * 1000);
        this.getTest().setTimeLimitMin((int) testTime);
        this.setStatus(Status.TESTING);
    }

}
