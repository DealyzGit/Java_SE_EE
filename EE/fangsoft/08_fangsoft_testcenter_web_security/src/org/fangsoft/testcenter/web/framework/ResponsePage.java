package org.fangsoft.testcenter.web.framework;

public class ResponsePage {
    private String responseURI;
    private SendMode mode = SendMode.FORWARD;

    public String getResponseURI() {
        return responseURI;
    }

    public void setResponseURI(String responseURI) {
        this.responseURI = responseURI;
    }

    public SendMode getMode() {
        return mode;
    }

    public void setMode(SendMode mode) {
        this.mode = mode;
    }

    public ResponsePage() {
    }

    ;

    public ResponsePage(String responseURI) {
        this.responseURI = responseURI;
    }

    public ResponsePage(String responseURI, SendMode sendMode) {
        this.responseURI = responseURI;
        this.mode = sendMode;
    }

    public static enum SendMode {
        FORWARD("forward"),
        REDIRECT("redirect");
        private String value;

        private SendMode(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
