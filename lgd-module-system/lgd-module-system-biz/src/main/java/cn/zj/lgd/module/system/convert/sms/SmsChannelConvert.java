package cn.zj.lgd.module.system.convert.sms;

import cn.zj.lgd.module.system.controller.admin.sms.vo.channel.SmsChannelCreateReqVO;
import cn.zj.lgd.module.system.controller.admin.sms.vo.channel.SmsChannelRespVO;
import cn.zj.lgd.module.system.controller.admin.sms.vo.channel.SmsChannelSimpleRespVO;
import cn.zj.lgd.module.system.controller.admin.sms.vo.channel.SmsChannelUpdateReqVO;
import cn.zj.lgd.module.system.dal.dataobject.sms.SmsChannelDO;
import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.framework.sms.core.property.SmsChannelProperties;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信渠道 Convert
 *
 *
 */
@Mapper
public interface SmsChannelConvert {

    SmsChannelConvert INSTANCE = Mappers.getMapper(SmsChannelConvert.class);

    SmsChannelDO convert(SmsChannelCreateReqVO bean);

    SmsChannelDO convert(SmsChannelUpdateReqVO bean);

    SmsChannelRespVO convert(SmsChannelDO bean);

    List<SmsChannelRespVO> convertList(List<SmsChannelDO> list);

    PageResult<SmsChannelRespVO> convertPage(PageResult<SmsChannelDO> page);

    List<SmsChannelProperties> convertList02(List<SmsChannelDO> list);

    List<SmsChannelSimpleRespVO> convertList03(List<SmsChannelDO> list);

}
