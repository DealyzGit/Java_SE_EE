<%@ page contentType="text/html; charset=UTF-8" %>

<%--
<%
    if (!JSPUtil.processNotLogin(request, response)) return;//未登录不能访问

    String userId = JSPUtil.getCustomer(request).getUserId();

    try {
        response.setContentType("text/html;charset=UTF-8");
        String test_Id = request.getParameter("testId");
        if (test_Id != null) {
            int testId = Integer.parseInt(test_Id);
            Date date = new Date();
            Test testById = JSPUtil.getTestCenterFacade().findTestByPK(testId);
            Customer customer = JSPUtil.getCustomer(request);
            TestReservation testReservation = new TestReservation();
            testReservation.setStatus(TestReservation.Status.ORDERED);
            testReservation.setOrderDate(date);
            testReservation.setTest(testById);
            testReservation.setCustomer(customer);
            JSPUtil.getTestCenterFacade().getDaoFactory().getTestReservationDao().save(testReservation);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }


    List<TestReservation> testReservationList = JSPUtil.getTestCenterFacade().findActiveTestReservationByUserId(userId);//考试预订记录
    List<TestResult> testResultList = JSPUtil.getTestCenterFacade().findTestResultByUserId(userId);//考试结果历史记录


    String urlLogout = URLConfig.urlLogout;//
    String urlStartTest = URLConfig.urlStartTest;
    String urlTestResult = URLConfig.urlTestResult;
    String urlPayment = URLConfig.urlPayment;

    DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, request.getLocale());//日期显示格式

%>

--%>

<%@ taglib uri="https://fangsoft.com" prefix="tc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${param.locale}"/>


<jsp:useBean
        id="testResListBean"
        class="org.fangsoft.testcenter.web.bean.TestReservationListBean"
        scope="page">
    <jsp:setProperty name="testResListBean"
                     property="userId"
                     value="${sessionScope.session_userId.userId}"/>
</jsp:useBean>

<jsp:useBean id="testResultListBean"
             class="org.fangsoft.testcenter.web.bean.TestResultListBean"
             scope="page">
    <jsp:setProperty name="testResultListBean" property="userId"
                     value="${sessionScope.session_userId.userId}"/>
</jsp:useBean>


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

    欢迎，<c:out value="${sessionScope.session_userId.userId }"/>
    <a href="logout">登出</a>
</p>

<tc:allTest urlTestDetail="testDetailView?testId={testId}"/>
<%--<jsp:include page="/WEB-INF/allTest.jsp"/>--%>

<table width="100%" border="0">
    <tr>
        <td colspan="3" bgcolor="#FFFFCC">
            <strong>您预定的考试</strong>
        </td>
    </tr>
    <c:choose>
        <c:when test="${! empty pageScope.testResListBean && pageScope.testResListBean.size > 0}">
            <c:forEach var="testRes" items="${pageScope.testResListBean.testReservationList}">
                <c:choose>
                    <c:when test="${testRes.status == 'PAYED'}">
                        <c:url var="urlTestReservation" value="startTest" scope="page">
                            <c:param name="testId" value="${testRes.test.id}"/>
                            <c:param name="testReservationId" value="${testRes.id}"/>
                        </c:url>
                        <c:set var="hrefText" value="开始考试" scope="page"/>
                    </c:when>
                    <c:when test="${testRes.status == 'FULFILLING'}">
                        <c:url var="urlTestReservation" value="startTest" scope="page">
                            <c:param name="testId" value="${testRes.test.id}"/>
                            <c:param name="testReservationId" value="${testRes.id}"/>
                        </c:url>
                        <c:set var="hrefText" value="继续考试" scope="page"/>
                    </c:when>
                    <c:when test="${testRes.status == 'ORDERED'}">
                        <c:url var="urlTestReservation" value="paymentView" scope="page">
                            <c:param name="testReservationId" value="${testRes.id}"/>
                        </c:url>
                        <c:set var="hrefText" value="支付" scope="page"/>
                    </c:when>
                </c:choose>
                <tr>
                    <td>
                        <c:out value="${testRes.test.name}"/>
                    </td>
                    <td>
                        <c:out value="${testRes.status.description}"/>
                    </td>
                    <td>
                        <a href="${pageScope.urlTestReservation}">
                            <c:out value="${pageScope.hrefText}"/></a>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
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
        </c:otherwise>
    </c:choose>
</table>


<table width="100%" border="0">
    <tr>
        <td colspan="3" valign="top" bgcolor="#FFFFCC">
            <strong>您的考试记录</strong>
        </td>
    </tr>
    <c:choose>
        <c:when
                test="${! empty pageScope.testResultListBean && pageScope.testResultListBean.size > 0}">
            <c:forEach var="testResult" items="${pageScope.testResultListBean.testResultList}">
                <c:url var="urlTestResult" value="testResultView" scope="page">
                    <c:param name="testResultId" value="${testResult.id}"/>
                </c:url>
                <tr>
                    <td width="33%">
                        <fmt:formatDate type="date" dateStyle="medium" value="${testResult.startTime}"/>
                    </td>
                    <td width="31%">
                        <a href="${pageScope.urlTestResult}">
                            <c:out value="${testResult.test.name}"/></a>
                    </td>
                    <td width="36%">
                        <c:out value="${testResult.result.value}"/>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
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
        </c:otherwise>
    </c:choose>
</table>

