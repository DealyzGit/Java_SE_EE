package org.fangsoft.testcenter.command;

import org.fangsoft.testcenter.model.TestResult;

public final class CommitTestCommand extends Command {
    private TestResult testResult;

    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public CommitTestCommand() {}
    public CommitTestCommand(TestResult testResult) {
        this.testResult = testResult;
    }
    public void execute() {
        if(this.getController()==null)return;
        this.getController().commitTest(this.getTestResult());
    }
}

