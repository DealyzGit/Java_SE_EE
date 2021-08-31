package org.fangsoft.view;

import java.io.PrintWriter;

public class PaymentView extends HtmlView{
   private static final long serialVersionUID = 1L;
   private String userID;

   public int getTestReservationId() {
      return testReservationId;
   }

   public void setTestReservationId(int testReservationId) {
      this.testReservationId = testReservationId;
   }

   private int testReservationId;

   private int money;

   public static long getSerialVersionUID() {
      return serialVersionUID;
   }

   public String getUserID() {
      return userID;
   }

   public void setUserID(String userID) {
      this.userID = userID;
   }

   public int getMoney() {
      return money;
   }

   public void setMoney(int money) {
      this.money = money;
   }

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
      writer.println("        <h1 >Fangsoft考试中心</h1>");
      writer.println("    </span>");
      writer.println("</div>");
      writer.println("");
      writer.println("<table width=\"99%\" border=\"0\">");
      writer.println("    <tr>");
      writer.println("        <td bgcolor=\"#FFFFCC\">");
      writer.println("            <strong>支付</strong>");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("");
      writer.println("");
      writer.println("    <tr>");
      writer.println("        <td>");
      writer.println("            <form>");
      writer.println("                <div align=\"center\">");
      writer.println("                    <label >金额：</label>");

//      writer.println("                    <input type=\"text\" name=\"username\" value=\" \" > ");
      writer.println("                    <input type=\"text\" name=\"username\" value=\" "+ this.getMoney()+" \" >");
      writer.println("                </div>");
      writer.println("                <div align=\"center\">");
      writer.println("                    <label >账号：</label>");

//      writer.println("                    <input type=\"text\" name=\"name\">");
      writer.println("                    <input type=\"text\" name=\"name\" value=\" "+ this.getUserID()+" \" >");
      writer.println("                </div>");
      writer.println("                <div align=\"center\">银行");
      writer.println("                    <input type=\"checkbox\" name=\"vehicle\" value=\"Bike\">邮储银行");
      writer.println("                    <input type=\"checkbox\" name=\"vehicle\" value=\"Bike\">邮惠万家银行<br>");
      writer.println("                </div>");
      writer.println("");
      writer.println("                <div align=\"center\">");
//      writer.println("                    <a href=\"pay\"            class=\"button\">支付</a>");
      String urlPage="pay?testReservationId={testReservationId}".replace("{testReservationId}",String.valueOf(this.getTestReservationId()));
      writer.println("                    <a href=\" "+urlPage+"\" class=\"button\">支付</a>");

      writer.println("                    <a href=\"testCenterView\" class=\"button\">放弃</a>");
      writer.println("                </div>");
      writer.println("");
      writer.println("                <style type=\"text/css\">");
      writer.println("                    .block {");
      writer.println("                        width: 400px;");
      writer.println("                        display: block;");
      writer.println("                        margin: 5px 0;");
      writer.println("                    }");
      writer.println("");
      writer.println("                    .center {");
      writer.println("                        text-align: center;");
      writer.println("                    }");
      writer.println("");
      writer.println("                    label {");
      writer.println("                        display: inline-block;");
      writer.println("                        width: 100px;");
      writer.println("                        text-align: right;");
      writer.println("                    }");
      writer.println("");
      writer.println("                    input, textarea {");
      writer.println("                        vertical-align: top;");
      writer.println("                    }");
      writer.println("                    .button {");
      writer.println("                        background-color: #4CAF50; /* Green */");
      writer.println("                        border: none;");
      writer.println("                        color: white;");
      writer.println("                        padding: 10px 30px;");
      writer.println("                        text-align: center;");
      writer.println("                        text-decoration: none;");
      writer.println("                        display: inline-block;");
      writer.println("                        font-size: 16px;");
      writer.println("                    }");
      writer.println("                </style>");
      writer.println("            </form>");
      writer.println("        </td>");
      writer.println("    </tr>");
      writer.println("</table>");
      writer.println("");
      writer.println("</body>");
      writer.println("</html>");

   }
}