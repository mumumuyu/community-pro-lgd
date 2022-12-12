package cn.zj.lgd.module.system.convert.group;

import java.util.*;

import cn.zj.lgd.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.zj.lgd.module.system.controller.admin.group.vo.*;
import cn.zj.lgd.module.system.dal.dataobject.group.GroupDO;

/**
 * 用户组 Convert
 *
 *
 */
@Mapper
public interface GroupConvert {

    GroupConvert INSTANCE = Mappers.getMapper(GroupConvert.class);

    GroupDO convert(GroupCreateReqVO bean);

    GroupDO convert(GroupUpdateReqVO bean);

    GroupRespVO convert(GroupDO bean);

    List<GroupRespVO> convertList(List<GroupDO> list);

    PageResult<GroupRespVO> convertPage(PageResult<GroupDO> page);

    List<GroupExcelVO> convertList02(List<GroupDO> list);

}
