package org.fangsoft.view;

import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.model.TestResult;
import org.fangsoft.util.DataConverter;

import java.io.PrintWriter;

public class TestResultView extends HtmlView {
    private static final long serialVersionUID = 1L;
    private Test test;
    private TestResult testResult;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }


    public TestResult getTestResult() {
        return testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    public void outputResult(PrintWriter writer) {

        writer.println("<table width=\"99%\" border=\"0\">");
        for (int i=0;i<this.getTestResult().getTest().getNumQuestion();i++){
            writer.println("    <tr>");
            writer.println("        <td width=25%>");
            writer.println(i);
            writer.println("        </td>");
            writer.println("        <td width=25%>");
            writer.println(this.getTestResult().getQuestionResult(i).getAnswer());
            writer.println("        </td>");
            writer.println("");
            writer.println("        <td width=25%>");
            writer.println(this.getTestResult().getTest().getQuestion().get(i).getAnswer());
            writer.println("        </td>");
            writer.println("");
            writer.println("        <td width=24%>");
            writer.println(this.getTestResult().getQuestionResult(i).isResult()?"right":"wrong");
            writer.println("        </td>");
            writer.println("    </tr>");

        }
        writer.println("</table>");
    }

    public void output(PrintWriter writer) {

        writer.println("<!DOCTYPE html>");
        writer.println("<html lang=\"en\">");
        writer.println("<head>");
        writer.println("    <meta charset=\"UTF-8\">");
        writer.println("    <title>Title</title>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("");
        writer.println("<div align=\"center\" style=\"background-color: rgb(255,255,204); width: 100%; height: 10%;\">");
        writer.println("    <span style=\"font-size: medium; color: BLUE; \">");
        writer.println("        <h1>Fangsoft考试中心</h1>");
        writer.println("    </span>");
        writer.println("</div>");
        writer.println("<table width=\"99%\" border=\"0\">");
        writer.println("    <tr>");
        writer.println("        <td bgcolor=\"#FFFFCC\">");
        writer.println("            <strong>考试结果报告</strong>");
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("</table>");
        writer.println("");
        writer.println("<table width=\"99%\" border=\"0\">");
        writer.println("    <tr>");
        writer.println("        <td width=50%>");
        writer.println("            参考人");
        writer.println("        </td>");
        writer.println("");
        writer.println("        <td width=49%>");
//      writer.println("            tong");
        writer.println(this.getTestResult() == null ? "N/A" : this.getTestResult().getCustomer().getUserId());

        writer.println("");
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("    <tr>");
        writer.println("        <td width=50%>");
        writer.println("            是否通过考试");
        writer.println("        </td>");
        writer.println("");
        writer.println("        <td width=49%>");
//        writer.println("            Y/N");
        writer.println(this.getTestResult() == null ? "N/A" : this.getTestResult().getResult());
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("");
        writer.println("    <tr>");
        writer.println("        <td width=50%>");
        writer.println("            考试得分");
        writer.println("        </td>");
        writer.println("");
        writer.println("        <td width=49%>");
//        writer.println("            3");
        writer.println(this.getTestResult() == null ? "N/A" : this.getTestResult().getScore());
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("    <tr>");
        writer.println("        <td width=50%>");
        writer.println("            考试开始时间");
        writer.println("        </td>");
//        writer.println("");
        writer.println(this.getTestResult() == null ? "N/A" : this.getTestResult().getStartTime());

        writer.println("        <td width=49%>");
        writer.println("");
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("    <tr>");
        writer.println("        <td width=50%>");
        writer.println("            考试结束时间");
        writer.println("        </td>");
        writer.println("        <td width=49%>");
//        writer.println("            ");
        writer.println(this.getTestResult() == null ? "N/A" : this.getTestResult().getEndTime());

        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("</table>");
        writer.println("");
        writer.println("");

        writer.println("<table width=\"99%\" border=\"0\">");
        writer.println("    <tr>");
        writer.println("        <td bgcolor=\"#FFFFCC\">");
        writer.println("            <strong>详细考试结果</strong>");
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("</table>");

        writer.println("<table width=\"99%\" border=\"0\">");

        writer.println("    <tr>");
        writer.println("        <td width=25%>");
        writer.println("            题号");
        writer.println("        </td>");
        writer.println("        <td width=25%>");
        writer.println("            您的答案");
        writer.println("        </td>");
        writer.println("");
        writer.println("        <td width=25%>");
        writer.println("            参考正确答案");
        writer.println("        </td>");
        writer.println("");
        writer.println("        <td width=24%>");
        writer.println("            对错");
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("</table>");

        outputResult(writer);

        writer.println("<div align=\"center\">");
        writer.println("    <a href=\"testcenter.html\">");
        writer.println("        返回");
        writer.println("    </a>");
        writer.println("</div>");
        writer.println("");
        writer.println("");
        writer.println("</body>");
        writer.println("</html>");

    }
}