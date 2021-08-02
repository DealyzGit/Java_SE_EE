package org.fangsoft.testcenter.dao.array;

import org.fangsoft.testcenter.dao.QuestionDao;
import org.fangsoft.testcenter.data.TestData;
import org.fangsoft.testcenter.model.ChoiceItem;
import org.fangsoft.testcenter.model.Question;
import org.fangsoft.testcenter.model.Test;
import static org.fangsoft.testcenter.data.TestData.RIGHT_CHOICE;

public class QuestionArrayDao implements QuestionDao {
    private static final QuestionArrayDao tdao=new QuestionArrayDao();
    public static final QuestionArrayDao getInstance(){
        return tdao;
    }
    private QuestionArrayDao(){}
    @Override
    public Test loadQuestion(Test test) {
        String[][] data={};

        if ( test.getName().equals("java知识测试")){
            data=TestData.allTest[0];
        }
        if ( test.getName().equals("web")){
            data=TestData.allTest[1];
        }

        int qi =0 ;
        while(qi<data.length-1){
            String[] qds=data[qi+1];
            Question q=new Question();
            q.setName(qds[0]);
            for(int j=1;j<qds.length;j++){
                ChoiceItem ch=new ChoiceItem();
                String choiceText=qds[j];
                if (choiceText.startsWith(RIGHT_CHOICE)){
                    choiceText=choiceText.substring(1);
                    ch.setCorrect(true);
                }
                ch.setName(choiceText);
                q.addChoiceItem(ch);
            }
            q.setScore(1);
            test.addQuestion(q);
            qi++;
        }
        return test;
    }


}
