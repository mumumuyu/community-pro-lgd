package cn.zj.lgd.module.infra.dal.mysql.job;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.framework.mybatis.core.mapper.BaseMapperX;
import cn.zj.lgd.framework.mybatis.core.query.QueryWrapperX;
import cn.zj.lgd.module.infra.controller.admin.job.vo.log.JobLogExportReqVO;
import cn.zj.lgd.module.infra.controller.admin.job.vo.log.JobLogPageReqVO;
import cn.zj.lgd.module.infra.dal.dataobject.job.JobLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 任务日志 Mapper
 *
 *
 */
@Mapper
public interface JobLogMapper extends BaseMapperX<JobLogDO> {

    default PageResult<JobLogDO> selectPage(JobLogPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<JobLogDO>()
                .eqIfPresent("job_id", reqVO.getJobId())
                .likeIfPresent("handler_name", reqVO.getHandlerName())
                .geIfPresent("begin_time", reqVO.getBeginTime())
                .leIfPresent("end_time", reqVO.getEndTime())
                .eqIfPresent("status", reqVO.getStatus())
                .orderByDesc("id") // ID 倒序
        );
    }

    default List<JobLogDO> selectList(JobLogExportReqVO reqVO) {
        return selectList(new QueryWrapperX<JobLogDO>()
                .eqIfPresent("job_id", reqVO.getJobId())
                .likeIfPresent("handler_name", reqVO.getHandlerName())
                .geIfPresent("begin_time", reqVO.getBeginTime())
                .leIfPresent("end_time", reqVO.getEndTime())
                .eqIfPresent("status", reqVO.getStatus())
                .orderByDesc("id") // ID 倒序
        );
    }

}
