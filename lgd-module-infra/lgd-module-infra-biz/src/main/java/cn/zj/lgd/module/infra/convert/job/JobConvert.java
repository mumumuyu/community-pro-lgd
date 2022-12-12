package cn.zj.lgd.module.infra.convert.job;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.infra.controller.admin.job.vo.job.JobCreateReqVO;
import cn.zj.lgd.module.infra.controller.admin.job.vo.job.JobExcelVO;
import cn.zj.lgd.module.infra.controller.admin.job.vo.job.JobRespVO;
import cn.zj.lgd.module.infra.controller.admin.job.vo.job.JobUpdateReqVO;
import cn.zj.lgd.module.infra.dal.dataobject.job.JobDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 定时任务 Convert
 *
 *
 */
@Mapper
public interface JobConvert {

    JobConvert INSTANCE = Mappers.getMapper(JobConvert.class);

    JobDO convert(JobCreateReqVO bean);

    JobDO convert(JobUpdateReqVO bean);

    JobRespVO convert(JobDO bean);

    List<JobRespVO> convertList(List<JobDO> list);

    PageResult<JobRespVO> convertPage(PageResult<JobDO> page);

    List<JobExcelVO> convertList02(List<JobDO> list);

}
