package org.fangsoft.testcenter.dao;

import org.fangsoft.testcenter.model.TestResult;

import java.util.List;

public interface TestResultDao {
    public List<TestResult> findTestResultByCustomer(String userId);
    public void  save(TestResult testResult);
}
