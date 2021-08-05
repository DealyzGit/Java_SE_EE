package org.fangsoft.testcenter.web.tag;

//import jakarta.servlet.jsp.JspException;
//import jakarta.servlet.jsp.JspWriter;
//import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.web.JSPUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class TestInfoTag extends SimpleTagSupport {
    private Test test ;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public void doTag() throws JspException, IOException {
        try {

//            Test test = JSPUtil.getTestCenterFacade().findTestByPK(this.testId);
            Test test =this.test;

            JspWriter writer = this.getJspContext().getOut();
            writer.println("<table width=\"69%\" border=\"0\" align=\"right\">");
            writer.println("  <tr>");
            writer.println("    <td bgcolor=\"#FFFFCC\">");
            writer.println("      <strong>考试详细信息</strong>");
            writer.println("    </td>");
            writer.println("  </tr>");
            writer.println("</table>");
            writer.println("");
            writer.println("<table width=\"69%\" border=\"0\" align=\"right\">");
            writer.println("  <tr>");
            writer.println("    <td width=25%>");
            writer.println("      考试名称：");
            writer.println("    </td>");
            writer.println("    <td width=\"44%\">");
            writer.println(test.getName());
            writer.println("    </td>");
            writer.println("  </tr>");
            writer.println("  <tr>");
            writer.println("    <td width=49%>");
            writer.println("      考试时间：");
            writer.println("    </td>");
            writer.println("    <td width=\"50%\">");
            writer.println(test.getTimeLimitMin());
            writer.println("    </td>");
            writer.println("  </tr>");
            writer.println("");
            writer.println("  <tr>");
            writer.println("    <td width=49%>");
            writer.println("      考试题数：");
            writer.println("    </td>");
            writer.println("    <td width=\"50%\">");
            writer.println(test.getNumQuestion());
            writer.println("    </td>");
            writer.println("  </tr>");
            writer.println("");
            writer.println("  <tr>");
            writer.println("    <td width=49%>");
            writer.println("      考试总分：");
            writer.println("    </td>");
            writer.println("    <td width=\"50%\">");
            writer.println(test.getScore());
            writer.println("    </td>");
            writer.println("  </tr>");
            writer.println("");
            writer.println("  <tr>");
            writer.println("    <td width=49%>");
            writer.println("      考试描述：");
            writer.println("    </td>");
            writer.println("    <td width=\"50%\">");
            writer.println(test.getDescription());
            writer.println("    </td>");
            writer.println("  </tr>");
            writer.println("");
            writer.println("</table>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


