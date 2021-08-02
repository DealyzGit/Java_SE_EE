package org.fangsoft.testcenter.view.console;

import org.fangsoft.testcenter.config.Configuration;
import org.fangsoft.testcenter.view.TestCenterView;

import static org.fangsoft.util.Console_util.output;
import static org.fangsoft.util.Console_util.prompt;

public class LoginView extends ConsoleView implements TestCenterView {
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    private String userId;
    private String password;
    private boolean error = false;
    private int loginCount = 0;

    public LoginView() {
    }

    public void displayView() {
        if (!error) {
            output("进入fangsoft考试中心前请先登录输入完成后按enter确认");
        } else {
            output("用户名或密码错误不能登录,请重新登录。" +
                    "注意：登录" + Configuration.MAX_LOGIN + "次不成功，" + "系统将退出，这是" + (++this.loginCount) + "次");
        }
        this.userId = prompt("输入用户名称：");
        this.password = prompt("输入用户密码：");
    }


}
