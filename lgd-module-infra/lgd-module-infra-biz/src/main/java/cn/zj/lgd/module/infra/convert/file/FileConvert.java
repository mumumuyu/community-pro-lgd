package cn.zj.lgd.module.infra.convert.file;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.infra.controller.admin.file.vo.file.FileRespVO;
import cn.zj.lgd.module.infra.dal.dataobject.file.FileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FileConvert {

    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);

    FileRespVO convert(FileDO bean);

    PageResult<FileRespVO> convertPage(PageResult<FileDO> page);

}
