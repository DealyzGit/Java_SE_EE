package org.fangsoft.testcenter.web.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import org.fangsoft.testcenter.model.Test;
import org.fangsoft.testcenter.web.JSPUtil;

import java.io.IOException;
import java.util.List;

public class AllTestTag extends SimpleTagSupport {
    private String urlTestDetail;

    public String getUrlTestDetail() {
        return urlTestDetail;
    }

    public void setUrlTestDetail(String urlTestDetail) {
        this.urlTestDetail = urlTestDetail;
    }


}
