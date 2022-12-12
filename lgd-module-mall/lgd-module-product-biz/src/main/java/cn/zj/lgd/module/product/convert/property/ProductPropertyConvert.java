package cn.zj.lgd.module.product.convert.property;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.product.controller.admin.property.vo.property.ProductPropertyAndValueRespVO;
import cn.zj.lgd.module.product.controller.admin.property.vo.property.ProductPropertyCreateReqVO;
import cn.zj.lgd.module.product.controller.admin.property.vo.property.ProductPropertyRespVO;
import cn.zj.lgd.module.product.controller.admin.property.vo.property.ProductPropertyUpdateReqVO;
import cn.zj.lgd.module.product.controller.admin.property.vo.value.ProductPropertyValueRespVO;
import cn.zj.lgd.module.product.dal.dataobject.property.ProductPropertyDO;
import cn.zj.lgd.module.product.dal.dataobject.property.ProductPropertyValueDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 规格名称 Convert
 *
 *
 */
@Mapper
public interface ProductPropertyConvert {

    ProductPropertyConvert INSTANCE = Mappers.getMapper(ProductPropertyConvert.class);

    ProductPropertyDO convert(ProductPropertyCreateReqVO bean);

    ProductPropertyDO convert(ProductPropertyUpdateReqVO bean);

    ProductPropertyAndValueRespVO convert(ProductPropertyRespVO bean);

    ProductPropertyRespVO convert(ProductPropertyDO bean);

    List<ProductPropertyRespVO> convertList(List<ProductPropertyDO> list);

    PageResult<ProductPropertyRespVO> convertPage(PageResult<ProductPropertyDO> page);

}
