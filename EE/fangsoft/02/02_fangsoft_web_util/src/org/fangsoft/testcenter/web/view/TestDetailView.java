package org.fangsoft.testcenter.web.view;

import java.io.PrintWriter;public class TestDetailView extends HtmlView{
   private static final long serialVersionUID = 1L;
   public void output(PrintWriter writer){
      writer.println("<!DOCTYPE html>");
      writer.println("<meta charset=\"UTF-8\">");
      writer.println("<html>");
      writer.println("<head>");
      writer.println("    <title>");
      writer.println("        FangSoft");
      writer.println("    </title>");
      writer.println("</head>");
      writer.println("<body>");
      writer.println("<div align=\"center\" style=\"background-color: rgb(255,255,204); width: 100%; height: 10%;\">");
      writer.println("  <span style=\"font-size: medium; color: BLUE; \">");
      writer.println("    <h1>");
      writer.println("      Fangsoft考试中心");
      writer.println("    </h1>");
      writer.println("  </span>");
      writer.println("</div>");
      writer.println("");
      writer.println("");
      writer.println("<p>");
      writer.println("    欢迎，tong");
      writer.println("    <a href=\"login.html\">");
      writer.println("        登出");
      writer.println("    </a>");
      writer.println("</p>");
      writer.println("");
      writer.println("<table width=\"30%\" border=\"0\" align=\"left\">");
      writer.println("    <tr>");
      writer.println("        <td bgcolor=\"#FFFFCC\">");
      writer.println("            <strong>考试</strong>");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("    <tr>");
      writer.println("        <td>");
      writer.println("            <div align=\"left\">");
      writer.println("                <ul>");
      writer.println("                    <li>");
      writer.println("                        <a href=\"testDetail.html\">java</a>");
      writer.println("                    </li>");
      writer.println("                </ul>");
      writer.println("            </div>");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("</table>");
      writer.println("");
      writer.println("<table width=\"69%\" border=\"0\" align=\"right\">");
      writer.println("    <tr>");
      writer.println("        <td bgcolor=\"#FFFFCC\">");
      writer.println("            <strong>考试详细信息</strong>");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("</table>");
      writer.println("");
      writer.println("<table width=\"69%\" border=\"0\" align=\"right\">");
      writer.println("    <tr>");
      writer.println("        <td width=25%>");
      writer.println("            考试名称：");
      writer.println("        </td>");
      writer.println("        <td width=\"44%\">");
      writer.println("            java");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("    <tr>");
      writer.println("        <td width=49%>");
      writer.println("            考试时间：");
      writer.println("        </td>");
      writer.println("        <td width=\"50%\">");
      writer.println("            10分钟");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("");
      writer.println("    <tr>");
      writer.println("        <td width=49%>");
      writer.println("            考试题数：");
      writer.println("        </td>");
      writer.println("        <td width=\"50%\">");
      writer.println("            3");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("");
      writer.println("    <tr>");
      writer.println("        <td width=49%>");
      writer.println("            考试总分：");
      writer.println("        </td>");
      writer.println("        <td width=\"50%\">");
      writer.println("            3");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("");
      writer.println("    <tr>");
      writer.println("        <td width=49%>");
      writer.println("            考试描述：");
      writer.println("        </td>");
      writer.println("        <td width=\"50%\">");
      writer.println("            java知识测试");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("    <tr>");
      writer.println("        <td align=\"center\" width=49%>");
      writer.println("        <a href=\"payment.html\">");
      writer.println("            预定考试");
      writer.println("        </a>");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("</table>");
      writer.println("");
      writer.println("</body>");
      writer.println("</html>");

   }
}