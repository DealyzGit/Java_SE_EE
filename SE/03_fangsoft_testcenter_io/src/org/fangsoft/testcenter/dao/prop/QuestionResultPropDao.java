package org.fangsoft.testcenter.dao.prop;

import org.fangsoft.testcenter.dao.DaoIOConfig;
import org.fangsoft.testcenter.dao.QuestionResultDao;
import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.model.TestResult;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class QuestionResultPropDao implements QuestionResultDao {

    @Override
    public List<QuestionResult> findQuestionResultByCustomer(String userId) {
        return null;
    }

    @Override
    public void save(QuestionResult questionResult, TestResult testResult) {
        String path = DaoIOConfig.getQuestionResultFilePath(testResult);
        path=path.replace(":","_");
        new File(path).mkdirs();

        Properties ps = Property2Object.toProperties(questionResult);
        String fileName = DaoIOConfig.getFileName(questionResult);
        try {
            ps.store(new FileWriter(path + fileName), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static final QuestionResultPropDao testDao = new QuestionResultPropDao();

    public static final QuestionResultPropDao getInstance() {
        return testDao;
    }

    private QuestionResultPropDao() {
    }
}
