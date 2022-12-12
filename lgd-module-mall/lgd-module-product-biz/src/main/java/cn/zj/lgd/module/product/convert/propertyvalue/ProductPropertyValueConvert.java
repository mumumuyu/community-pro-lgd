package cn.zj.lgd.module.product.convert.propertyvalue;

import java.util.*;

import cn.zj.lgd.framework.common.pojo.PageResult;

import cn.zj.lgd.module.product.controller.admin.property.vo.value.ProductPropertyValueCreateReqVO;
import cn.zj.lgd.module.product.controller.admin.property.vo.value.ProductPropertyValueRespVO;
import cn.zj.lgd.module.product.controller.admin.property.vo.value.ProductPropertyValueUpdateReqVO;
import cn.zj.lgd.module.product.dal.dataobject.property.ProductPropertyValueDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 规格值 Convert
 *
 *
 */
@Mapper
public interface ProductPropertyValueConvert {

    ProductPropertyValueConvert INSTANCE = Mappers.getMapper(ProductPropertyValueConvert.class);

    ProductPropertyValueDO convert(ProductPropertyValueCreateReqVO bean);

    ProductPropertyValueDO convert(ProductPropertyValueUpdateReqVO bean);

    ProductPropertyValueRespVO convert(ProductPropertyValueDO bean);

    List<ProductPropertyValueRespVO> convertList(List<ProductPropertyValueDO> list);

    PageResult<ProductPropertyValueRespVO> convertPage(PageResult<ProductPropertyValueDO> page);

    List<ProductPropertyValueDO> convertList03(List<ProductPropertyValueCreateReqVO> list);

}
