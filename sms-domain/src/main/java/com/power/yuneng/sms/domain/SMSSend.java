package com.power.yuneng.sms.domain;

import java.io.Serializable;

/**
 * Created by gryang on 16/05/11.
 */
public class SMSSend implements Serializable {
    private static final long serialVersionUID = 8467975548471765580L;

    /*
    业务渠道名称
     */
    private String bizName;

    /*
    业务渠道标识
     */
    private String bizTarget;

    /*
    创建时间
     */
    private String createTime;

    /*
    发送渠道
     */
    private String sendChannel;

    /*
    发送渠道描述
     */
    private String sendChannelDesc;


    public SMSSend() {
    }

    public SMSSend(String bizName, String sendChannel, String bizTarget) {
        this.bizName = bizName;
        this.sendChannel = sendChannel;
        this.bizTarget = bizTarget;
    }

    public String getBizName() {
        return bizName;
    }

    public String getBizTarget() {
        return bizTarget;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getSendChannel() {
        return sendChannel;
    }

    public String getSendChannelDesc() {
        return sendChannelDesc;
    }

    public void setSendChannelDesc(String sendChannelDesc) {
        this.sendChannelDesc = sendChannelDesc;
    }

    @Override
    public String toString() {
        return "SMSSend{" +
                "bizName='" + bizName + '\'' +
                ", bizTarget='" + bizTarget + '\'' +
                ", createTime='" + createTime + '\'' +
                ", sendChannel='" + sendChannel + '\'' +
                ", sendChannelDesc='" + sendChannelDesc + '\'' +
                '}';
    }
}
