package org.fangsoft.testcenter.web.bean;

import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.web.JSPUtil;

import java.io.Serializable;
import java.util.List;

public class TestResultListBean implements Serializable {
    private String userId;
    private List<TestResult> testResultList;
    public int getSize() {
        if(this.testResultList==null){
            this.testResultList= JSPUtil.getTestCenterFacade().
                    findTestResultByUserId(this.getUserId());
        }
        return this.testResultList.size();
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public List<TestResult> getTestResultList() {
        if(this.testResultList==null){
            this.testResultList=JSPUtil.getTestCenterFacade().
                    findTestResultByUserId(this.getUserId());
        }
        return this.testResultList;
    }
}
