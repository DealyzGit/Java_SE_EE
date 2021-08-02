package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.view.TestCenterView;
import org.fangsoft.util.Console_util;

import java.util.List;

import static org.fangsoft.util.Console_util.output;


public class SelectTestView extends ConsoleView implements TestCenterView {
    private String testName;
    private List<String> testNames;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public SelectTestView(List<String> testNames) {
        this.testNames = testNames;
    }

    public void displayView() {
        output("fangsoft考试中心提供的所有考试：");
        int count = 0;
        for (String name : this.testNames) {
            output((++count) + ". " + name + "考试" + ", 输入：" + name);
        }
        while (true) {
            String inputTestName = Console_util.prompt("请选择考试名称：");
            for (String name : testNames) {
                if (name.equals(inputTestName)) {
                    this.setTestName(name);
                    return;
                }
            }
        }
    }
}

