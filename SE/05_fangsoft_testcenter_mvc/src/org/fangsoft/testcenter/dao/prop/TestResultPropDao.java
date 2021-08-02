package org.fangsoft.testcenter.dao.prop;

import org.fangsoft.testcenter.dao.DaoIOConfig;
import org.fangsoft.testcenter.dao.TestResultDao;
import org.fangsoft.testcenter.model.Question;
import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.model.TestResult;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TestResultPropDao implements TestResultDao {
    @Override
    public List<TestResult> findTestResultByCustomer(String userId) {
        String path = DaoIOConfig.getTestResultFilePath(userId);
        File[] files = new File(path).listFiles();
        if (files == null || files.length == 0) return new ArrayList<TestResult>(0);
        ArrayList<TestResult> testResults = new ArrayList<>(files.length);
        Properties ps = new Properties();
        try {
            for (File f : files) {
                if (f.isDirectory()) continue;
                ps.load(new FileReader(f));
                testResults.add(Property2Object.toTestResult(ps));
                ps.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        testResults.trimToSize();
        return testResults;
    }

    @Override
    public void save(TestResult testResult) {
        Properties ps = Property2Object.toProperties(testResult);
        String path = DaoIOConfig.getTestResultFilePath(testResult.getCustomer().getUserId());
        String fileName = DaoIOConfig.getFileName(testResult);
        new File(path).mkdirs();
        try {
            fileName = fileName.replace(":", "_");
            ps.store(new FileWriter(path + fileName), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<QuestionResult> qrList=new ArrayList<>(testResult.getQuestionResult().length);
        for (QuestionResult q:testResult.getQuestionResult()
             ) {
            QuestionResultPropDao.getInstance().save(q,testResult);
        }

    }

    private static final TestResultPropDao testDao = new TestResultPropDao();

    public static final TestResultPropDao getInstance() {
        return testDao;
    }

    private TestResultPropDao() {
    }

}
