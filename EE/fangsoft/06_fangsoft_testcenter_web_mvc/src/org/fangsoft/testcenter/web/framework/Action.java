package org.fangsoft.testcenter.web.framework;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Action {
    public ResponsePage doProcess(HttpServletRequest request,
                                  HttpServletResponse response, ActionConfig urlMap)
            throws ServletException, IOException;
}
