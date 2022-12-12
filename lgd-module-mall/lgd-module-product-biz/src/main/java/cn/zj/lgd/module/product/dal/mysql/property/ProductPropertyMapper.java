package cn.zj.lgd.module.product.dal.mysql.property;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.framework.mybatis.core.mapper.BaseMapperX;
import cn.zj.lgd.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.zj.lgd.module.product.controller.admin.property.vo.property.ProductPropertyPageReqVO;
import cn.zj.lgd.module.product.dal.dataobject.property.ProductPropertyDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 规格名称 Mapper
 *
 * 
 */
@Mapper
public interface ProductPropertyMapper extends BaseMapperX<ProductPropertyDO> {

    default PageResult<ProductPropertyDO> selectPage(ProductPropertyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductPropertyDO>()
                .likeIfPresent(ProductPropertyDO::getName, reqVO.getName())
                .eqIfPresent(ProductPropertyDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ProductPropertyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductPropertyDO::getId));
    }

    default ProductPropertyDO selectByName(String name) {
        return selectOne(new LambdaQueryWrapperX<ProductPropertyDO>()
                .eqIfPresent(ProductPropertyDO::getName, name));
    }

}
