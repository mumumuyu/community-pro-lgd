package cn.zj.lgd.framework.operatelog.config;

import cn.zj.lgd.framework.operatelog.core.aop.OperateLogAspect;
import cn.zj.lgd.framework.operatelog.core.service.OperateLogFrameworkService;
import cn.zj.lgd.framework.operatelog.core.service.OperateLogFrameworkServiceImpl;
import cn.zj.lgd.module.system.api.logger.OperateLogApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class LgdOperateLogAutoConfiguration {

    @Bean
    public OperateLogAspect operateLogAspect() {
        return new OperateLogAspect();
    }

    @Bean
    public OperateLogFrameworkService operateLogFrameworkService(OperateLogApi operateLogApi) {
        return new OperateLogFrameworkServiceImpl(operateLogApi);
    }

}
