package com.power.yuneng.sms.impl;

import com.power.yuneng.sms.api.ISMSService;
import com.power.yuneng.sms.dao.SMSDao;
import com.power.yuneng.sms.domain.SMSCheckCode;
import com.power.yuneng.sms.domain.SMSSend;
import com.power.yuneng.sms.domain.SMSStatus;
import com.sun.javafx.binding.StringFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * Created by wuhun0301 on 17/07/26.
 */
@Service("SMSServiceImpl")
public class SMSServiceImpl implements ISMSService{

    private static final Logger logger = LoggerFactory.getLogger(SMSServiceImpl.class);

    private List<SMSSend> sendList = new ArrayList<>();

    private List<SMSSend> sendNewList = new ArrayList<>();
    //云之讯短信key
    public final static String smsKey = "253";

    private static final String url = "http://sms.253.com/msg/send";// 应用地址
    private static final String un = "N6882898";// 账号
    private static final String pw = "M4LuOmDTnW042b";// 密码
    private static final String rd = "1";// 是否需要状态报告，需要1，不需要0
    private static final String ex = null;// 扩展码

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SMSDao smsDao;

    public final static String ERROR = "ERROR";

    /**
     * true sendList m
     * false sendNewList
     */
    private AtomicBoolean needChange = new AtomicBoolean();

    @Override
    public boolean sendSMS(SMSCheckCode smsCheckCode) {
        String msg = StringFormatter.format("【%s】您好，你的验证码是:%s, 请不要告诉他人", smsCheckCode.getBizTarget(),smsCheckCode.getCheckCode()).get();// 短信内容

        HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        GetMethod method = new GetMethod();
        try {
            URI base = new URI(url, false);
            method.setURI(new URI(base, "send", false));
            method.setQueryString(new NameValuePair[] { new NameValuePair("un", un), new NameValuePair("pw", pw),
                    new NameValuePair("phone", smsCheckCode.getPhone()), new NameValuePair("rd", rd), new NameValuePair("msg", msg),
                    new NameValuePair("ex", ex), });
            int result = client.executeMethod(method);
            if (result == HttpStatus.SC_OK) {
                InputStream in = method.getResponseBodyAsStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = in.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                String rtn = URLDecoder.decode(baos.toString(), "UTF-8");
                if (rtn!=null){

                    String[] rtns = rtn.split("\n");
                    String messageid = null;
                    if (rtns.length>1) {
                        messageid = rtns[1];
                    }
                    String[] rtnStatus = rtns[0].split(",");
                    String rtnCdoe = rtnStatus[1];
                    String rtnTime = rtnStatus[0];

                    final SMSStatus status = new SMSStatus(
                            smsCheckCode.getBizTarget(),
                            smsCheckCode.getPhone(),
                            smsCheckCode.getCheckCode(),
                            rtn,
                            rtnCdoe+"",
                            smsKey,
                            SMS_TXT,
                            messageid
                            ,rtnTime
                    );

                    smsDao.saveCheckCode(status);
                    if(!"0".equals(status.getResultCode())){
                        return false;
                    }
                    return smsDao.saveSMSStatus(status);
                }else {
                    throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
                }
            } else {
                throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
            }
        } catch (Exception e){

        }finally {
            method.releaseConnection();
        }
        return false;
    }

    @Override
    public boolean sendVoiceSMS(SMSCheckCode smsCheckCode) {
        return false;
    }

    private String getChannel(String target) {
        if (needChange.get()) {
            for(SMSSend send : sendNewList) {
                if (send.getBizTarget().equals(target)) {
                    return send.getSendChannel();
                }
            }
        } else {
            for(SMSSend send : sendList) {
                if (send.getBizTarget().equals(target)) {
                    return send.getSendChannel();
                }
            }
        }

        return "";
    }

    /**
     * 异常枚举
     */
    public enum ErrorCode {
//        ERROR_0("0","提交成功"),
        ERROR101("无此用户"),
        ERROR102("密码错误"),
        ERROR103("提交过快（提交速度超过流速限制）"),
        ERROR104("系统忙（因平台侧原因，暂时无法处理提交的短信）"),
        ERROR105("敏感短信（短信内容包含敏感词）"),
        ERROR106("消息长度错（>536或<=0）"),
        ERROR107("包含错误的手机号码"),
        ERROR108("手机号码个数错（群发>50000或<=0）"),
        ERROR109("无发送额度（该用户可用短信数已使用完）"),
        ERROR110("不在发送时间内"),
        ERROR113("extno格式错（非数字或者长度不对）"),
        ERROR116("签名不合法或未带签名（用户必须带签名的前提下）"),
        ERROR117("IP地址认证错,请求调用的IP地址不是系统登记的IP地址"),
        ERROR118("用户没有相应的发送权限（账号被禁止发送）"),
        ERROR119("用户已过期"),
        ERROR120("违反放盗用策略(日发限制) --自定义添加"),
        ERROR121("必填参数。是否需要状态报告，取值true或false"),
        ERROR122("5分钟内相同账号提交相同消息内容过多"),
        ERROR123("发送类型错误"),
        ERROR124("白模板匹配错误"),
        ERROR125("匹配驳回模板，提交失败"),
        ERROR126("审核通过模板匹配错误"),
        ;
        private final String msg;

        ErrorCode(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }


}
