package com.power.yuneng.sms.api;


import com.power.yuneng.sms.domain.SMSCheckCode;

/**
 * Created by gryang on 16/05/11.
 */
public interface ISMSService {

    public static final String SMS_TXT = "txt";
    public static final String SMS_VOICE = "voice";

    /**
     * 发送短信
     * @param smsCheckCode   短信内容
     * @return
     */
    boolean sendSMS(SMSCheckCode smsCheckCode);

    /**
     * 发送语音短信
     * @param smsCheckCode
     * @return
     */
    boolean sendVoiceSMS(SMSCheckCode smsCheckCode);

}
