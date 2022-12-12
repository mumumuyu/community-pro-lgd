package cn.zj.lgd.module.product.dal.mysql.property;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.framework.mybatis.core.mapper.BaseMapperX;
import cn.zj.lgd.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.zj.lgd.module.product.controller.admin.property.vo.value.ProductPropertyValuePageReqVO;
import cn.zj.lgd.module.product.dal.dataobject.property.ProductPropertyValueDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 规格值 Mapper
 *
 *
 */
@Mapper
public interface ProductPropertyValueMapper extends BaseMapperX<ProductPropertyValueDO> {

    default List<ProductPropertyValueDO> selectListByPropertyId(List<Long> propertyIds) {
        return selectList(new LambdaQueryWrapperX<ProductPropertyValueDO>()
                .inIfPresent(ProductPropertyValueDO::getPropertyId, propertyIds));
    }

    default ProductPropertyValueDO selectByName(Long propertyId, String name) {
        return selectOne(new LambdaQueryWrapperX<ProductPropertyValueDO>()
                .eq(ProductPropertyValueDO::getPropertyId, propertyId)
                .eq(ProductPropertyValueDO::getName, name));
    }

    default void deletePropertyValueByPropertyId(Long propertyId) {
        delete(new LambdaQueryWrapperX<ProductPropertyValueDO>().eq(ProductPropertyValueDO::getPropertyId, propertyId)
                .eq(ProductPropertyValueDO::getDeleted, false));
    }

    default PageResult<ProductPropertyValueDO> selectPage(ProductPropertyValuePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductPropertyValueDO>()
                .eqIfPresent(ProductPropertyValueDO::getPropertyId, reqVO.getPropertyId())
                .likeIfPresent(ProductPropertyValueDO::getName, reqVO.getName())
                .eqIfPresent(ProductPropertyValueDO::getStatus, reqVO.getStatus())
                .orderByDesc(ProductPropertyValueDO::getId));
    }

}
