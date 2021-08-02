package org.fangsoft.testcenter.dao.array;

import org.fangsoft.testcenter.dao.TestDao;
import org.fangsoft.testcenter.data.TestData;
import org.fangsoft.testcenter.model.Test;

import java.util.ArrayList;
import java.util.List;


public class TestArrayDao implements TestDao {
    public Test findTestByName(String testName) {
        for(String[][] data:TestData.allTest){
            if(data[0][0].equals(testName)){
                return TestData.newTest(data);
            }
        }
        return null;
    }
    public List<Test> findAllTest() {
        ArrayList<Test> tests=new ArrayList<Test>(TestData.allTest.length);
        for(String[][] data:TestData.allTest){
            tests.add(TestData.newTest(data));
        }
        tests.trimToSize();
        return tests;
    }
    private static final TestDao tdao=new TestArrayDao();
    public static final TestDao getInstance(){
        return tdao;
    }
    private TestArrayDao(){}

}
