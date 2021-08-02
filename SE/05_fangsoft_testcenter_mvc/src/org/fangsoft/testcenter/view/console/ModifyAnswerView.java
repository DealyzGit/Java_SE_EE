package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.model.*;
import org.fangsoft.testcenter.view.TestCenterView;
import org.fangsoft.testcenter.view.console.ConsoleView;
import org.fangsoft.util.Console_util;
import org.fangsoft.util.DataConverter;

import java.util.ArrayList;

import static org.fangsoft.util.Console_util.output;

// 修改已答试题视图
public class ModifyAnswerView extends ConsoleView implements TestCenterView {

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public int numQuestions() {
        return numQuestions;
    }

    public void numQuestions(int index) {
        this.numQuestions = numQuestions;
    }

    private int numQuestions;
    private Test test;
    private boolean isEnd = false;

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    private TestResult testResult;
    private String[] labels;

    public String getInputString() {
        return InputString;
    }

    public void setInputString(String inputString) {
        InputString = inputString;
    }

    private String InputString;
    private ArrayList<QuestionResult> questionResult;

    public  ModifyAnswerView(String s){
        this.InputString=s;
    }
    @Override
    protected void displayView() {
        numQuestions = DataConverter.str2Int(this.InputString);
        if(numQuestions<this.testResult.getTest().getNumQuestion()){
            try {
                questionResult = this.testResult.getQuestionResult();
                QuestionResult qr = questionResult.get(numQuestions - 1);
                Question q = qr.getQuestion();
                output(numQuestions + "." + q.getName());
                for (ChoiceItem c : q.getChoiceItem()
                ) {
                    output(c.getLabel() + "." + c.getName());
                }
                output("输入答案:");
                String newAnswer = Console_util.read();
                q.setAnswer(newAnswer);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }


    }
}
