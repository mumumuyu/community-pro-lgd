package cn.zj.lgd.module.infra.dal.mysql.db;

import cn.zj.lgd.framework.mybatis.core.mapper.BaseMapperX;
import cn.zj.lgd.module.infra.dal.dataobject.db.DataSourceConfigDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据源配置 Mapper
 *
 *
 */
@Mapper
public interface DataSourceConfigMapper extends BaseMapperX<DataSourceConfigDO> {
}
