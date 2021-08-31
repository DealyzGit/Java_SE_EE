package org.fangsoft.testcenter.dao.memory;

import org.fangsoft.testcenter.dao.TestDao;
import org.fangsoft.testcenter.model.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMemDao implements TestDao {
    private Test clone(Test test){
        Test t=new Test();
        t.setId(test.getId());
        t.setName(test.getName());
        t.setDescription(test.getDescription());
        t.setNumQuestion(test.getNumQuestion());
        t.setScore(test.getScore());
        t.setTimeLimitMin(test.getTimeLimitMin());
        return t;
    }

    public Test findTestByPK(int id) {
        Test test= DataRepository.testMap.get(id);
        if(test!=null){
            return this.clone(test);
        }
        return null;
    }

    @Override
    public Test findTestByName(String testName) {
        return null;
    }
    @Override
    public List<Test> findAllTest() {
        ArrayList<Test> allTest=new ArrayList<Test>();
        for(Test test:DataRepository.testMap.values()){
            allTest.add(this.clone(test));
        }
        allTest.trimToSize();
        return allTest;
    }

    @Override
    public void save(Test test) {

    }

    @Override
    public List<String> findAllTestNames() {
        return null;
    }


}
