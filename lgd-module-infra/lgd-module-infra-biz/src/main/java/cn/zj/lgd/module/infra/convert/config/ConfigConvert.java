package cn.zj.lgd.module.infra.convert.config;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.infra.controller.admin.config.vo.ConfigCreateReqVO;
import cn.zj.lgd.module.infra.controller.admin.config.vo.ConfigExcelVO;
import cn.zj.lgd.module.infra.controller.admin.config.vo.ConfigRespVO;
import cn.zj.lgd.module.infra.controller.admin.config.vo.ConfigUpdateReqVO;
import cn.zj.lgd.module.infra.dal.dataobject.config.ConfigDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ConfigConvert {

    ConfigConvert INSTANCE = Mappers.getMapper(ConfigConvert.class);

    PageResult<ConfigRespVO> convertPage(PageResult<ConfigDO> page);

    @Mapping(source = "configKey", target = "key")
    ConfigRespVO convert(ConfigDO bean);

    @Mapping(source = "key", target = "configKey")
    ConfigDO convert(ConfigCreateReqVO bean);

    ConfigDO convert(ConfigUpdateReqVO bean);

    @Mapping(source = "configKey", target = "key")
    List<ConfigExcelVO> convertList(List<ConfigDO> list);

}
