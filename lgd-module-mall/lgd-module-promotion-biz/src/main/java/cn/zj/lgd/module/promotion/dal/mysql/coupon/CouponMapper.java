package cn.zj.lgd.module.promotion.dal.mysql.coupon;

import cn.zj.lgd.framework.common.pojo.PageResult;
import cn.zj.lgd.framework.mybatis.core.mapper.BaseMapperX;
import cn.zj.lgd.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.zj.lgd.module.promotion.controller.admin.coupon.vo.coupon.CouponPageReqVO;
import cn.zj.lgd.module.promotion.dal.dataobject.coupon.CouponDO;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * 优惠劵 Mapper
 *
 *
 */
@Mapper
public interface CouponMapper extends BaseMapperX<CouponDO> {

    default PageResult<CouponDO> selectPage(CouponPageReqVO reqVO, Collection<Long> userIds) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CouponDO>()
                .eqIfPresent(CouponDO::getTemplateId, reqVO.getTemplateId())
                .eqIfPresent(CouponDO::getStatus, reqVO.getStatus())
                .inIfPresent(CouponDO::getUserId, userIds)
                .betweenIfPresent(CouponDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CouponDO::getId));
    }

    default List<CouponDO> selectListByUserIdAndStatus(Long userId, Integer status) {
        return selectList(new LambdaQueryWrapperX<CouponDO>()
                .eq(CouponDO::getUserId, userId).eq(CouponDO::getStatus, status));
    }

    default CouponDO selectByIdAndUserId(Long id, Long userId) {
        return selectOne(new LambdaQueryWrapperX<CouponDO>()
                .eq(CouponDO::getId, id).eq(CouponDO::getUserId, userId));
    }

    default int delete(Long id, Collection<Integer> whereStatuses) {
        return update(null, new LambdaUpdateWrapper<CouponDO>()
                .eq(CouponDO::getId, id).in(CouponDO::getStatus, whereStatuses)
                .set(CouponDO::getDeleted, 1));
    }

    default int updateByIdAndStatus(Long id, Integer status, CouponDO updateObj) {
        return update(updateObj, new LambdaUpdateWrapper<CouponDO>()
                .eq(CouponDO::getId, id).eq(CouponDO::getStatus, status));
    }

}
