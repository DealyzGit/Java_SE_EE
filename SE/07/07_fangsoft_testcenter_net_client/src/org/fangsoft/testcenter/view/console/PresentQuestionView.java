package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.model.ChoiceItem;
import org.fangsoft.testcenter.model.Question;
import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.view.TestCenterView;
import org.fangsoft.util.Console_util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.fangsoft.util.Console_util.output;

public class PresentQuestionView extends ConsoleView implements TestCenterView {
    public static final String[] CHOICE_LABEL = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
    private int sequence;
    private String[] labels;

    public int getSequence() {
        return Sequence;
    }

    public void setSequence(int sequence) {
        Sequence = sequence;
    }

    private int Sequence;

    public QuestionResult getQuestionResult() {
        return questionResult;
    }

    public void setQuestionResult(QuestionResult questionResults) {
        this.questionResult = questionResults;
    }

    private QuestionResult questionResult;

    public PresentQuestionView() {
    }

    public PresentQuestionView(QuestionResult qr, int sequence, String[] labels) {
        this.questionResult = qr;
        this.sequence = sequence;
        this.labels = labels;
    }

    public PresentQuestionView(QuestionResult result, int sequence) {
        this(result, sequence, null);
    }

    public void displayView() {
        Console_util.output("现在时间是：%1$tT%n", new Date());
        if (this.labels == null) this.labels = CHOICE_LABEL;
        this.questionResult.getQuestion().assignLabel(this.labels);
        String answer =prompt(this.sequence, this.questionResult.getQuestion());
        this.questionResult.setAnswer(answer);
    }

    public static String prompt(int pos, Question q) {
        Console_util.output("%1$s. %2$s%n", pos, q.getName());
        List<ChoiceItem> items = q.getChoiceItem();
        for (ChoiceItem item : items) {
            Console_util.output("%1$s. %2$s%n", item.getLabel(),item.getName());
        }
        Console_util.output("输入答案：");
        return Console_util.read();
    }
}
