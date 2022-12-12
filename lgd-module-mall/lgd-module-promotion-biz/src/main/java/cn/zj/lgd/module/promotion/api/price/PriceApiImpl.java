package cn.zj.lgd.module.promotion.api.price;

import cn.zj.lgd.module.promotion.api.price.dto.PriceCalculateReqDTO;
import cn.zj.lgd.module.promotion.api.price.dto.PriceCalculateRespDTO;
import cn.zj.lgd.module.promotion.service.price.PriceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 价格 API 实现类
 *
 *
 */
@Service
public class PriceApiImpl implements PriceApi {

    @Resource
    private PriceService priceService;

    @Override
    public PriceCalculateRespDTO calculatePrice(PriceCalculateReqDTO calculateReqDTO) {
        return priceService.calculatePrice(calculateReqDTO);
    }

}
