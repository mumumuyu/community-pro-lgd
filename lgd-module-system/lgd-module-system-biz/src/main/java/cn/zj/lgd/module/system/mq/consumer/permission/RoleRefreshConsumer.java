package cn.zj.lgd.module.system.mq.consumer.permission;

import cn.zj.lgd.framework.mq.core.pubsub.AbstractChannelMessageListener;
import cn.zj.lgd.module.system.mq.message.permission.RoleRefreshMessage;
import cn.zj.lgd.module.system.service.permission.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link RoleRefreshMessage} 的消费者
 *
 *
 */
@Component
@Slf4j
public class RoleRefreshConsumer extends AbstractChannelMessageListener<RoleRefreshMessage> {

    @Resource
    private RoleService roleService;

    @Override
    public void onMessage(RoleRefreshMessage message) {
        log.info("[onMessage][收到 Role 刷新消息]");
        roleService.initLocalCache();
    }

}
