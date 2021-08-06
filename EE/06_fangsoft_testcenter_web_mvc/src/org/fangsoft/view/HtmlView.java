package org.fangsoft.view;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HtmlView extends HttpServlet implements java.io.Serializable{
   private static final long serialVersionUID = 1L;
   public void output(PrintWriter writer){}

   public String makeLink(String url, String text) {
      return "<a href=\"" + url + "\">" + text + "</a>";
   }

   public void display(HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      response.setCharacterEncoding("utf-8");
      this.output(response.getWriter());
   }

}