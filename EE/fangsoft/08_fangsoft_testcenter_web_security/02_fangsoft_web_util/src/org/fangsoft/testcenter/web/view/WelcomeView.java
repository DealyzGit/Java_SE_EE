package org.fangsoft.testcenter.web.view;

import java.io.PrintWriter;public class WelcomeView extends HtmlView{
   private static final long serialVersionUID = 1L;
   public void output(PrintWriter writer){
      writer.println("<!DOCTYPE html>");
      writer.println("<html>");
      writer.println("<head>");
      writer.println("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
      writer.println("    <title>FangSoft</title>");
      writer.println("</head>");
      writer.println("<body>");
      writer.println("<div align=\"center\" style=\"background-color: rgb(255,255,204); width: 100%; height: 10%;\">");
      writer.println("    <span style=\"font-size: medium; color: BLUE; \">");
      writer.println("        <h1 >Fangsoft考试中心</h1>");
      writer.println("    </span>");
      writer.println("</div>");
      writer.println("");
      writer.println("<div style=\"background-color: rgb(255, 255, 255); width: 100%; height: 60%;\">");
      writer.println("    <h2 align=\"center\">需求</h2>");
      writer.println("        <span style=\"font-size: large; color: black; \">");
      writer.println("        <ol>");
      writer.println("            <li>fangsoft是一家专注于教育培训咨询的公司，为了开拓新业务，fangsoft公司决定开发一个基于Java的远程电子考试系统，");
      writer.println("                为客户提供多种考试服务。</li>");
      writer.println("            <li>客户可以注册、登录、更新个人的注册信息。");
      writer.println("                客户注册时应提供用户ID（唯一）、密码、姓名、身份证、电话、电子邮件、证书邮寄地址等信息。用户注册成功后，系统将发送一封注册成功的邮件给客户。");
      writer.println("                已注册客户登录后，客户可以在线预约考试时间，在线支付考试费用。系统将发送一封支付成功确认的邮件给客户，客户可以在预定的时间内参加考试。");
      writer.println("                如果客户在预定的时间不能参加考试，系统将保留客户参加考试的资格，但客户需要再次预约考试时间。客户在规定的时间内完成考试后，将能立即得到考试的结果，");
      writer.println("                考试的结果将说明答题的对错和成绩，并发送邮件至参加考试的客户，在考试规定的时间内若客户未能完成考试，系统将自动提交。");
      writer.println("                若因为不可避免的故障造成考试不得不中断，系统管理员可以中断考试，保留客户已用的时间以及已答的题目，");
      writer.println("                客户在一定的时间范围内可以继续参加考试或选择新的考试。");
      writer.println("            </li>");
      writer.println("            <li>考试采用的题目目前仅考虑支持选择题（包括多选和单选题），但以后可能考虑其他形式的题目，如问答题。");
      writer.println("                考试的题目分为难度1，2，3，4，5级，考试的题目现在可以按初级（难度1-2）40%、中级（难度3）40%、高级（难度5）20%的比例出题，但以后可以按设计的出题策略出题。");
      writer.println("            </li>");
      writer.println("            <li>客户在登录系统后可以浏览打印参加过考试的结果。");
      writer.println("            </li>");
      writer.println("            <li>考试试题提供者可以修改、增加、删除试题。一般地，一道试题使用过500次后应作相应的修改或暂停使用。</li>");
      writer.println("            <li>fangsoft公司聘请的相关考试领域专家可以对考试以及题目进行评审，评审的结果需要记录保留。");
      writer.println("            </li>");
      writer.println("            <li>fangsoft公司可以对考试的结果进行分析，如根据客户的地域、参加考试的人数以及知识点掌握的情况进行分析和统计，");
      writer.println("                分析的结果可以发布或供第三方机构使用。</li>");
      writer.println("        </ol>");
      writer.println("    </span>");
      writer.println("</div>");
      writer.println("");
      writer.println("<div style=\"background-color: rgb(255,255,204); width: 100%; height: 30%;\">");
      writer.println("    <div style=\"text-align:center;\">");
      writer.println("        <a href=\"login.html\">进入fangsoft考试中心原型</a>");
      writer.println("    </div>");
      writer.println("</div>");
      writer.println("");
      writer.println("</body>");
      writer.println("</html>");

   }
}