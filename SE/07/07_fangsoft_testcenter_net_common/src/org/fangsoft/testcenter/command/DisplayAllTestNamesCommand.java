package org.fangsoft.testcenter.command;

import java.util.List;

public final class DisplayAllTestNamesCommand extends Command{
    private List<String> testNames;
    public DisplayAllTestNamesCommand() {}
    private static final long serialVersionUID = 1L;
    public List<String> getTestNames() {
        return testNames;
    }
    public void execute() {
        if(this.getController()==null)return;
        this.testNames=this.getController().findAllTestNames();
    }
}
