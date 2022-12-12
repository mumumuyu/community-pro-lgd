package cn.zj.lgd.framework.pay.config;

import cn.zj.lgd.framework.pay.core.client.PayClientFactory;
import cn.zj.lgd.framework.pay.core.client.impl.PayClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 支付配置类
 *
 *
 */
@AutoConfiguration
@EnableConfigurationProperties(PayProperties.class)
public class LgdPayAutoConfiguration {

    @Bean
    public PayClientFactory payClientFactory() {
        return new PayClientFactoryImpl();
    }

}
