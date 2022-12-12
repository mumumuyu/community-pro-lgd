package cn.zj.lgd.module.system.controller.admin.group.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.zj.lgd.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.zj.lgd.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 用户组分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GroupPageReqVO extends PageParam {

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
