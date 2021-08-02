package org.fangsoft.testcenter.dao.memory;

import org.fangsoft.testcenter.dao.QuestionDao;
import org.fangsoft.testcenter.model.Question;
import org.fangsoft.testcenter.model.Test;

import java.util.ArrayList;
import java.util.List;

public class QuestionMemDao implements QuestionDao {
    @Override
    public void addQuestionToTest(Test test, Question q) {

    }

    public boolean loadQuestion(Test test) {
        List<Question> questionList=DataRepository.questionMap.get(test.getId());
        if(questionList==null)return false;
        java.util.Collections.shuffle(questionList);
        test.setQuestion((questionList));
        return true;
    }

}
