package cn.zj.lgd.module.infra.controller.admin.job;

import cn.zj.lgd.framework.common.pojo.CommonResult;
import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.framework.excel.core.util.ExcelUtils;
import cn.zj.lgd.framework.operatelog.core.annotations.OperateLog;
import cn.zj.lgd.module.infra.controller.admin.job.vo.log.JobLogExcelVO;
import cn.zj.lgd.module.infra.controller.admin.job.vo.log.JobLogExportReqVO;
import cn.zj.lgd.module.infra.controller.admin.job.vo.log.JobLogPageReqVO;
import cn.zj.lgd.module.infra.controller.admin.job.vo.log.JobLogRespVO;
import cn.zj.lgd.module.infra.convert.job.JobLogConvert;
import cn.zj.lgd.module.infra.dal.dataobject.job.JobLogDO;
import cn.zj.lgd.module.infra.service.job.JobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.zj.lgd.framework.common.pojo.CommonResult.success;
import static cn.zj.lgd.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Api(tags = "管理后台 - 定时任务日志")
@RestController
@RequestMapping("/infra/job-log")
@Validated
public class JobLogController {

    @Resource
    private JobLogService jobLogService;

    @GetMapping("/get")
    @ApiOperation("获得定时任务日志")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('infra:job:query')")
    public CommonResult<JobLogRespVO> getJobLog(@RequestParam("id") Long id) {
        JobLogDO jobLog = jobLogService.getJobLog(id);
        return success(JobLogConvert.INSTANCE.convert(jobLog));
    }

    @GetMapping("/list")
    @ApiOperation("获得定时任务日志列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('infra:job:query')")
    public CommonResult<List<JobLogRespVO>> getJobLogList(@RequestParam("ids") Collection<Long> ids) {
        List<JobLogDO> list = jobLogService.getJobLogList(ids);
        return success(JobLogConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得定时任务日志分页")
    @PreAuthorize("@ss.hasPermission('infra:job:query')")
    public CommonResult<PageResult<JobLogRespVO>> getJobLogPage(@Valid JobLogPageReqVO pageVO) {
        PageResult<JobLogDO> pageResult = jobLogService.getJobLogPage(pageVO);
        return success(JobLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出定时任务日志 Excel")
    @PreAuthorize("@ss.hasPermission('infra:job:export')")
    @OperateLog(type = EXPORT)
    public void exportJobLogExcel(@Valid JobLogExportReqVO exportReqVO,
                                  HttpServletResponse response) throws IOException {
        List<JobLogDO> list = jobLogService.getJobLogList(exportReqVO);
        // 导出 Excel
        List<JobLogExcelVO> datas = JobLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "任务日志.xls", "数据", JobLogExcelVO.class, datas);
    }

}
