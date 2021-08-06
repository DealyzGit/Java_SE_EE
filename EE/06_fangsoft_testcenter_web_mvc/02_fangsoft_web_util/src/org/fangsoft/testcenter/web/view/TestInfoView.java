package org.fangsoft.testcenter.web.view;

import java.io.PrintWriter;public class TestInfoView extends HtmlView{
   private static final long serialVersionUID = 1L;
   public void output(PrintWriter writer){
      writer.println("<!DOCTYPE html>");
      writer.println("<html lang=\"en\">");
      writer.println("<head>");
      writer.println("    <meta charset=\"UTF-8\">");
      writer.println("    <title>Title</title>");
      writer.println("</head>");
      writer.println("<body>");

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
      writer.println("      <%=test.getName()%>");
      writer.println("    </td>");
      writer.println("  </tr>");
      writer.println("  <tr>");
      writer.println("    <td width=49%>");
      writer.println("      考试时间：");
      writer.println("    </td>");
      writer.println("    <td width=\"50%\">");
      writer.println("      <%=test.getTimeLimitMin()%>");
      writer.println("    </td>");
      writer.println("  </tr>");
      writer.println("");
      writer.println("  <tr>");
      writer.println("    <td width=49%>");
      writer.println("      考试题数：");
      writer.println("    </td>");
      writer.println("    <td width=\"50%\">");
      writer.println("      <%=test.getNumQuestion()%>");
      writer.println("    </td>");
      writer.println("  </tr>");
      writer.println("");
      writer.println("  <tr>");
      writer.println("    <td width=49%>");
      writer.println("      考试总分：");
      writer.println("    </td>");
      writer.println("    <td width=\"50%\">");
      writer.println("      <%=test.getScore()%>");
      writer.println("    </td>");
      writer.println("  </tr>");
      writer.println("");
      writer.println("  <tr>");
      writer.println("    <td width=49%>");
      writer.println("      考试描述：");
      writer.println("    </td>");
      writer.println("    <td width=\"50%\">");
      writer.println("      <%=test.getDescription()%>");
      writer.println("    </td>");
      writer.println("  </tr>");
      writer.println("");
      writer.println("</table>");

      writer.println("</body>");
      writer.println("</html>");

   }
}