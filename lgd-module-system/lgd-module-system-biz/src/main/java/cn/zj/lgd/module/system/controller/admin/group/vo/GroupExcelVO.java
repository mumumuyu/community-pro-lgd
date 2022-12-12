package cn.zj.lgd.module.system.controller.admin.group.vo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.zj.lgd.framework.excel.core.annotations.DictFormat;
import cn.zj.lgd.framework.excel.core.convert.DictConvert;


/**
 * 用户组 Excel VO
 *
 *
 */
@Data
public class GroupExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("名字")
    private String name;

    @ExcelProperty("描述")
    private String description;

    @ExcelProperty(value = "状态", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
