package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.view.TestCenterView;

import static org.fangsoft.util.Console_util.output;

// 提示退出系统视图
public class ExitView extends ConsoleView implements TestCenterView {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ExitView(String s){
        this.message=s;
    }
    private String message;
    @Override
    protected void displayView() {
        output(this.message);

    }

}
