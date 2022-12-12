package cn.zj.lgd.module.pay.convert.channel;

import java.util.*;

import cn.zj.lgd.framework.common.pojo.PageResult;

import cn.zj.lgd.module.pay.controller.admin.merchant.vo.channel.PayChannelCreateReqVO;
import cn.zj.lgd.module.pay.controller.admin.merchant.vo.channel.PayChannelExcelVO;
import cn.zj.lgd.module.pay.controller.admin.merchant.vo.channel.PayChannelRespVO;
import cn.zj.lgd.module.pay.controller.admin.merchant.vo.channel.PayChannelUpdateReqVO;
import cn.zj.lgd.module.pay.dal.dataobject.merchant.PayChannelDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PayChannelConvert {

    PayChannelConvert INSTANCE = Mappers.getMapper(PayChannelConvert.class);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelCreateReqVO bean);

    @Mapping(target = "config",ignore = true)
    PayChannelDO convert(PayChannelUpdateReqVO bean);

    @Mapping(target = "config",expression = "java(cn.zj.lgd.framework.common.util.json.JsonUtils.toJsonString(bean.getConfig()))")
    PayChannelRespVO convert(PayChannelDO bean);

    List<PayChannelRespVO> convertList(List<PayChannelDO> list);

    PageResult<PayChannelRespVO> convertPage(PageResult<PayChannelDO> page);

    List<PayChannelExcelVO> convertList02(List<PayChannelDO> list);



}
