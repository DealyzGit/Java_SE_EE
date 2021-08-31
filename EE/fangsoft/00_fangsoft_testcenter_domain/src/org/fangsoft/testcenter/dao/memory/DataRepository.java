package org.fangsoft.testcenter.dao.memory;

import org.fangsoft.testcenter.data.CustomerData;
import org.fangsoft.testcenter.data.TestData;
import org.fangsoft.testcenter.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataRepository {
    public static final Map<String, Customer> customerMap =
            new HashMap<String, Customer>();

    static {
        for (Customer customer : CustomerData.getCustomer()) {
            customerMap.put(customer.getUserId(), customer);
        }
    }

    public static final Map<Integer, Test> testMap = new HashMap<Integer, Test>();
    public static final Map<Integer, List<Question>> questionMap = new HashMap<Integer, List<Question>>();

    static {
        int questionId = 0;
        int choiceItemId = 0;
        for (int i = 0; i < TestData.allTest.length; i++) {
            String[][] data = TestData.allTest[i];
            Test test = TestData.newTest(data);
            int testId = i + 1;
            test.setId(testId);
            testMap.put(testId, test);
            TestData.loadQuestion(test, data);
            for (Question q : test.getQuestion()) {
                q.setId(questionId++);
                for (ChoiceItem item : q.getChoiceItem()) {
                    item.setId(choiceItemId++);
                }
            }
            questionMap.put(test.getId(), test.getQuestion());
            test.setQuestion(null);
        }
    }

    public static final Map<Integer, List<QuestionResult>> questionResultMap = new ConcurrentHashMap<Integer, List<QuestionResult>>();

    public static final Map<String, ArrayList<TestReservation>> testReservationMapForUserId = new ConcurrentHashMap<String, ArrayList<TestReservation>>();
    public static final Map<Integer, TestReservation> testReservationMapForPK = new ConcurrentHashMap<Integer, TestReservation>();

    public static final Map<Integer,TestResult> testResultMapForPK=new HashMap<Integer,TestResult>();
    public static final Map<String,List<TestResult>> testResultMapForUserId=new HashMap<String,List<TestResult>>();

}
