package cn.zj.lgd.module.bpm.convert.definition;

import java.util.*;

import cn.zj.lgd.module.bpm.controller.admin.definition.vo.group.BpmUserGroupCreateReqVO;
import cn.zj.lgd.module.bpm.controller.admin.definition.vo.group.BpmUserGroupRespVO;
import cn.zj.lgd.module.bpm.controller.admin.definition.vo.group.BpmUserGroupUpdateReqVO;
import cn.zj.lgd.module.bpm.dal.dataobject.definition.BpmUserGroupDO;
import cn.zj.lgd.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * 用户组 Convert
 *
 *
 */
@Mapper
public interface BpmUserGroupConvert {

    BpmUserGroupConvert INSTANCE = Mappers.getMapper(BpmUserGroupConvert.class);

    BpmUserGroupDO convert(BpmUserGroupCreateReqVO bean);

    BpmUserGroupDO convert(BpmUserGroupUpdateReqVO bean);

    BpmUserGroupRespVO convert(BpmUserGroupDO bean);

    List<BpmUserGroupRespVO> convertList(List<BpmUserGroupDO> list);

    PageResult<BpmUserGroupRespVO> convertPage(PageResult<BpmUserGroupDO> page);

    @Named("convertList2")
    List<BpmUserGroupRespVO> convertList2(List<BpmUserGroupDO> list);

}
