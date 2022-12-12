package cn.zj.lgd.framework.sms.config;

import cn.zj.lgd.framework.sms.core.client.SmsClientFactory;
import cn.zj.lgd.framework.sms.core.client.impl.SmsClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 短信配置类
 *
 *
 */
@AutoConfiguration
public class LgdSmsAutoConfiguration {

    @Bean
    public SmsClientFactory smsClientFactory() {
        return new SmsClientFactoryImpl();
    }

}
