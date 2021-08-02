package org.fangsoft.testcenter.web.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.web.JSPUtil;

import java.io.IOException;
import java.util.List;


public class SimpleTagSupport  {
    public void doTag() throws JspException, IOException {

        try {
            List<Test> testList=JSPUtil.getTestCenterFacade().findAllTest();
            JspWriter writer=this.getJspContext().getOut();
            writer.println(" <table width=\"99%\" border=\"0\">");
            writer.println("              <tr>");
            writer.println("                <td bgcolor=\"#FFFFCC\">");
            writer.println("                  <strong>考试</strong> ");
            writer.println("                </td>");
            writer.println("              </tr>");
            writer.println("              <tr>");
            writer.println("                <td>");
            writer.println("                  <div align=\"left\">");
            writer.println("                    <ul>");
            if(testList!=null){
                for(Test t:testList){
                    String url=this.urlTestDetail.replace("{testId}",
                            String.valueOf(t.getId()));
                    writer.println("                      <li>");
                    writer.println(JSPUtil.makeLink(url, t.getName()));
                    writer.println("                      </li>");
                }
            }
            writer.println("                    </ul>");
            writer.println("                  </div>");
            writer.println("                </td>");
            writer.println("              </tr>");
            writer.println("            </table>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
