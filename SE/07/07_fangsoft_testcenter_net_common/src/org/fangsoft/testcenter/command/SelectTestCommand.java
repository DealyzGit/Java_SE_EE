package org.fangsoft.testcenter.command;

import org.fangsoft.testcenter.model.Test;

public final class SelectTestCommand extends Command {
    private String testName;
    private Test test;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public SelectTestCommand() {}
    public SelectTestCommand(String testName) {
        this.testName = testName;
    }
    public void execute() {
        if(this.getController()==null)return;
        this.setTest(this.getController().selectTest(this.getTestName()));
    }
}
