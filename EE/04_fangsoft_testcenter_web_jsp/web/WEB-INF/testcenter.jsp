<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import=" org.fangsoft.testcenter.model.TestReservation,
                 org.fangsoft.testcenter.model.TestResult,
                 org.fangsoft.testcenter.web.JSPUtil,
                 org.fangsoft.testcenter.web.URLConfig,
                 java.text.DateFormat,
                 java.util.List"
%>

<%
    if (!JSPUtil.processNotLogin(request, response)) return;//未登录不能访问
    String userId = JSPUtil.getCustomer(request).getUserId();
    List<TestReservation> testReservationList = JSPUtil.getTestCenterFacade().findActiveTestReservationByUserId(userId);//考试预订记录
    List<TestResult> testResultList = JSPUtil.getTestCenterFacade().findTestResultByUserId(userId);//考试结果历史记录
    String urlLogout = URLConfig.urlLogout;//
    String urlStartTest = URLConfig.urlStartTest;
    String urlTestResult = URLConfig.urlTestResult;
    String urlPayment = URLConfig.urlPayment;
    DateFormat dateFormat = DateFormat.getDateInstance
            (DateFormat.MEDIUM, request.getLocale());//日期显示格式
%>


<meta charset="UTF-8">
<title>
    FangSoft
</title>
<div align="center" style="background-color: rgb(255,255,204); width: 100%; height: 5%;">
  <span style="font-size: medium; color: BLUE; ">
    <h1>
      Fangsoft考试中心
    </h1>
  </span>
</div>
<p>
    <%--    欢迎，tong--%>
    欢迎，<%=userId %>
    <%--    <a href="login.html">--%>
    <%--        登出--%>
    <%--    </a>--%>

    <%=JSPUtil.makeLink(urlLogout, "登出") %>

</p>


<jsp:include page="allTest"/>

<table width="100%" border="0">
    <tr>
        <td colspan="3" bgcolor="#FFFFCC">
            <strong>您预定的考试</strong>
        </td>
    </tr>
    <%
        if (testReservationList != null && testReservationList.size() > 0) {
            for (TestReservation testReservation : testReservationList) {
                if (testReservation.getStatus() ==TestReservation.Status.FULFILLED) continue;
                String href = "";
                String hrefText = "";
                String testReservationId =
                        String.valueOf(testReservation.getId());
                String testId =
                        String.valueOf(testReservation.getTest().getId());
                if (testReservation.getStatus() ==
                        TestReservation.Status.PAYED) {
                    href = urlStartTest.replace("{testId}", testId);
                    href = href.replace("{testReservationId}",
                            testReservationId);
                    hrefText = "开始考试";
                } else if (testReservation.getStatus() ==
                        TestReservation.Status.FULFILLING) {
                    href = urlStartTest.replace("{testId}", testId);
                    href = href.replace("{testReservationId}",
                            testReservationId);
                    hrefText = "继续考试";
                } else if (testReservation.getStatus() ==
                        TestReservation.Status.ORDERED) {
                    href = urlPayment.replace("{testReservationId}",
                            testReservationId);
                    hrefText = "支付";
                }
    %>
    <tr>
        <td>
            <%=testReservation.getTest().getName() %>
        </td>
        <td>
            <%=testReservation.getStatus().getDescription() %>
        </td>
        <td>
            <%=JSPUtil.makeLink(href, hrefText) %>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td>
            N/A
        </td>
        <td>
            N/A
        </td>
        <td>
            N/A
        </td>
    </tr>
    <%
        }
    %>
</table>

<table width="100%" border="0">
    <tr>
        <td colspan="3" valign="top" bgcolor="#FFFFCC">
            <strong>您的考试记录</strong>
        </td>
    </tr>
    <%
        if (testResultList != null && testResultList.size() > 0) {
            for (TestResult testResult : testResultList) {
                String href = urlTestResult.replace("{testResultId}",
                        String.valueOf(testResult.getId()));
    %>
    <tr>
        <td width="33%">
            <%=dateFormat.format(testResult.getStartTime()) %>
        </td>
        <td width="31%">
            <%=JSPUtil.makeLink(href,
                    testResult.getTest().getName()) %>
        </td>
        <td width="36%">
            <%=testResult.getResult().getValue() %>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td width="33%">
            N/A
        </td>
        <td width="31%">
            N/A
        </td>
        <td width="36%">
            N/A
        </td>
    </tr>
    <%
        }
    %>
</table>
