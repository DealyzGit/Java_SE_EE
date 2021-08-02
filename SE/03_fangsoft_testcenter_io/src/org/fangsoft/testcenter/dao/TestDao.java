package org.fangsoft.testcenter.dao;

import org.fangsoft.testcenter.model.Test;

import java.util.List;

public interface TestDao {
    public Test findTestByName(String testName);
    public List<Test> findAllTest();
    public void save(Test test);
}

