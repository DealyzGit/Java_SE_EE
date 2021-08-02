package org.fangsoft.testcenter.web.tag;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.Tag;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.fangsoft.testcenter.web.JSPUtil;

import java.io.IOException;

public class NotLoginedTag extends TagSupport {
    public int doStartTag() throws JspException {
        return Tag.SKIP_BODY;
    }
    public int doEndTag() throws JspException {
        HttpServletRequest request= (HttpServletRequest)this.pageContext.getRequest();
        HttpServletResponse response=
                (HttpServletResponse)this.pageContext.getResponse();
        try {
            if(!JSPUtil.processNotLogin(request,response))return Tag.SKIP_PAGE;
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Tag.EVAL_PAGE;
    }


}
