package cn.zj.lgd.module.product.convert.spu;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.product.api.spu.dto.ProductSpuRespDTO;
import cn.zj.lgd.module.product.controller.admin.spu.vo.*;
import cn.zj.lgd.module.product.controller.app.spu.vo.AppSpuPageReqVO;
import cn.zj.lgd.module.product.controller.app.spu.vo.AppSpuPageRespVO;
import cn.zj.lgd.module.product.dal.dataobject.spu.ProductSpuDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商品spu Convert
 *
 *
 */
@Mapper
public interface ProductSpuConvert {

    ProductSpuConvert INSTANCE = Mappers.getMapper(ProductSpuConvert.class);

    ProductSpuDO convert(ProductSpuCreateReqVO bean);

    ProductSpuDO convert(ProductSpuUpdateReqVO bean);

    ProductSpuRespVO convert(ProductSpuDO bean);

    List<ProductSpuRespVO> convertList(List<ProductSpuDO> list);

    PageResult<ProductSpuRespVO> convertPage(PageResult<ProductSpuDO> page);

    ProductSpuPageReqVO convert(AppSpuPageReqVO bean);

    AppSpuPageRespVO convertAppResp(ProductSpuDO list);

    List<ProductSpuRespDTO> convertList2(List<ProductSpuDO> list);

    List<ProductSpuSimpleRespVO> convertList02(List<ProductSpuDO> list);

}
