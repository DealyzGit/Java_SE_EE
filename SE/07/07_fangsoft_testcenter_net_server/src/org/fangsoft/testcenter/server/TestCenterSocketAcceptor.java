package org.fangsoft.testcenter.server;

import org.fangsoft.net.server.ServerTask;
import org.fangsoft.net.server.SocketAcceptor;
import org.fangsoft.testcenter.command.Command;
import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.controller.TestCenterController;

import java.net.Socket;

public class TestCenterSocketAcceptor extends SocketAcceptor<Command,Command> {
    public TestCenterSocketAcceptor(int port) {
        super(port);
    }
    protected ServerTask<Command,Command>
    generateServerTask(Socket socket) {
        return new TestCenterServerTask(socket,getTestCenterController());
    }
    private ServerTestCenterController controller;
    private TestCenterController getTestCenterController(){
        if(this.controller==null){
            this.controller=new ServerTestCenterController();
            this.controller.setDaoFactory(Configuration.getDaoFactory());
        }
        return this.controller;
    }
}
