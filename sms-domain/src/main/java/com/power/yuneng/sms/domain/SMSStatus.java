package com.power.yuneng.sms.domain;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by gryang on 16/05/11.
 */
public class SMSStatus implements Serializable {
    private static final long serialVersionUID = 1027194809596365960L;

    /*
    短信编码
     */
    private long id;

    /*
    发送来源系统
     */
    private String bizTarget;

    /*
    发送给的号码
     */
    private String toSendPhone;

    /*
    发送内容
     */
    private String sendContent;

    /*
    发送结果
     */
    private String sendResult;

    /*
    结果编码
    */
    private String resultCode;

    /*
    发送服务商
     */
    private String sendChannel;

    /*
    发送服务商描述
     */
    private String channelDesc;

    /*
    发送时间
     */
    private String sendTime;

    /*
    短信类型    文字短信 or 语音短信
     */
    private String smsType;

    /*
    唯一电话标识符
     */
    private String callSid;

    public SMSStatus() {
    }

    public SMSStatus(String bizTarget, String toSendPhone, String sendContent, String smsType, String sendResult, String resultCode, String sendChannel,String sendTime) {
        this.bizTarget = bizTarget;
        this.toSendPhone = toSendPhone;
        this.sendContent = sendContent;
        this.smsType = smsType;
        this.sendResult = sendResult;
        this.resultCode = resultCode;
        this.sendChannel = sendChannel;
        this.sendTime = sendTime;

    }

    public SMSStatus(String bizTarget, String toSendPhone, String sendContent, String sendResult, String resultCode, String sendChannel, String smsType, String callSid,String sendTime) {
        this.bizTarget = bizTarget;
        this.toSendPhone = toSendPhone;
        this.sendContent = sendContent;
        this.sendResult = sendResult;
        this.resultCode = resultCode;
        this.sendChannel = sendChannel;
        this.smsType = smsType;
        this.callSid = callSid;
        this.sendTime = sendTime;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBizTarget() {
        return bizTarget;
    }

    public void setBizTarget(String bizTarget) {
        this.bizTarget = bizTarget;
    }

    public String getToSendPhone() {
        return toSendPhone;
    }

    public void setToSendPhone(String toSendPhone) {
        this.toSendPhone = toSendPhone;
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    public String getSendResult() {
        return sendResult;
    }

    public void setSendResult(String sendResult) {
        this.sendResult = sendResult;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getSendChannel() {
        return sendChannel;
    }

    public void setSendChannel(String sendChannel) {
        this.sendChannel = sendChannel;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getCallSid() {
        return callSid;
    }

    public void setCallSid(String callSid) {
        this.callSid = callSid;
    }

    @Override
    public String toString() {
        return "SMSStatus{" +
                "id=" + id +
                ", bizTarget='" + bizTarget + '\'' +
                ", toSendPhone='" + toSendPhone + '\'' +
                ", sendContent='" + sendContent + '\'' +
                ", sendResult='" + sendResult + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", sendChannel='" + sendChannel + '\'' +
                ", channelDesc='" + channelDesc + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", smsType='" + smsType + '\'' +
                ", callSid='" + callSid + '\'' +
                '}';
    }
}
