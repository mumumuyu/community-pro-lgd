package cn.zj.lgd.module.bpm.controller.admin.oa;

import cn.zj.lgd.module.bpm.controller.admin.oa.vo.BpmOALeaveCreateReqVO;
import cn.zj.lgd.module.bpm.controller.admin.oa.vo.BpmOALeavePageReqVO;
import cn.zj.lgd.module.bpm.controller.admin.oa.vo.BpmOALeaveRespVO;
import cn.zj.lgd.module.bpm.convert.oa.BpmOALeaveConvert;
import cn.zj.lgd.module.bpm.dal.dataobject.oa.BpmOALeaveDO;
import cn.zj.lgd.framework.common.pojo.CommonResult;
import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.bpm.service.oa.BpmOALeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.zj.lgd.framework.common.pojo.CommonResult.success;
import static cn.zj.lgd.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

/**
 * OA 请假申请 Controller，用于演示自己存储数据，接入工作流的例子
 *
 * @author jason
 *
 */
@Api(tags = "管理后台 - OA 请假申请")
@RestController
@RequestMapping("/bpm/oa/leave")
@Validated
public class BpmOALeaveController {

    @Resource
    private BpmOALeaveService leaveService;

    @PostMapping("/create")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:create')")
    @ApiOperation("创建请求申请")
    public CommonResult<Long> createLeave(@Valid @RequestBody BpmOALeaveCreateReqVO createReqVO) {
        return success(leaveService.createLeave(getLoginUserId(), createReqVO));
    }

    @GetMapping("/get")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @ApiOperation("获得请假申请")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<BpmOALeaveRespVO> getLeave(@RequestParam("id") Long id) {
        BpmOALeaveDO leave = leaveService.getLeave(id);
        return success(BpmOALeaveConvert.INSTANCE.convert(leave));
    }

    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('bpm:oa-leave:query')")
    @ApiOperation("获得请假申请分页")
    public CommonResult<PageResult<BpmOALeaveRespVO>> getLeavePage(@Valid BpmOALeavePageReqVO pageVO) {
        PageResult<BpmOALeaveDO> pageResult = leaveService.getLeavePage(getLoginUserId(), pageVO);
        return success(BpmOALeaveConvert.INSTANCE.convertPage(pageResult));
    }

}
