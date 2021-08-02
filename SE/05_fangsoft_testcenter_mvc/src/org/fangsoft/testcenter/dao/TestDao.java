package org.fangsoft.testcenter.dao;

import org.fangsoft.testcenter.db.SQLUtil;
import org.fangsoft.testcenter.model.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TestDao {
    public Test findTestByName(String testName);
    public List<Test> findAllTest();
    public void save(Test test);
    public List<String> findAllTestNames();


}

