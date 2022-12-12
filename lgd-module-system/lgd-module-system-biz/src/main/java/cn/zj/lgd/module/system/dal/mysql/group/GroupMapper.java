package cn.zj.lgd.module.system.dal.mysql.group;

import java.util.*;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.zj.lgd.framework.mybatis.core.mapper.BaseMapperX;
import cn.zj.lgd.module.system.dal.dataobject.group.GroupDO;
import org.apache.ibatis.annotations.Mapper;
import cn.zj.lgd.module.system.controller.admin.group.vo.*;

/**
 * 用户组 Mapper
 *
 * 
 */
@Mapper
public interface GroupMapper extends BaseMapperX<GroupDO> {

    default PageResult<GroupDO> selectPage(GroupPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GroupDO>()
                .likeIfPresent(GroupDO::getName, reqVO.getName())
                .eqIfPresent(GroupDO::getDescription, reqVO.getDescription())
                .eqIfPresent(GroupDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(GroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GroupDO::getId));
    }

    default List<GroupDO> selectList(GroupExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<GroupDO>()
                .likeIfPresent(GroupDO::getName, reqVO.getName())
                .eqIfPresent(GroupDO::getDescription, reqVO.getDescription())
                .eqIfPresent(GroupDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(GroupDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GroupDO::getId));
    }

}
