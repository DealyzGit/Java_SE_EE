package org.fangsoft.testcenter.web.view;

import java.io.PrintWriter;public class TestResultView extends HtmlView{
   private static final long serialVersionUID = 1L;
   public void output(PrintWriter writer){
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
      writer.println("            tong");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("    <tr>");
      writer.println("        <td width=50%>");
      writer.println("            是否通过考试");
      writer.println("        </td>");
      writer.println("");
      writer.println("        <td width=49%>");
      writer.println("            Y/N");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("");
      writer.println("    <tr>");
      writer.println("        <td width=50%>");
      writer.println("            考试得分");
      writer.println("        </td>");
      writer.println("");
      writer.println("        <td width=49%>");
      writer.println("            3");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("    <tr>");
      writer.println("        <td width=50%>");
      writer.println("            考试开始时间");
      writer.println("        </td>");
      writer.println("");
      writer.println("        <td width=49%>");
      writer.println("");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("    <tr>");
      writer.println("        <td width=50%>");
      writer.println("            考试结束时间");
      writer.println("        </td>");
      writer.println("        <td width=49%>");
      writer.println("            tong");
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
      writer.println("<div align=\"center\">");
      writer.println("    <a href=\"testcenter.html\">");
      writer.println("        返回");
      writer.println("    </a>");
      writer.println("</div>");
      writer.println("");
      writer.println("</body>");
      writer.println("</html>");

   }
}