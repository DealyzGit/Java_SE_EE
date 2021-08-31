package org.fangsoft.view;

import org.fangsoft.testcenter.model.ChoiceItem;
import org.fangsoft.testcenter.model.Question;
import org.fangsoft.testcenter.model.Test;

import java.io.PrintWriter;
import java.util.List;

public class StartTestView extends HtmlView {
    private static final long serialVersionUID = 1L;

    private Test test;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUrlCommitTestAction() {
        return urlCommitTestAction;
    }

    public void setUrlCommitTestAction(String urlCommitTestAction) {
        this.urlCommitTestAction = urlCommitTestAction;
    }

    private String urlCommitTestAction;

    private void outputQuestion(PrintWriter writer) {
        writer.println("      <form action=\"" + this.getUrlCommitTestAction() + "\"method=\"POST\" name=\"takeTestForm\" id=\"takeTestForm\">");
        writer.println("        <table width=\"100%\" border=\"0\">");
        writer.println("          <tr>");
        writer.println("            <td colspan=\"2\" bgcolor=\"#FFFFCC\">");
        writer.println("              <div align=\"left\">");
        writer.println("                <strong>考试试题</strong>");
        writer.println("              </div>");
        writer.println("            </td>");
        writer.println("          </tr>");
        if (this.getTest() != null && this.getTest().getQuestion() != null) {
            List<Question> questionList = this.getTest().getQuestion();
            for (int i = 0; i < questionList.size(); i++) {
                Question q = questionList.get(i);
                writer.println("          <tr>");
                writer.println("            <td width=\"6%\">");
                writer.println((i + 1));
                writer.println("            </td>");
                writer.println("            <td width=\"94%\">");
                writer.println(q.getName());
                writer.println("            </td>");
                writer.println("          </tr>");
                writer.println("          <tr>");
                writer.println("            <td>");
                writer.println("            </td>");
                writer.println("            <td>");
                writer.println("              <ol>");
                List<ChoiceItem> items = q.getChoiceItem();
                if (items != null) {
                    for (ChoiceItem item : items) {
                        String id = q.getId() + "_" + item.getId();
                        writer.println("                <li>");
                        writer.println("<input type=\"checkbox\" name=\"" + id + "\" id=\"" + id + "\" />");
                        writer.println(item.getName());
                        writer.println("                </li>");
                    }
                    writer.println("              </ol>");
                    writer.println("            </td>");
                    writer.println("          </tr>");
                }
                writer.println("          <tr>");
                writer.println("            <td>");
                writer.println("            </td>");
                writer.println("            <td>");
                writer.println("            </td>");
                writer.println("          </tr>");
            }
            writer.println("          <tr>");
            writer.println("            <td colspan=\"2\">");
            writer.println("              <div align=\"center\">");
            writer.println("<input type=\"submit\" name=\"button\" id=\"button\" value =\"完成考试\" />");
            writer.println("              </div>");
            writer.println("            </td>");
            writer.println("          </tr>");
        }
        writer.println("        </table>");
        writer.println("      </form>");
    }

    public void output(PrintWriter writer) {
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
        writer.println("<table width=\"100%\" border=\"0\">");
        writer.println("    <tr>");
        writer.println("        <td colspan=\"3\" valign=\"top\" bgcolor=\"#FFFFCC\">");
        writer.println("            <strong>开始考试</strong>");
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("    <tr>");
        writer.println("        <td width=\"49%\">");
        writer.println("            考试名称：");
        writer.println("        </td>");
        writer.println("        <td width=\"50%\">");

        writer.println(this.getTest() == null ? "N/A" : this.getTest().getName());
//      writer.println("            JAVA");

        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("");
        writer.println("    <tr>");
        writer.println("        <td width=\"49%\">");
        writer.println("            考试时间：");
        writer.println("        </td>");
        writer.println("        <td width=\"50%\">");
//      writer.println("            10分钟");
        writer.println(this.getTest() == null ? "N/A" : this.getTest().getTimeLimitMin() + "（分钟）");

        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("");
        writer.println("    <tr>");
        writer.println("        <td width=\"49%\">");
        writer.println("            考试题数：");
        writer.println("        </td>");
        writer.println("        <td width=\"50%\">");
//      writer.println("            3");
        writer.println(this.getTest() == null ? "N/A" : this.getTest().getNumQuestion());

        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("");
        writer.println("    <tr>");
        writer.println("        <td width=\"49%\">");
        writer.println("            考试总分：");
        writer.println("        </td>");
        writer.println("        <td width=\"50%\">");
//      writer.println("            3");
        writer.println(this.getTest() == null ? "N/A" : this.getTest().getScore());

        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("");
        writer.println("    <tr>");
        writer.println("        <td width=\"49%\">");
        writer.println("            参考人：");
        writer.println("        </td>");
        writer.println("        <td width=\"50%\">");
//      writer.println("            tong");
        writer.println(this.getTest() == null ? "N/A" : this.getUserId());

        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("</table>");
        writer.println("<table width=\"100%\" border=\"0\">");
        writer.println("    <tr>");
        writer.println("        <td colspan=\"3\" valign=\"top\" bgcolor=\"#FFFFCC\">");
        writer.println("            <strong>开始考试</strong>");
        writer.println("        </td>");
        writer.println("    </tr>");
        writer.println("");
        writer.println("</table>");
        writer.println("");
        writer.println("</body>");
        writer.println("</html>");
        writer.println("");

        outputQuestion(writer);


    }
}