package org.fangsoft.testcenter.dao;

import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.model.TestResult;

import java.util.List;

public interface QuestionResultDao {
    public List<QuestionResult> findQuestionResultByCustomer(String userId);
    public void  save(QuestionResult questionResult,TestResult testResult);
}
