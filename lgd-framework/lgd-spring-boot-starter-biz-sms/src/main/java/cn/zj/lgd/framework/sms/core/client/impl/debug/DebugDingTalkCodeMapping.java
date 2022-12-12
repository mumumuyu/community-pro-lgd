package cn.zj.lgd.framework.sms.core.client.impl.debug;

import cn.zj.lgd.framework.common.exception.ErrorCode;
import cn.zj.lgd.framework.common.exception.enums.GlobalErrorCodeConstants;
import cn.zj.lgd.framework.sms.core.client.SmsCodeMapping;
import cn.zj.lgd.framework.sms.core.enums.SmsFrameworkErrorCodeConstants;

import java.util.Objects;

/**
 * 钉钉的 SmsCodeMapping 实现类
 *
 *
 */
public class DebugDingTalkCodeMapping implements SmsCodeMapping {

    @Override
    public ErrorCode apply(String apiCode) {
        return Objects.equals(apiCode, "0") ? GlobalErrorCodeConstants.SUCCESS : SmsFrameworkErrorCodeConstants.SMS_UNKNOWN;
    }

}
