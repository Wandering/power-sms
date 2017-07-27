package com.power.yuneng.sms.dao;

import com.power.yuneng.sms.domain.SMSSend;
import com.power.yuneng.sms.domain.SMSStatus;

import java.util.List;

/**
 * Created by gryang on 16/05/11.
 */
public interface SMSDao {
    /**
     * 保存短信信息
     * @param status
     * @return
     */
    boolean saveCheckCode(SMSStatus status);

    /**
     * 保存发送状态
     * @param status
     * @return
     */
    boolean saveSMSStatus(SMSStatus status);

    /**
     * 获取短信发送渠道信息集合
     * @return
     */
    List<SMSSend> getTemplate(Long id);

}
