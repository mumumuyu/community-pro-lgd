package cn.zj.lgd.module.product.convert.brand;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.product.controller.admin.brand.vo.ProductBrandCreateReqVO;
import cn.zj.lgd.module.product.controller.admin.brand.vo.ProductBrandRespVO;
import cn.zj.lgd.module.product.controller.admin.brand.vo.ProductBrandUpdateReqVO;
import cn.zj.lgd.module.product.dal.dataobject.brand.ProductBrandDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 品牌 Convert
 *
 *
 */
@Mapper
public interface ProductBrandConvert {

    ProductBrandConvert INSTANCE = Mappers.getMapper(ProductBrandConvert.class);

    ProductBrandDO convert(ProductBrandCreateReqVO bean);

    ProductBrandDO convert(ProductBrandUpdateReqVO bean);

    ProductBrandRespVO convert(ProductBrandDO bean);

    List<ProductBrandRespVO> convertList(List<ProductBrandDO> list);

    PageResult<ProductBrandRespVO> convertPage(PageResult<ProductBrandDO> page);

}
