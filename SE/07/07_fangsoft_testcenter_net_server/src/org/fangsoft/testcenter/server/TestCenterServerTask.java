package org.fangsoft.testcenter.server;

import org.fangsoft.net.server.ServerTask;
import org.fangsoft.testcenter.command.Command;
import org.fangsoft.testcenter.controller.TestCenterController;

import java.net.Socket;

public class TestCenterServerTask extends ServerTask<Command,Command> {
    private TestCenterController controller;
    public TestCenterServerTask(Socket socket,
                                TestCenterController controller) {
        super(socket);
        this.controller=controller;
    }
    protected Command handle(Command request) {
        request.setController(this.controller);
        request.execute();
        request.setController(null);
        return request;
    }
}
