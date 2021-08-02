package org.fangsoft.testcenter.command;

import org.fangsoft.testcenter.model.Customer;

public final class LoginCommand extends Command {
    private String userId;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Customer customer;
    public LoginCommand() {}
    public LoginCommand(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
    public void execute() {
        if(this.getController()==null)
            return;
        this.customer=this.getController().login(this.userId, this.password);
    }
}
