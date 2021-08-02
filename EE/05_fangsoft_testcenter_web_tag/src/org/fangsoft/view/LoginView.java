package org.fangsoft.view;

import jakarta.servlet.annotation.WebServlet;

import java.io.PrintWriter;

public class LoginView extends HtmlView {
   private static final long serialVersionUID = 1L;

//      userId、loginAction和errorMsg
   private String userId;
   private String loginAction;
   private String errorMsg;

   public static long getSerialVersionUID() {
      return serialVersionUID;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getLoginAction() {
      return loginAction;
   }

   public void setLoginAction(String loginAction) {
      this.loginAction = loginAction;
   }

   public String getErrorMsg() {
      return errorMsg;
   }

   public void setErrorMsg(String errorMsg) {
      this.errorMsg = errorMsg;
   }

   public void output(PrintWriter writer){
      writer.println("<!DOCTYPE html>");
      writer.println("<html>");
      writer.println("<head>");
      writer.println("    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\" charset=\"utf-8\">");
      writer.println("    <style>");
      writer.println("        body {");
      writer.println("            font-family: Arial, Helvetica, sans-serif;");
      writer.println("        }");
      writer.println("");
      writer.println("        /* Full-width input fields */");
      writer.println("        input[type=text], input[type=password] {");
      writer.println("            width: 100%;");
      writer.println("            padding: 12px 20px;");
      writer.println("            margin: 8px 0;");
      writer.println("            display: inline-block;");
      writer.println("            border: 1px solid #ccc;");
      writer.println("            box-sizing: border-box;");
      writer.println("        }");
      writer.println("");
      writer.println("        /* Set a style for all buttons */");
      writer.println("        button {");
      writer.println("            background-color: #04AA6D;");
      writer.println("            color: white;");
      writer.println("            padding: 14px 20px;");
      writer.println("            margin: 8px 0;");
      writer.println("            border: none;");
      writer.println("            cursor: pointer;");
      writer.println("            width: 100%;");
      writer.println("        }");
      writer.println("");
      writer.println("        button:hover {");
      writer.println("            opacity: 0.8;");
      writer.println("        }");
      writer.println("");
      writer.println("        /* Extra styles for the cancel button */");
      writer.println("        .cancelbtn {");
      writer.println("            width: auto;");
      writer.println("            padding: 10px 18px;");
      writer.println("            background-color: #f44336;");
      writer.println("        }");
      writer.println("");
      writer.println("        /* Center the image and position the close button */");
      writer.println("        .imgcontainer {");
      writer.println("            text-align: center;");
      writer.println("            margin: 24px 0 12px 0;");
      writer.println("            position: relative;");
      writer.println("        }");
      writer.println("");
      writer.println("        img.avatar {");
      writer.println("            width: 40%;");
      writer.println("            border-radius: 50%;");
      writer.println("        }");
      writer.println("");
      writer.println("        .container {");
      writer.println("            padding: 16px;");
      writer.println("        }");
      writer.println("");
      writer.println("        span.psw {");
      writer.println("            float: right;");
      writer.println("            padding-top: 16px;");
      writer.println("        }");
      writer.println("");
      writer.println("        /* The Modal (background) */");
      writer.println("        .modal {");
      writer.println("            display: none; /* Hidden by default */");
      writer.println("            position: fixed; /* Stay in place */");
      writer.println("            z-index: 1; /* Sit on top */");
      writer.println("            left: 0;");
      writer.println("            top: 0;");
      writer.println("            width: 100%; /* Full width */");
      writer.println("            height: 100%; /* Full height */");
      writer.println("            overflow: auto; /* Enable scroll if needed */");
      writer.println("            background-color: rgb(0, 0, 0); /* Fallback color */");
      writer.println("            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */");
      writer.println("            padding-top: 60px;");
      writer.println("        }");
      writer.println("");
      writer.println("        /* Modal Content/Box */");
      writer.println("        .modal-content {");
      writer.println("            background-color: #fefefe;");
      writer.println("            margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */");
      writer.println("            border: 1px solid #888;");
      writer.println("            width: 80%; /* Could be more or less, depending on screen size */");
      writer.println("        }");
      writer.println("");
      writer.println("        /* The Close Button (x) */");
      writer.println("        .close {");
      writer.println("            position: absolute;");
      writer.println("            right: 25px;");
      writer.println("            top: 0;");
      writer.println("            color: #000;");
      writer.println("            font-size: 35px;");
      writer.println("            font-weight: bold;");
      writer.println("        }");
      writer.println("");
      writer.println("        .close:hover,");
      writer.println("        .close:focus {");
      writer.println("            color: red;");
      writer.println("            cursor: pointer;");
      writer.println("        }");
      writer.println("");
      writer.println("        /* Add Zoom Animation */");
      writer.println("        .animate {");
      writer.println("            -webkit-animation: animatezoom 0.6s;");
      writer.println("            animation: animatezoom 0.6s");
      writer.println("        }");
      writer.println("");
      writer.println("        @-webkit-keyframes animatezoom {");
      writer.println("            from {");
      writer.println("                -webkit-transform: scale(0)");
      writer.println("            }");
      writer.println("            to {");
      writer.println("                -webkit-transform: scale(1)");
      writer.println("            }");
      writer.println("        }");
      writer.println("");
      writer.println("        @keyframes animatezoom {");
      writer.println("            from {");
      writer.println("                transform: scale(0)");
      writer.println("            }");
      writer.println("            to {");
      writer.println("                transform: scale(1)");
      writer.println("            }");
      writer.println("        }");
      writer.println("");
      writer.println("        /* Change styles for span and cancel button on extra small screens */");
      writer.println("        @media screen and (max-width: 300px) {");
      writer.println("            span.psw {");
      writer.println("                display: block;");
      writer.println("                float: none;");
      writer.println("            }");
      writer.println("");
      writer.println("            .cancelbtn {");
      writer.println("                width: 100%;");
      writer.println("            }");
      writer.println("        }");
      writer.println("    </style>");
      writer.println("</head>");
      writer.println("<body>");
      writer.println("<div align=\"center\" style=\"background-color: rgb(255,255,204); width: 100%; height: 5%;\">");
      writer.println("  <span style=\"font-size: medium; color: BLUE; \">");
      writer.println("    <h1>");
      writer.println("      Fangsoft考试中心");
      writer.println("    </h1>");
      writer.println("  </span>");
      writer.println("</div>");
      writer.println("<!--<h2 align=\"center\">FangSoft考试中心</h2>-->");
      writer.println("");

      writer.println("<form class=\"modal-content animate\" method=\"post\">");
//      writer.println("<form action=\"TestCenterViewServlet\" class=\"modal-content animate\" method=\"post\">");
      writer.println("<form action="+this.getLoginAction()+ " class=\"modal-content animate\" method=\"post\">");
      writer.println("");
      writer.println("    <div class=\"container\">");
      writer.println("        <label><b>用户名：</b></label>");
//      writer.println("        <input name=\"userId\" placeholder=\"输入用户名\" required type=\"text\">");
      writer.println("        <input name=\"userId\" placeholder=" +this.getUserId() +" required type=\"text\">");
      writer.println("");
      writer.println("        <label><b>密码：</b></label>");
      writer.println("        <input name=\"password\" placeholder=\"输入密码\" required type=\"password\">");
      writer.println("");
      writer.println("            <button type=\"submit\">登录</button>");
      writer.println("");
      writer.println("        <label>");
      writer.println("            <input checked=\"checked\" name=\"remember\" type=\"checkbox\"> 在此计算机上记住我的信息");
      writer.println("        </label>");
      writer.println("    </div>");
      writer.println("");
      writer.println("    <div class=\"container\" style=\"background-color:#f1f1f1\">");
      writer.println("        <button class=\"cancelbtn\" type=\"button\">取消");
      writer.println("        </button>");
      writer.println("        <span class=\"psw\"><a href=\"loginView.jsp\">忘记密码?</a></span>");
      writer.println("    </div>");
      writer.println("</form>");
      writer.println("");
      writer.println("</body>");
      writer.println("</html>");

   }
}