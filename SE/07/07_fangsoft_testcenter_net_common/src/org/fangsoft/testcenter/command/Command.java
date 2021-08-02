package org.fangsoft.testcenter.command;

import org.fangsoft.testcenter.controller.ITestCenterController;

public class Command implements ICommand {
    protected static final long serialVersionUID = 1L;
    protected transient ITestCenterController controller;
    public Command() {}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public ITestCenterController getController() {
        return controller;
    }

    public void setController(ITestCenterController controller) {
        this.controller = controller;
    }

    public Command(ITestCenterController controller) {
        this.controller = controller;
    }
    public void execute() {}
}
