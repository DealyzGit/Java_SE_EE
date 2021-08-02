package org.fangsoft.testcenter.controller;

import org.fangsoft.net.client.NetClient;
import org.fangsoft.testcenter.command.*;
import org.fangsoft.testcenter.model.Customer;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.model.TestResult;

import java.util.List;

public class NetTestCenterController extends  TestCenterController {
    private NetClient<ICommand, ICommand> netClient;
    public NetTestCenterController(){}
    public NetTestCenterController(
            NetClient<ICommand,ICommand> netClient){
        this.netClient=netClient;
    }

    public NetClient<ICommand, ICommand> getNetClient() {
        return netClient;
    }

    public void setNetClient(NetClient<ICommand, ICommand> netClient) {
        this.netClient = netClient;
    }

    public Customer login(String userId, String password) {
        LoginCommand request=new LoginCommand(userId,password);
        LoginCommand response= (LoginCommand)getNetClient().synProcess(request);
        this.setCustomer(response.getCustomer());
        return response.getCustomer();
    }

    public List<String> findAllTestNames() {
        DisplayAllTestNamesCommand request=
                new DisplayAllTestNamesCommand();
        DisplayAllTestNamesCommand response=
                (DisplayAllTestNamesCommand)getNetClient().synProcess(request);
        return response.getTestNames();
    }
    public Test selectTest(String testName) {
        SelectTestCommand request=new SelectTestCommand(testName);
        SelectTestCommand response=
                (SelectTestCommand)getNetClient().synProcess(request);
        return response.getTest();
    }

    public TestResult startTest(Test test, Customer customer){
        StartTestCommand request=new StartTestCommand(test,customer);
        StartTestCommand response=
                (StartTestCommand)getNetClient().synProcess(request);
        TestResult testResult=response.getTestResult();
        this.setTestDeadTime(
                testResult.getStartTime().getTime()+test.getTimeLimitMin()*1000*60);
        return testResult;
    }

    public void commitTest(TestResult testResult) {
        CommitTestCommand request=new CommitTestCommand(testResult);
        CommitTestCommand response=
                (CommitTestCommand)getNetClient().synProcess(request);
        this.setTestResult(response.getTestResult());
    }

}
