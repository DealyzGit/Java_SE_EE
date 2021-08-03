package org.fangsoft.testcenter.web;

public class URLConfig {
    public static final String urlLoginView = "loginView";
    public static final String urlLoginAction = "login";
    public static final String urlLogout = "logout";
    public static final String urlTestCenterView = "testCenterView";

    public static final String urlTestDetail = "testDetailView?testId={testId}";
    public static final String urlTestResult = "testResultView?testResultId={testResultId}";
    public static final String urlStartTest = "startTest?testId={testId}&testReservationId={testReservationId}";
    public static final String urlPayAction = "pay";
    public static final String urlPayment = "paymentView?testReservationId={testReservationId}";
    public static final String urlCommitTestAction = "commitTest";
    public static final String urlTestReservation = "reserveTest?testId={testId}";
    public static final String urlStartTestView="";
}
