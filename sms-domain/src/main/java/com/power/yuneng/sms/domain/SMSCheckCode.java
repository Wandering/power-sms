package com.power.yuneng.sms.domain;

import java.io.Serializable;

/**
 * Created by gryang on 16/05/11.
 */
public class SMSCheckCode implements Serializable {
    private static final long serialVersionUID = 7333932514146799165L;

    /**
     * 模板ID
     */
    private Long templateId;
    /**
     * 电话
     */
    private String phone;

    /**
     * 验证码
     */
    private String checkCode;

    /**
     * 渠道业务标识
     */
    private String bizTarget;

    /**
     * 有效时间
     */
    private String effectiveTime;


    public SMSCheckCode() {
    }

    public SMSCheckCode(String phone, String checkCode, String bizTarget, String effectiveTime) {
        this.phone = phone;
        this.checkCode = checkCode;
        this.bizTarget = bizTarget;
        this.effectiveTime = effectiveTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getBizTarget() {
        return bizTarget;
    }

    public void setBizTarget(String bizTarget) {
        this.bizTarget = bizTarget;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }
}
