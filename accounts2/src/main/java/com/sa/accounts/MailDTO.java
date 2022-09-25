package com.sa.accounts;

public class MailDTO {
    private String to;
    private String content;
    private String subject;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MailDTO() {
    }

    public MailDTO(String to, String content, String subject) {
        this.to = to;
        this.content = content;
        this.subject = subject;
    }
}
