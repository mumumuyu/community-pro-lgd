package cn.zj.lgd.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目的启动类
 *
 * 如果你碰到启动的问题，请认真阅读 https://doc.iocoder.cn/quick-start/ 文章
 * 如果你碰到启动的问题，请认真阅读 https://doc.iocoder.cn/quick-start/ 文章
 * 如果你碰到启动的问题，请认真阅读 https://doc.iocoder.cn/quick-start/ 文章
 *
 *
 */
@SuppressWarnings("SpringComponentScan") // 忽略 IDEA 无法识别 ${lgd.info.base-package}
@SpringBootApplication(scanBasePackages = {"${lgd.info.base-package}.server", "${lgd.info.base-package}.module"})
public class LgdServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LgdServerApplication.class, args);
    }

}
