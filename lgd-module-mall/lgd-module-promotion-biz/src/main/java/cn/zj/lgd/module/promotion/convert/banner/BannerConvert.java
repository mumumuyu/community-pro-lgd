package cn.zj.lgd.module.promotion.convert.banner;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.module.promotion.controller.admin.banner.vo.BannerCreateReqVO;
import cn.zj.lgd.module.promotion.controller.admin.banner.vo.BannerRespVO;
import cn.zj.lgd.module.promotion.controller.admin.banner.vo.BannerUpdateReqVO;
import cn.zj.lgd.module.promotion.dal.dataobject.banner.BannerDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Banner Convert
 *
 * @author xia
 */
@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);


    List<BannerRespVO> convertList(List<BannerDO> list);

    PageResult<BannerRespVO> convertPage(PageResult<BannerDO> pageResult);

    BannerRespVO convert(BannerDO banner);

    BannerDO convert(BannerCreateReqVO createReqVO);

    BannerDO convert(BannerUpdateReqVO updateReqVO);

}
