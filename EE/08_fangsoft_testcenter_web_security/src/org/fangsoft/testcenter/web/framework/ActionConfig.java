package org.fangsoft.testcenter.web.framework;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ActionConfig implements Serializable {
    private Action action;//web层的操作
    private Map<String,ResponsePage> responsePageMap; //可能的响应页面

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Map<String, ResponsePage> getResponsePageMap() {
        return responsePageMap;
    }

    public void setResponsePageMap(Map<String, ResponsePage> responsePageMap) {
        this.responsePageMap = responsePageMap;
    }

    public ActionConfig(){
        this.responsePageMap=new HashMap<String,ResponsePage>();
    }

    //配置响应页面
    public void addResponsePage(String responseKey,String responseURI,ResponsePage.SendMode mode ){
        ResponsePage responsePage=new ResponsePage();
        responsePage.setMode(mode);
        responsePage.setResponseURI(responseURI);
        this.responsePageMap.put(responseKey, responsePage);
    }
    public void addResponsePage(String responseKey,String responseURI){
        this.addResponsePage(responseKey,
                responseURI,ResponsePage.SendMode.FORWARD);
    }
    public ResponsePage getResponsePage(String responseKey){
        return this.getResponsePageMap().get(responseKey);
    }
}
