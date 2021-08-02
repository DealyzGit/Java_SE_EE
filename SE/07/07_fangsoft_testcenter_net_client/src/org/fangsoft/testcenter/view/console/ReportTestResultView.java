package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.view.TestCenterView;

import static org.fangsoft.util.Console_util.output;

public class ReportTestResultView extends ConsoleView implements TestCenterView {
    public TestResult getTr() {
        return tr;
    }

    public void setTr(TestResult tr) {
        this.tr = tr;
    }

    private TestResult tr;

    @Override
    protected void displayView() {
        tr=this.tr;
        output("==========考试报告===========");
        long duration = (tr.getEndTime().getTime() - tr.getStartTime().getTime()) / (1000 * 60);
        output("你花了%1$s分钟考试%n", duration);
        output("%1$-6s%2$-6s%3$-6s%4$-6s%n", "题号", "你的答案", "正确答案", "对错");
        int count = 0;
        for (QuestionResult qr : tr.getQuestionResult()) {
            output("%1$-8s%2$-8s%3$-8s%4$-8s%n",++count,qr.getAnswer(),qr.getQuestion().getAnswer(),qr.isResult() ? "right" : "wrong");
        }
        output("你考试的得分是："+tr.getScore()+","+tr.getResult().getValue());
        output("██████╗ ██╗   ██╗███████╗██╗\n" +
                "██╔══██╗╚██╗ ██╔╝██╔════╝██║\n" +
                "██████╔╝ ╚████╔╝ █████╗  ██║\n" +
                "██╔══██╗  ╚██╔╝  ██╔══╝  ╚═╝\n" +
                "██████╔╝   ██║   ███████╗██╗\n" +
                "╚═════╝    ╚═╝   ╚══════╝╚═╝");
    }
}
