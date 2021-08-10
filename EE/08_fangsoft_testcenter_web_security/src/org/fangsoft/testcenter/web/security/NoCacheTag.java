package org.fangsoft.testcenter.web.security;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

public class NoCacheTag extends TagSupport {
    public int doStartTag() throws JspException {
        return Tag.SKIP_BODY;
    }
    public int doEndTag() throws JspException {
        HttpServletResponse response=(HttpServletResponse)this.
                pageContext.getResponse();
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setDateHeader("Expires", 0); //stale
        response.setHeader("Pragma","no-cache"); //HTTP 1.0
        return Tag.EVAL_PAGE;
    }
}
