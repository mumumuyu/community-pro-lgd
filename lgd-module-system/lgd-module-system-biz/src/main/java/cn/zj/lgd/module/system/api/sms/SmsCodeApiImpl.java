package cn.zj.lgd.module.system.api.sms;

import cn.zj.lgd.module.system.api.sms.dto.code.SmsCodeCheckReqDTO;
import cn.zj.lgd.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import cn.zj.lgd.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import cn.zj.lgd.module.system.service.sms.SmsCodeService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;

/**
 * 短信验证码 API 实现类
 *
 *
 */
@Service
@Validated
public class SmsCodeApiImpl implements SmsCodeApi {

    @Resource
    private SmsCodeService smsCodeService;

    @Override
    public void sendSmsCode(SmsCodeSendReqDTO reqDTO) {
        smsCodeService.sendSmsCode(reqDTO);
    }

    @Override
    public void useSmsCode(SmsCodeUseReqDTO reqDTO) {
        smsCodeService.useSmsCode(reqDTO);
    }

    @Override
    public void checkSmsCode(SmsCodeCheckReqDTO reqDTO) {
        smsCodeService.checkSmsCode(reqDTO);
    }

}
