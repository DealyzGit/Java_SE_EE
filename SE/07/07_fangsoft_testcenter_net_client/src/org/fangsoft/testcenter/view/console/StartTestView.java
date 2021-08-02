package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.model.Test;

import static org.fangsoft.util.Console_util.output;

public final class StartTestView extends ConsoleView  {
    private Test test;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public StartTestView(Test test) {
        this.setTest(test);
    }

    public void displayView() {
        output("开始考试");
        output("考试名称：%1$5s%n" +
                        "考试简述：%2$5s%n" +
                        "考试问题：%3$5s%n" +
                        "考试时间：%4$5s分钟%n",
                getTest().getName(),
                getTest().getDescription(),
                getTest().getNumQuestion(),
                getTest().getTimeLimitMin());
        long start = System.currentTimeMillis();
        output("注意你有%1$s分钟答题，现在时间是" + "%2$tT%n", getTest().getTimeLimitMin(), start);
    }
}
