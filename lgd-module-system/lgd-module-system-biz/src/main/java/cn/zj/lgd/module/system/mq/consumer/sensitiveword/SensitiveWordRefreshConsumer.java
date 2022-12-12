package cn.zj.lgd.module.system.mq.consumer.sensitiveword;

import cn.zj.lgd.framework.mq.core.pubsub.AbstractChannelMessageListener;
import cn.zj.lgd.module.system.mq.message.sensitiveword.SensitiveWordRefreshMessage;
import cn.zj.lgd.module.system.service.sensitiveword.SensitiveWordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link SensitiveWordRefreshMessage} 的消费者
 *
 * 
 */
@Component
@Slf4j
public class SensitiveWordRefreshConsumer extends AbstractChannelMessageListener<SensitiveWordRefreshMessage> {

    @Resource
    private SensitiveWordService sensitiveWordService;

    @Override
    public void onMessage(SensitiveWordRefreshMessage message) {
        log.info("[onMessage][收到 SensitiveWord 刷新消息]");
        sensitiveWordService.initLocalCache();
    }

}
