package org.fangsoft.view;

import java.io.PrintWriter;

public class PaymentReportView extends HtmlView {
   private static final long serialVersionUID = 1L;


   public void output(PrintWriter writer){
      writer.println("<!DOCTYPE html>");
      writer.println("<html lang=\"en\">");
      writer.println("<head>");
      writer.println("    <meta charset=\"UTF-8\">");
      writer.println("    <title>FangSoft</title>");
      writer.println("</head>");
      writer.println("<body>");
      writer.println("");
      writer.println("<div align=\"center\" style=\"background-color: rgb(255,255,204); width: 100%; height: 10%;\">");
      writer.println("    <span style=\"font-size: medium; color: BLUE; \">");
      writer.println("        <h1>Fangsoft考试中心</h1>");
      writer.println("    </span>");
      writer.println("</div>");
      writer.println("");
      writer.println("<table width=\"99%\" border=\"0\">");
      writer.println("    <tr>");
      writer.println("        <td bgcolor=\"#FFFFCC\">");
      writer.println("            <strong>支付结果报告</strong>");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("    <tr>");
      writer.println("        支付成功");
      writer.println("    </tr>");
      writer.println("    <tr>");
      writer.println("        <a href=\"testCenterView\">");
      writer.println("            返回");
      writer.println("        </a>");
      writer.println("    </tr>");
      writer.println("");
      writer.println("</table>");
      writer.println("</body>");
      writer.println("</html>");

   }
}