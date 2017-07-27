package com.power.yuneng.sms.domain;

import java.io.Serializable;

/**
 * Created by gryang on 16/05/11.
 */
public class SMSTemplate implements Serializable {
    private static final long serialVersionUID = 8467975548471765580L;
    private Long id;
    private String content;
    private String bizTarget;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBizTarget() {
        return bizTarget;
    }

    public void setBizTarget(String bizTarget) {
        this.bizTarget = bizTarget;
    }
}
