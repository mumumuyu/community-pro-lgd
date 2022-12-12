package cn.zj.lgd.framework.file.config;

import cn.zj.lgd.framework.file.core.client.FileClientFactory;
import cn.zj.lgd.framework.file.core.client.FileClientFactoryImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 文件配置类
 *
 *
 */
@AutoConfiguration
public class LgdFileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
