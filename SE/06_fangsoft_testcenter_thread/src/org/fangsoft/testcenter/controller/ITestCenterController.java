package org.fangsoft.testcenter.controller;

import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.model.TestResult;

import java.util.List;

public interface ITestCenterController {
    public abstract Customer login(String userId, String password);
    public abstract List<String> findAllTestNames();
    public abstract Test selectTest(String testName);
    public abstract TestResult startTest(Test test,Customer customer);
    public abstract void commitTest(TestResult testResult);
}
