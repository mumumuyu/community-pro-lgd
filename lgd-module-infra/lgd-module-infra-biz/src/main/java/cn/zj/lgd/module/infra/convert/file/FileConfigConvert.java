package cn.zj.lgd.module.infra.convert.file;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.infra.controller.admin.file.vo.config.FileConfigCreateReqVO;
import cn.zj.lgd.module.infra.controller.admin.file.vo.config.FileConfigRespVO;
import cn.zj.lgd.module.infra.controller.admin.file.vo.config.FileConfigUpdateReqVO;
import cn.zj.lgd.module.infra.dal.dataobject.file.FileConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 文件配置 Convert
 *
 *
 */
@Mapper
public interface FileConfigConvert {

    FileConfigConvert INSTANCE = Mappers.getMapper(FileConfigConvert.class);

    @Mapping(target = "config", ignore = true)
    FileConfigDO convert(FileConfigCreateReqVO bean);

    @Mapping(target = "config", ignore = true)
    FileConfigDO convert(FileConfigUpdateReqVO bean);

    FileConfigRespVO convert(FileConfigDO bean);

    List<FileConfigRespVO> convertList(List<FileConfigDO> list);

    PageResult<FileConfigRespVO> convertPage(PageResult<FileConfigDO> page);

}
