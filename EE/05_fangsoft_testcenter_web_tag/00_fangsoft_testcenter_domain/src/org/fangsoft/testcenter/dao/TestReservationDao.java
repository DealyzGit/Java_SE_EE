package org.fangsoft.testcenter.dao;

import org.fangsoft.testcenter.model.TestReservation;
import org.fangsoft.testcenter.model.TestResult;

import java.util.List;

public interface TestReservationDao {
    public List<TestReservation> getTestReservation(String userId);
    public void save(TestReservation tr);
    public boolean updateStatus(int trId, TestReservation.Status status);
    public TestReservation findTestReservationByPK(int trId);
    public List<TestReservation> getTestReservationNotInStatus(String userId,TestReservation.Status status);
}
