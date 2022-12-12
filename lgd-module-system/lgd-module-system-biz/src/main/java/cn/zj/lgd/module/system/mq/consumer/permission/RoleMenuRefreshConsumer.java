package cn.zj.lgd.module.system.mq.consumer.permission;

import cn.zj.lgd.framework.mq.core.pubsub.AbstractChannelMessageListener;
import cn.zj.lgd.module.system.mq.message.permission.RoleMenuRefreshMessage;
import cn.zj.lgd.module.system.service.permission.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 针对 {@link RoleMenuRefreshMessage} 的消费者
 *
 *
 */
@Component
@Slf4j
public class RoleMenuRefreshConsumer extends AbstractChannelMessageListener<RoleMenuRefreshMessage> {

    @Resource
    private PermissionService permissionService;

    @Override
    public void onMessage(RoleMenuRefreshMessage message) {
        log.info("[onMessage][收到 Role 与 Menu 的关联刷新消息]");
        permissionService.initLocalCache();
    }

}
