package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.view.TestCenterView;

public class EndTestView extends ConsoleView implements TestCenterView {
    @Override
    protected void displayView() {
                 System.out.println("退出考试系统！，即将生成考试报告...");
    }
}
