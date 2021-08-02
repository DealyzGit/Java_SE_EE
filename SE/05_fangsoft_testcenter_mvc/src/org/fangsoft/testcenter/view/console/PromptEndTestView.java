package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.model.*;
import org.fangsoft.testcenter.view.TestCenterView;
import org.fangsoft.util.Console_util;
import org.fangsoft.util.DataConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.fangsoft.util.Console_util.output;
import static org.fangsoft.util.Console_util.prompt;

// 提示是否结束考试视图
public class PromptEndTestView extends ConsoleView implements TestCenterView {

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

    private String[] labels;

    private ArrayList<QuestionResult> questionResult;

    public String getInputString() {
        return InputString;
    }

    public void setInputString(String inputString) {
        InputString = inputString;
    }

    private  String InputString;

    public PromptEndTestView() {
    }

    public PromptEndTestView(QuestionResult questionResult, int index, String[] s) {
        this.questionResult.add(index, questionResult);
        this.numQuestions = index;
        this.labels = s;
    }

    public PromptEndTestView(int index) {
        this.numQuestions = index;
    }

    @Override
    protected void displayView() {
        output("结束考试还是修改答案？");
        output("1.结束考试输入:end");
        output("2.修改试题答案，输入试题序号(1-3)");
        this.InputString = Console_util.read();
        this.InputString.toLowerCase();
        if (this.InputString.equals("end")) {
            this.isEnd = true;
        }
    }
}
