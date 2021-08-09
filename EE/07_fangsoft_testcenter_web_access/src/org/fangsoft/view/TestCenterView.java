package org.fangsoft.view;

import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.model.TestReservation;
import org.fangsoft.testcenter.model.TestResult;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

public class TestCenterView extends HtmlView {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String urlLogout;
    private List<Test> testList;
    private List<TestReservation> testReservationList;
    private String urlStartTest;
    private String urlPayment;
    private List<TestResult> TestResultList;
    private String urlTestResult;
    private Locale locale;
    private String UrlTestDetail;

    public String getUrlTestDetail() {
        return UrlTestDetail;
    }

    public void setUrlTestDetail(String urlTestDetail) {
        UrlTestDetail = urlTestDetail;
    }

    public List<TestResult> getTestResultList() {
        return TestResultList;
    }

    public void setTestResultList(List<TestResult> testResultList) {
        TestResultList = testResultList;
    }

    public String getUrlTestResult() {
        return urlTestResult;
    }

    public void setUrlTestResult(String urlTestResult) {
        this.urlTestResult = urlTestResult;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }


    public String getUrlLogout() {
        return urlLogout;
    }

    public void setUrlLogout(String urlLogout) {
        this.urlLogout = urlLogout;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public List<TestReservation> getTestReservationList() {
        return testReservationList;
    }

    public void setTestReservationList(List<TestReservation> testReservationList) {
        this.testReservationList = testReservationList;
    }

    public String getUrlStartTest() {
        return urlStartTest;
    }

    public void setUrlStartTest(String urlStartTest) {
        this.urlStartTest = urlStartTest;
    }

    public String getUrlPayment() {
        return urlPayment;
    }

    public void setUrlPayment(String urlPayment) {
        this.urlPayment = urlPayment;
    }

    private void outputTestReservation(PrintWriter writer) {
        writer.println("<table width=\"100%\" border=\"0\">");
        writer.println("    <tr>");
        writer.println("      <td colspan=\"3\" bgcolor=\"#FFFFCC\">");
        writer.println("         <strong>您预定的考试</strong> ");
        writer.println("      </td>");
        writer.println("     </tr>");
        if (this.getTestReservationList() != null
                && this.getTestReservationList().size() > 0) {
            for (TestReservation testRes : this.getTestReservationList()) {
                if (testRes.getStatus() == TestReservation.Status.FULFILLED)
                    continue;
                String href = "";
                String hrefText = "";
                String testReservationId = String.valueOf(testRes.getId());
                String testId = String.valueOf(testRes.getTest().getId());
                if (testRes.getStatus() == TestReservation.Status.PAYED) {
                    href = this.getUrlStartTest().replace("{testId}", testId);
                    href = href.replace("{testReservationId}", testReservationId);
                    hrefText = "开始考试";
                } else if (testRes.getStatus() == TestReservation.Status.FULFILLING) {
                    href = this.getUrlStartTest().replace("{testId}", testId);
                    href = href.replace("{testReservationId}", testReservationId);
                    hrefText = "继续考试";
                } else if (testRes.getStatus() == TestReservation.Status.ORDERED) {
                    href = this.getUrlPayment().replace("{testReservationId}", testReservationId);
                    hrefText = "支付";
                }
                writer.println("              <tr>");
                writer.println("                <td width=\"33%\">");
                writer.println(testRes.getTest().getName());
                writer.println("                </td>");
                writer.println("                <td width=\"31%\">");
                writer.println(testRes.getStatus().getDescription());
                writer.println("                </td>");
                writer.println("                <td width=\"36%\">");
                writer.println(this.makeLink(href, hrefText));
                writer.println("                </td>");
                writer.println("              </tr>");
            }
        } else {
            writer.println("              <tr>");
            writer.println("                <td width=\"33%\">");
            writer.println("                  N/A");
            writer.println("                </td>");
            writer.println("                <td width=\"31%\">");
            writer.println("                  N/A");
            writer.println("                </td>");
            writer.println("                <td width=\"36%\">");
            writer.println("                  N/A");
            writer.println("                </td>");
            writer.println("              </tr>");
        }
        writer.println("            </table>");
    }

    private void outputTestResult(PrintWriter writer) {
        writer.println(" <table width=\"100%\" border=\"0\">");
        writer.println("  <tr>");
        writer.println("   <td colspan=\"3\" valign=\"top\" bgcolor=\"#FFFFCC\">");
        writer.println("       <strong>您的考试记录</strong> ");
        writer.println("   </td>");
        writer.println("  </tr>");
        if (this.getTestResultList() != null && this.getTestResultList().size() > 0) {
            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, this.getLocale());
            for (TestResult testResult : this.getTestResultList()) {
                String url = this.getUrlTestResult().replace("{testResultId}",String.valueOf(testResult.getId()));
                writer.println("              <tr>");
                writer.println("                <td width=\"33%\">");
                writer.println(dateFormat.format(testResult.getStartTime()));
                writer.println("                </td>");
                writer.println("                <td width=\"31%\">");
                writer.println(this.makeLink(url, testResult.getTest().getName()));
                writer.println("                </td>");
                writer.println("                <td width=\"36%\">");
                writer.println(testResult.getResult().getValue());
                writer.println("                </td>");
                writer.println("              </tr>");
            }
        } else {
            writer.println("              <tr>");
            writer.println("                <td width=\"33%\">");
            writer.println("                  N/A");
            writer.println("                </td>");
            writer.println("                <td width=\"31%\">");
            writer.println("                  N/A");
            writer.println("                </td>");
            writer.println("                <td width=\"36%\">");
            writer.println("                  N/A");
            writer.println("                </td>");
            writer.println("              </tr>");
        }
        writer.println("            </table>");
    }

    private void outputTestList(PrintWriter writer) {
        String  url=this.getUrlTestDetail();
        for (int t = 0; t < this.getTestList().size(); ++t) {
            writer.println("                    <li>");
            writer.println(" <a href=\"" + url.replace("{testId}", String.valueOf(t)) + "\">" + this.getTestList().get(t).getName() + "</a>");
            writer.println("                    </li>");
        }
    }

    public void output(PrintWriter writer) {
        writer.println("<meta charset=\"UTF-8\">");
        writer.println("<title>");
        writer.println("    FangSoft");
        writer.println("</title>");
        writer.println("<div align=\"center\" style=\"background-color: rgb(255,255,204); width: 100%; height: 5%;\">");
        writer.println("  <span style=\"font-size: medium; color: BLUE; \">");
        writer.println("    <h1>");
        writer.println("      Fangsoft考试中心");
        writer.println("    </h1>");
        writer.println("  </span>");
        writer.println("</div>");
        writer.println("<p>");
        writer.println("            欢迎， " + this.getUserId());
        writer.println(this.makeLink(this.getUrlLogout(), "登出"));
        writer.println("</p>");
        writer.println("");
        writer.println("<table width=\"99%\" border=\"0\">");
        writer.println("    <tr>");
        writer.println("        <td bgcolor=\"#FFFFCC\">");
        writer.println("            <strong>考试</strong>");
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("    <tr>");
        writer.println("        <td>");
        writer.println("            <div align=\"left\">");
        writer.println("                <ul>");

        outputTestList(writer);
        writer.println("                </ul>");
        writer.println("            </div>");
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("</table>");
        outputTestReservation(writer);
        outputTestResult(writer);
    }

}