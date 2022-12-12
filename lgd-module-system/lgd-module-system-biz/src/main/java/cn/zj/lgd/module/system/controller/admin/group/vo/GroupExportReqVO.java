package cn.zj.lgd.module.system.controller.admin.group.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.zj.lgd.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.zj.lgd.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 用户组 Excel 导出 Request VO", description = "参数和 GroupPageReqVO 是一致的")
@Data
public class GroupExportReqVO {

    @ApiModelProperty(value = "名字", example = "lgd")
    private String name;

    @ApiModelProperty(value = "描述", example = "我是描述")
    private String description;

    @ApiModelProperty(value = "状态", example = "0")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
