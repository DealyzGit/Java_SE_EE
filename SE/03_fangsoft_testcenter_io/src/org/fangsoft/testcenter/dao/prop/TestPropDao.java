package org.fangsoft.testcenter.dao.prop;

import org.fangsoft.testcenter.dao.DaoIOConfig;
import org.fangsoft.testcenter.dao.TestDao;
import org.fangsoft.testcenter.model.Question;
import org.fangsoft.testcenter.model.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class TestPropDao implements TestDao {
    @Override
    public Test findTestByName(String testName) {
        String path= DaoIOConfig.getTestFilePath();
        File[] files=new File(path).listFiles();
        if(files==null ||files.length==0)return null;
        for(File f:files){
            String fileName=f.getName();
            if(f.isFile() &&fileName.startsWith(testName)){
                Properties ps=new Properties();
                try {
                    ps.load(new FileReader(f));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return Property2Object.toTest(ps);
            }
        }
        return null;
    }
    @Override
    public List<Test> findAllTest() {
        String testPath = DaoIOConfig.getTestFilePath();
        File[] files = new File(testPath).listFiles();
        if(files==null||files.length==0)return new ArrayList<Test>(0);
        ArrayList<Test> tests = new ArrayList<Test>(files.length);
        Properties ps = new Properties();
        try {
            for (File f : files) {
                if (f.isDirectory())continue;
                ps.load(new FileReader(f));
                tests.add(Property2Object.toTest(ps));
                ps.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tests.trimToSize();
        return tests;
    }
    @Override
    public void save(Test test) {
        Properties ps=Property2Object.toProperties(test);
        String path=DaoIOConfig.getTestFilePath();
        String fileName=DaoIOConfig.getFileName(test);
        new File(path).mkdirs();
        try {
            ps.store(new FileWriter(path+fileName), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Question> qList= Arrays.asList(test.getQuestion());
        if(qList!=null){
            for(Question q:qList){
                QuestionPropDao.getInstance().addQuestionToTest(test,q);
            }
        }
    }


    private static final TestPropDao testDao=new TestPropDao();
    public static final TestPropDao getInstance(){
        return testDao;
    }
    private TestPropDao(){}

}
