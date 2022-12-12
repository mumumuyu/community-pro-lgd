package cn.zj.lgd.module.system.convert.sms;

import cn.zj.lgd.module.system.controller.admin.sms.vo.log.SmsLogExcelVO;
import cn.zj.lgd.module.system.controller.admin.sms.vo.log.SmsLogRespVO;
import cn.zj.lgd.module.system.dal.dataobject.sms.SmsLogDO;
import cn.zj.lgd.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 短信日志 Convert
 *
 *
 */
@Mapper
public interface SmsLogConvert {

    SmsLogConvert INSTANCE = Mappers.getMapper(SmsLogConvert.class);

    SmsLogRespVO convert(SmsLogDO bean);

    List<SmsLogRespVO> convertList(List<SmsLogDO> list);

    PageResult<SmsLogRespVO> convertPage(PageResult<SmsLogDO> page);

    List<SmsLogExcelVO> convertList02(List<SmsLogDO> list);

}
