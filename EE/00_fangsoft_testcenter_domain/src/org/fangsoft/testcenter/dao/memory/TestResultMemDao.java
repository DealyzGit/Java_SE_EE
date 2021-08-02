package org.fangsoft.testcenter.dao.memory;

import org.fangsoft.testcenter.dao.TestResultDao;
import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.model.TestResult;

import java.util.ArrayList;
import java.util.List;

public class TestResultMemDao implements TestResultDao {
    public List<TestResult> findTestResultByCustomer(String userId) {
        List<TestResult> testResultList= DataRepository.testResultMapForUserId.get(userId);
        return testResultList==null?new ArrayList<TestResult>(0):testResultList;
    }
    public TestResult findTestResultByPK(int id) {
        return DataRepository.testResultMapForPK.get(id);
    }
    public boolean loadQuestionResult(TestResult testResult) {
        List<QuestionResult>  questionResultList=
                DataRepository.questionResultMap.get(testResult.getId());
        if(questionResultList==null)return false;
        testResult.setQuestionResult(questionResultList);
        return true;
    }
    public void save(TestResult testResult) {
        int testResultId=testResult.getId()==0?1:testResult.getId();
        if(DataRepository.testResultMapForPK.get(testResultId)!=null){
            for(int id:DataRepository.testResultMapForPK.keySet()){
                testResultId+=id;
            }
        }
        testResult.setId(testResultId);
        DataRepository.testResultMapForPK.put(testResult.getId(), testResult);
        String userId=testResult.getCustomer().getUserId();
        if(DataRepository.testResultMapForUserId.get(userId)==null){
            DataRepository.testResultMapForUserId.put(userId,new ArrayList<TestResult>());
        }
        DataRepository.testResultMapForUserId.get(userId).add(testResult);
        DataRepository.questionResultMap.put(testResult.getId(),testResult.getQuestionResult());
    }


}
