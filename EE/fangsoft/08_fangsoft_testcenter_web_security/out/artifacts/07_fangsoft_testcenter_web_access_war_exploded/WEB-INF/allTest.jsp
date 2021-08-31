<%--
  Created by IntelliJ IDEA.
  User: 24818
  Date: 2021/8/2
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="org.fangsoft.testcenter.model.Test,
				 org.fangsoft.testcenter.web.JSPUtil,
				 org.fangsoft.testcenter.web.URLConfig,
				 java.util.List"
%>

<%
    List<Test> testList=JSPUtil.getTestCenterFacade().findAllTest();
    String urlTestDetail=URLConfig.urlTestDetail;
%>

<table width="99%" border="0">
    <tr>
        <td bgcolor="#FFFFCC"><strong>考试</strong></td>
    </tr>
    <tr>
        <td>
            <div align="left">
                <ul>

                    <%
                        if(testList!=null){
                            for(Test test:testList){
                                String url=urlTestDetail.replace("{testId}",String.valueOf(test.getId()));
                    %>
                    <li>
                        <%=JSPUtil.makeLink(url,test.getName()) %>
                    </li>
                    <%
                            }
                        }
                    %>

                </ul>
            </div>
        </td>
    </tr>
</table>

