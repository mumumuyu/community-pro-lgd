package cn.zj.lgd.framework.mq.core.stream;

import cn.zj.lgd.framework.mq.core.message.AbstractRedisMessage;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Redis Stream Message 抽象类
 *
 *
 */
public abstract class AbstractStreamMessage extends AbstractRedisMessage {

    /**
     * 获得 Redis Stream Key
     *
     * @return Channel
     */
    @JsonIgnore // 避免序列化
    public abstract String getStreamKey();

}
