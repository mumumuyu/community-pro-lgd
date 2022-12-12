package cn.zj.lgd.module.product.dal.mysql.spu;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.framework.mybatis.core.mapper.BaseMapperX;
import cn.zj.lgd.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.zj.lgd.module.product.controller.admin.spu.vo.ProductSpuPageReqVO;
import cn.zj.lgd.module.product.dal.dataobject.spu.ProductSpuDO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 商品spu Mapper
 *
 *
 */
@Mapper
public interface ProductSpuMapper extends BaseMapperX<ProductSpuDO> {

    default PageResult<ProductSpuDO> selectPage(ProductSpuPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductSpuDO>()
                .likeIfPresent(ProductSpuDO::getName, reqVO.getName())
                .eqIfPresent(ProductSpuDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(ProductSpuDO::getStatus, reqVO.getStatus())
                .leIfPresent(ProductSpuDO::getSalesCount, reqVO.getSalesCountMax())
                .geIfPresent(ProductSpuDO::getSalesCount, reqVO.getSalesCountMin())
                .leIfPresent(ProductSpuDO::getMarketPrice, reqVO.getMarketPriceMax())
                .geIfPresent(ProductSpuDO::getMarketPrice, reqVO.getMarketPriceMin())
                .orderByDesc(ProductSpuDO::getSort));
    }

    default PageResult<ProductSpuDO> selectPage(ProductSpuPageReqVO reqVO, Set<Long> alarmStockSpuIds) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductSpuDO>()
                .likeIfPresent(ProductSpuDO::getName, reqVO.getName())
                .eqIfPresent(ProductSpuDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(ProductSpuDO::getStatus, reqVO.getStatus())
                .leIfPresent(ProductSpuDO::getSalesCount, reqVO.getSalesCountMax())
                .geIfPresent(ProductSpuDO::getSalesCount, reqVO.getSalesCountMin())
                .leIfPresent(ProductSpuDO::getMarketPrice, reqVO.getMarketPriceMax())
                .geIfPresent(ProductSpuDO::getMarketPrice, reqVO.getMarketPriceMin())
                .inIfPresent(ProductSpuDO::getId, alarmStockSpuIds) // 库存告警
                .eqIfPresent(ProductSpuDO::getStatus, reqVO.getStatus())
                .orderByDesc(ProductSpuDO::getSort));
    }

    /**
     * 更新商品 SPU 库存
     *
     * @param id 商品 SPU 编号
     * @param incrCount 增加的库存数量
     */
    default void updateStock(Long id, Integer incrCount) {
        LambdaUpdateWrapper<ProductSpuDO> updateWrapper = new LambdaUpdateWrapper<ProductSpuDO>()
                .setSql(" total_stock = total_stock +" + incrCount) // 负数，所以使用 + 号
                .eq(ProductSpuDO::getId, id);
        update(null, updateWrapper);
    }

}
