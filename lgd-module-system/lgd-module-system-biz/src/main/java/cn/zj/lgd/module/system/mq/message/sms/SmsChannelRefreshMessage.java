package cn.zj.lgd.module.system.mq.message.sms;

import cn.zj.lgd.framework.mq.core.pubsub.AbstractChannelMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 短信渠道的数据刷新 Message
 *
 * 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SmsChannelRefreshMessage extends AbstractChannelMessage {

    @Override
    public String getChannel() {
        return "system.sms-channel.refresh";
    }

}
