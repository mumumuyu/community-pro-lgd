package cn.zj.lgd.module.system.mq.message.permission;

import cn.zj.lgd.framework.mq.core.pubsub.AbstractChannelMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色数据刷新 Message
 *
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleRefreshMessage extends AbstractChannelMessage {

    @Override
    public String getChannel() {
        return "system.role.refresh";
    }

}
