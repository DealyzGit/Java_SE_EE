package org.fangsoft.testcenter.web.bean;

import org.fangsoft.testcenter.model.TestReservation;
import org.fangsoft.testcenter.web.JSPUtil;

import java.io.Serializable;
import java.util.List;

public class TestReservationListBean implements Serializable {
    private String userId;

    private List<TestReservation> testReservationList;

    public int getSize() {
        if (this.testReservationList == null) {
            this.testReservationList = JSPUtil.getTestCenterFacade().
                    findActiveTestReservationByUserId(this.getUserId());
        }
        return this.testReservationList.size();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public List<TestReservation> getTestReservationList() {
        if (this.testReservationList == null) {
            this.testReservationList = JSPUtil.getTestCenterFacade().findActiveTestReservationByUserId(this.getUserId());
        }
            return this.testReservationList;
    }


}

