package org.fangsoft.testcenter.controller.console;

import org.fangsoft.testcenter.controller.TestCenterController;
import org.fangsoft.testcenter.controller.TestTimeoutGuard;
import org.fangsoft.testcenter.model.QuestionResult;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.testcenter.view.console.*;
import org.fangsoft.util.DataConverter;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class ConsoleTestCenterController extends TestCenterController implements Observer {
    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    private int index;

    public TestTimeoutGuard getTestTimeoutGuard() {
        return testTimeOutGuard;
    }

    public void setTestTimeoutGuard(TestTimeoutGuard testTimeoutGuard) {
        this.testTimeOutGuard = testTimeoutGuard;
    }

    private TestTimeoutGuard testTimeOutGuard;

    public String getInputPTV() {
        return InputPTV;
    }

    public void setInputPTV(String inputPTV) {
        InputPTV = inputPTV;
    }

    private String InputPTV;

    @Override
    public TestResult getTestResult() {
        return testResult;
    }

    @Override
    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    private TestResult testResult;


    @Override
    public void update(Observable observable, Object obj) {
        if (observable instanceof WelcomeView) {
            this.handle((WelcomeView) observable);
        } else if (observable instanceof LoginView) {
            this.handle((LoginView) observable);
        } else if (observable instanceof IsTakeTestView) {
            this.handle((IsTakeTestView) observable);
        } else if (observable instanceof SelectTestView) {
            this.handle((SelectTestView) observable);
        } else if (observable instanceof StartTestView) {
            this.handle((StartTestView) observable);
        } else if (observable instanceof PresentQuestionView) {
            this.handle((PresentQuestionView) observable);
        } else if (observable instanceof PromptEndTestView) {
            this.handle((PromptEndTestView) observable);
        } else if (observable instanceof ExitView) {
            this.handle((ExitView) observable);
        } else if (observable instanceof ModifyAnswerView) {
            this.handle((ModifyAnswerView) observable);
        } else if (observable instanceof EndTestView) {
            this.handle((EndTestView) observable);
        }

    }


    private void handle(WelcomeView view) {
        view.deleteObservers();
        LoginView loginView = new LoginView();
        loginView.addObserver(this);
        loginView.display();
    }

    private void handle(LoginView view) {
        String userId = view.getUserId();
        String password = view.getPassword();
        this.setCustomer(this.login(userId, password));
        if (this.getCustomer() != null) {
            IsTakeTestView tView = new IsTakeTestView();
            tView.addObserver(this);
            tView.display();
            view.deleteObservers();
        } else {
            if (view.getLoginCount() < Configuration.MAX_LOGIN) {
                view.setError(true);
                view.display();
            } else {
                view.deleteObservers();
                new ExitView("登录错误，系统退出").display();
            }
        }
    }

    private void handle(IsTakeTestView view) {
        view.deleteObservers();
        if (view.isTakeTest()) {
            SelectTestView stView = new SelectTestView(findAllTestNames());
            stView.addObserver(this);
            stView.display();
        } else {
            new ExitView("用户已取消考试").display();
        }
    }

    private void handle(SelectTestView view) {
        view.deleteObservers();
        StartTestView startView = new StartTestView(this.selectTest(view.getTestName()));
        startView.addObserver(this);
        startView.display();
    }

    private void handle(StartTestView view) {
        view.deleteObservers();
        Test test = view.getTest();
        this.setTestResult(this.startTest(test, this.getCustomer()));
        this.setIndex(1);
        this.testTimeOutGuard = new TestTimeoutGuard(this);
        QuestionResult qr = this.getTestResult().getQuestionResult(this.getIndex() - 1);

        PresentQuestionView pqView = new PresentQuestionView(qr, this.getIndex(), Configuration.CHOICE_LABEL);
        pqView.addObserver(this);
        pqView.display();
    }

    private void handle(PresentQuestionView view) {
        if (this.getIndex() < this.getTestResult().getTest().getNumQuestion()) {
            this.setIndex(this.getIndex() + 1);
            view.setSequence(this.getIndex());
            view.setQuestionResult(this.getTestResult().getQuestionResult(this.getIndex() - 1));
            view.display();
        } else {
            view.deleteObservers();
            PromptEndTestView promptEndTestView = new PromptEndTestView(this.getTestResult().getTest().getNumQuestion());
            promptEndTestView.addObserver(this);
            promptEndTestView.display();
            this.InputPTV = promptEndTestView.getInputString();
        }
    }

    private void handle(PromptEndTestView view) {
        if (view.isEnd()) {
            view.deleteObservers();
            ExitView exitView = new ExitView("退出修改答案！");
            exitView.addObserver(this);
            exitView.display();
        } else if (DataConverter.str2Int(this.InputPTV) < this.testResult.getTest().getNumQuestion()) {
            view.deleteObservers();
            ModifyAnswerView modifyAnswerView = new ModifyAnswerView(view.getInputString());
            modifyAnswerView.setTestResult(this.testResult);
            modifyAnswerView.addObserver(this);
            modifyAnswerView.display();
            System.exit(0);
        } else {
            view.display();
        }
    }

    private void handle(ModifyAnswerView view) {
        this.testResult = view.getTestResult();
        PromptEndTestView promptEndTestView = new PromptEndTestView(this.getTestResult().getTest().getNumQuestion());
        promptEndTestView.addObserver(this);
        promptEndTestView.display();
        this.InputPTV = promptEndTestView.getInputString();
    }

    private void handle(ExitView view) {
        view.deleteObservers();
        EndTestView endTestView = new EndTestView(this.testResult, isTestTimeout());
        long end = System.currentTimeMillis();
        this.testResult.setEndTime(new Date(end));
        endTestView.addObserver(this);
        endTestView.display();
    }

    private void handle(EndTestView view) {
        if (!this.isTestTimeout()) {
            this.testTimeOutGuard.setTestFinished(true);
        }
        view.deleteObservers();
        ReportTestResultView reportTestResultView = new ReportTestResultView();
        reportTestResultView.addObserver(this);
        this.testResult.commitTest();
        reportTestResultView.setTr(this.testResult);
        reportTestResultView.display();
    }


    public void handleTimeOut() {
        displayEndTestView(true);
    }

    private synchronized void displayEndTestView(boolean timeOut) {
        EndTestView endTestView = new EndTestView(this.getTestResult(), timeOut);
        endTestView.addObserver(this);
        endTestView.display();
    }

//    private void handle(ExitView view) {
//        System.exit(0);
//    }
}
