package org.fangsoft.testcenter.view.console;

import static org.fangsoft.util.Console_util.promptYesNo;

public class IsTakeTestView extends ConsoleView {
    private boolean isTakeTest;

    public boolean isTakeTest() {
        return isTakeTest;
    }

    protected void displayView() {
        this.isTakeTest = promptYesNo("确认参加考试吗？", "y", "是：y", "否,退出：n");
    }
}
