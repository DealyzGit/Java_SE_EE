package org.fangsoft.testcenter.web.bean;

import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.web.JSPUtil;

import java.io.Serializable;

public class TestBean implements Serializable {
    private int testId;

    public Test getTest() {
        if (testId != 0) {
            Test test = JSPUtil.getTestCenterFacade().findTestByPK(this.testId);
        }
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    private Test test;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

}
