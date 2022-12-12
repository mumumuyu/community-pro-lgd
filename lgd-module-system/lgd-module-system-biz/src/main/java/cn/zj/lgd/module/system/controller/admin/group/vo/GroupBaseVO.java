package cn.zj.lgd.module.system.controller.admin.group.vo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 用户组 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class GroupBaseVO {

    @ApiModelProperty(value = "名字", required = true, example = "lgd")
    @NotNull(message = "名字不能为空")
    private String name;

    @ApiModelProperty(value = "描述", example = "我是描述")
    private String description;

    @ApiModelProperty(value = "状态", required = true, example = "0")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
